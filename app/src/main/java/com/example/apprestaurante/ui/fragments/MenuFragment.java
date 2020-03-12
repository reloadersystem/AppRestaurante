package com.example.apprestaurante.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apprestaurante.R;
import com.example.apprestaurante.adapters.recyclerview.MenuAdapter;
import com.example.apprestaurante.beans.MenuBean;
import com.example.apprestaurante.helpers.Helper;
import com.example.apprestaurante.rest.ApiService;
import com.example.apprestaurante.rest.response.CategoriaResponse;
import com.example.apprestaurante.rest.response.PlatoCategoriaResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuFragment extends Fragment {

    RecyclerView recyclerView;

    private List<PlatoCategoriaResponse.Datum> items = new ArrayList<>();
    private MenuAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // Referencia
        recyclerView = view.findViewById(R.id.recyclerView);

        // Tipo de Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MenuAdapter(getContext(), items);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MenuAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", items.get(position));

                DetalleMenuFragment fragment = new DetalleMenuFragment();
                fragment.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment, "");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        getData();

        return view;
    }

    private void getData() {
        String id_categoria = getArguments().getString("id_categoria");

        Call<PlatoCategoriaResponse> call = Helper.getApiService().getPlatoCategoria(id_categoria);

        call.enqueue(new Callback<PlatoCategoriaResponse>() {
            @Override
            public void onResponse(Call<PlatoCategoriaResponse> call, Response<PlatoCategoriaResponse> response) {
                if(response.code() == 200 && response.body() != null) {
                    for(PlatoCategoriaResponse.Datum datum : response.body().getData()) {
                        items.add(datum);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PlatoCategoriaResponse> call, Throwable t) {

            }
        });

    }

}
