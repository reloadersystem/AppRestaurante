package com.example.apprestaurante.adapters.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.apprestaurante.R;
import com.example.apprestaurante.beans.MenuBean;
import com.example.apprestaurante.rest.response.CategoriaResponse;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.MenuViewHolder> {

    Context mContext;
    List<CategoriaResponse.Datum> items;
    private static ClickListener clickListener;

    public CategoriaAdapter(Context context, List<CategoriaResponse.Datum> items) {
        this.mContext = context;
        this.items = items;
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_nombre;

        MenuViewHolder(View itemView) {
            super(itemView);

            tv_nombre = itemView.findViewById(R.id.tv_nombre);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_categoria, viewGroup, false);
        MenuViewHolder pvh = new MenuViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder menuViewHolder, int position) {
        menuViewHolder.tv_nombre.setText(items.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Click
    public void setOnItemClickListener(ClickListener clickListener) {
        CategoriaAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

}

