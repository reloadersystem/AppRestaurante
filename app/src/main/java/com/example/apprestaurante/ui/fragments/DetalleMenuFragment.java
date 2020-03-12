package com.example.apprestaurante.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apprestaurante.R;
import com.example.apprestaurante.beans.MenuBean;
import com.squareup.picasso.Picasso;

public class DetalleMenuFragment extends Fragment {

    private ImageView iv_menu;
    private ImageView iv_favorito;
    private TextView tv_titulo;
    private TextView tv_precio;

    private boolean favorito = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_menu, container, false);

        // Referencias
        iv_menu = view.findViewById(R.id.iv_menu);
        iv_favorito = view.findViewById(R.id.iv_favorito);
        tv_titulo = view.findViewById(R.id.tv_titulo);
        tv_precio = view.findViewById(R.id.tv_precio);

        //
        Picasso.get().load("https://images-gmi-pmc.edge-generalmills.com/8b79836e-e3b4-4099-bf3b-79a21257b759.jpg").fit().centerInside().into(iv_menu);

        // Acciones
        iv_favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(favorito) {
                    favorito = false;
                    iv_favorito.setImageResource(R.drawable.ic_favorite_border_24px);
                } else {
                    favorito = true;
                    iv_favorito.setImageResource(R.drawable.ic_favorite_24px);
                }

                SharedPreferences preferences = getActivity().getSharedPreferences("APP", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("favorito", favorito);
                editor.apply();
            }
        });

        // Obtener data
        MenuBean data = (MenuBean) getArguments().getSerializable("data");
        tv_titulo.setText(data.getTitulo());
        tv_precio.setText("S/." + data.getPrecio());

        SharedPreferences preferences = getActivity().getSharedPreferences("APP", Context.MODE_PRIVATE);
        if(preferences.getBoolean("favorito", false)) {
            iv_favorito.setImageResource(R.drawable.ic_favorite_24px);
        } else {
            iv_favorito.setImageResource(R.drawable.ic_favorite_border_24px);
        }

        return view;
    }

}