package com.example.apprestaurante.ui.fragments.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apprestaurante.R;
import com.example.apprestaurante.adapters.recyclerview.CategoriaAdapter;
import com.example.apprestaurante.helpers.Helper;
import com.example.apprestaurante.rest.response.CategoriaResponse;
import com.example.apprestaurante.ui.fragments.MenuFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;

    private List<CategoriaResponse.Datum> data = new ArrayList<>();
    private CategoriaAdapter categoriaAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        categoriaAdapter = new CategoriaAdapter(getContext(), data);
        recyclerView.setAdapter(categoriaAdapter);

        categoriaAdapter.setOnItemClickListener(new CategoriaAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                Bundle bundle = new Bundle();
                bundle.putString("id_categoria", data.get(position).getId());

                MenuFragment menuFragment = new MenuFragment();
                menuFragment.setArguments(bundle);

                // Transacción
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, menuFragment, "");
                fragmentTransaction.addToBackStack(null); // Habilita la opción regrasar al fragment anterior
                fragmentTransaction.commit();

            }
        });

        getData();

        return root;
    }

    private void getData() {

        Call<CategoriaResponse> call = Helper.getApiService().getCategorias();
        call.enqueue(new Callback<CategoriaResponse>() {
            @Override
            public void onResponse(Call<CategoriaResponse> call, Response<CategoriaResponse> response) {
                if(response.code() == 200 && response.body() != null) {

                    for(CategoriaResponse.Datum datum : response.body().getData()) {
                        data.add(datum);
                    }

                    categoriaAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CategoriaResponse> call, Throwable t) {

            }
        });

    }

}