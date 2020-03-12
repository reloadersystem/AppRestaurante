package com.example.apprestaurante.adapters.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.apprestaurante.R;
import com.example.apprestaurante.helpers.Constants;
import com.example.apprestaurante.rest.response.PlatoCategoriaResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    Context mContext;
    List<PlatoCategoriaResponse.Datum> items;
    private static ClickListener clickListener;

    public MenuAdapter(Context context, List<PlatoCategoriaResponse.Datum> items) {
        this.mContext = context;
        this.items = items;
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivMenu;
        TextView tvTitulo;
        TextView tvSubtitulo;
        TextView tvPrecio;

        MenuViewHolder(View itemView) {
            super(itemView);

            ivMenu = itemView.findViewById(R.id.ivMenu);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvSubtitulo = itemView.findViewById(R.id.tvSubtitulo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_menu, viewGroup, false);
        MenuViewHolder pvh = new MenuViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder menuViewHolder, int position) {

        menuViewHolder.tvTitulo.setText(items.get(position).getNombre());
        menuViewHolder.tvSubtitulo.setText(items.get(position).getDescripcion());
        menuViewHolder.tvPrecio.setText("S/. " + items.get(position).getPrecio());

        Picasso.get().load(Constants.URL_PLATOS + items.get(position).getImagen()).into(menuViewHolder.ivMenu);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Click
    public void setOnItemClickListener(ClickListener clickListener) {
        MenuAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

}

