package com.example.prueba2.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba2.R;
import com.example.prueba2.model.ChangeNumberItemsListener;
import com.example.prueba2.model.ItemList;
import com.example.prueba2.Helper.Management;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    ArrayList<ItemList> itemLists;
    private Management management;
    private ChangeNumberItemsListener changeNumberItemsListener;


    public CardAdapter(ArrayList<ItemList> itemLists, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.itemLists = itemLists;
        management = new Management(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(itemLists.get(position).getTitulo());
        holder.precioEachItem.setText(String.valueOf(itemLists.get(position).getPrecio()));
        holder.TotalEachItem.setText(String.valueOf(Math.round(itemLists.get(position).getNumberInCart() * itemLists.get(position).getPrecio() * 100.0) / 100.0));
        holder.num.setText(String.valueOf(itemLists.get(position).getNumberInCart()));

        holder.pic.setImageResource(itemLists.get(position).getImgResource());

        holder.plusCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                management.plusNumberFood(itemLists, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.minusCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                management.MinusNumberFood(itemLists, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();

                    }
                });
            }
        });

        holder.remove_card_elem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                management.Removefood(itemLists, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });


    }


    @Override
    public int getItemCount() {
        return itemLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, precioEachItem;
        ImageView pic, plusCard, minusCard;
        TextView TotalEachItem, num;
        ImageView remove_card_elem;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title2Txt);
            precioEachItem = itemView.findViewById(R.id.precioEachItem);
            pic = itemView.findViewById(R.id.imCard);
            TotalEachItem = itemView.findViewById(R.id.TotalEachItem);
            num = itemView.findViewById(R.id.numberitemTxt);
            plusCard = itemView.findViewById(R.id.plusCard);
            minusCard = itemView.findViewById(R.id.minusCard);
            remove_card_elem = itemView.findViewById((R.id.remove_card_elem));
        }
    }
}
