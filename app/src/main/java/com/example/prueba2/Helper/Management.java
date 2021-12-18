package com.example.prueba2.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.prueba2.model.ItemList;
import com.example.prueba2.model.ChangeNumberItemsListener;
import com.example.prueba2.model.TinyDB;

import java.util.ArrayList;

public class Management {
    private Context context;
    private TinyDB tinyDB;

    public Management(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertFood(ItemList item){
        ArrayList<ItemList> listfood= getListCard();
        boolean exitAlready = false;
        int n=0;
        for (int i = 0; i< listfood.size(); i++){
            if (listfood.get(i).getTitulo().equals(item.getTitulo())){
                exitAlready = true;
                n=i;
                break;
            }
        }

        if (exitAlready){
            listfood.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            listfood.add(item);
        }

        tinyDB.putListObject("CardList",listfood);
        Toast.makeText(context,"Agregado al carrito",Toast.LENGTH_SHORT).show();


    }

    public ArrayList<ItemList> getListCard() {
        return tinyDB.getListObject("CardList");
    }

    public void plusNumberFood(ArrayList<ItemList> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener){
            if (listfood.get(position).getNumberInCart() == 1) {
                listfood.remove(position);
        }   else {
                listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1);
            }
            tinyDB.putListObject("CardList",listfood);
            changeNumberItemsListener.changed();
    }

    public void MinusNumberFood(ArrayList<ItemList> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if (listfood.get(position).getNumberInCart()==1){
            listfood.remove(position);
        }else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CardList",listfood);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<ItemList> listfood2 = getListCard();
        double fee=0;
        for (int i = 0; i < listfood2.size();i++){
            fee=fee+(listfood2.get(i).getPrecio()*listfood2.get(i).getNumberInCart());
        }
        return fee;
    }

    public void Removefood(ArrayList<ItemList> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listfood.remove(position);
        tinyDB.putListObject("CardList",listfood);
        changeNumberItemsListener.changed();}
}
