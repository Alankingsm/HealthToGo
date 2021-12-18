package com.example.prueba2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba2.R;
import com.example.prueba2.adaptador.RecyclerAdapter;
import com.example.prueba2.model.ItemList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Search_List extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapter adapter;
    private List<ItemList> items;
    private TextView text_prom,prm_sear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list);

        initViews();
        initValues();
        initListener();

        //bottonNavigation();
        BarraNavegacion();
    }

    //private void bottonNavigation() {
    //FloatingActionButton floatingActionButton = findViewById(R.id.btn_cart);

    //floatingActionButton.setOnClickListener(new View.OnClickListener() {
    // @Override
    //public void onClick(View view) {
    //    startActivity(new Intent(MainActivity.this, CardListActivity2.class));
    //  }
    //});
    //}

    private void BarraNavegacion(){
        //inicializamos y asignamos valriables
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        //colocamos el Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perdorm ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(Search_List.this, MainActivity.class));
                        //startActivity(new Intent(getApplicationContext(), Carrito.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.carrito:
                        startActivity(new Intent(Search_List.this, CardListActivity2.class));
                        //startActivity(new Intent(getApplicationContext(), Carrito.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.opciones:
                        Toast.makeText(Search_List.this, "Eso no esta disponible", Toast.LENGTH_SHORT).show();
                        //mensaje();
                        return true;
                    case R.id.perfil:
                        //mensaje();
                        Toast.makeText(Search_List.this, "Eso no esta disponible", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });
    }


    private void initViews(){
        rvLista = findViewById(R.id.rvLista);
        svSearch = findViewById(R.id.svSearch);
        text_prom = findViewById(R.id.text_prom);
        prm_sear = findViewById(R.id.prm_sear);

    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new RecyclerAdapter(items, this);
        rvLista.setAdapter(adapter);
        prm_sear.setVisibility(View.GONE);
        text_prom.setVisibility(View.GONE);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<ItemList> getItems() {
        List<ItemList> itemLists = new ArrayList<>();
        itemLists.add(new ItemList("Alcohol 600 ml", "dasdasdas",12.21, R.drawable.alcohol, 19.36651028798931,-99.11097698595609,"Faramacia A"));
        itemLists.add(new ItemList("Ampicilina 4", "La ultima transformacion de la saga no canon.",19.00,R.drawable.ampicilina, 19.365062869436077, -99.11171191123735,"Faramacia B" ));
        itemLists.add(new ItemList("Benzatina", "Goku y Vegeta, la transformacion de dioses.",28.90, R.drawable.benzatina, 19.36502238202219, -99.11269359976554,"Faramacia C"));
        itemLists.add(new ItemList("Losartan", "Infaltablñe power-up a Goku.",87.90,R.drawable.losartan,19.36651028798931,-99.11097698595609,"Faramacia A"));
        itemLists.add(new ItemList("Mejoral", "Diferentes transformaciones de super Vegeta.",21.23, R.drawable.mejoral,19.365062869436077, -99.11171191123735,"Faramacia B"));
        itemLists.add(new ItemList("Mestinon", "Vegeta sapbe o no sapbe xD.",65.25, R.drawable.mestinon,19.36502238202219, -99.11269359976554, "Faramacia C"));
        /*itemLists.add(new ItemList("Saga de Broly", "Ultima pelicula de DB, peleas epicas.", R.drawable.saga_broly));
        itemLists.add(new ItemList("Super sayayines 4", "La ultima transformacion de la saga no canon.",, R.drawable.ssj4s));
        itemLists.add(new ItemList("Super Sayayiness Blues", "Goku y Vegeta, la transformacion de dioses.",, R.drawable.ssj_blues));
        itemLists.add(new ItemList("Goku ultrainstinto", "Infaltablñe power-up a Goku.", R.drawable.ultrainsitinto));
        itemLists.add(new ItemList("Super Vegeta Blue x2", "Diferentes transformaciones de super Vegeta.", R.drawable.super_vegeta));
        itemLists.add(new ItemList("Vegeta sapbe", "Vegeta sapbe o no sapbe xD.", R.drawable.vegeta_blue));
*/
        return itemLists;
    }

    @Override
    public void itemClick(ItemList item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemDetail", item);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);


        if (newText.length()!=0){
            double average =0;
            for (int i= 0; i < items.size() ;i++){
                average = (average+(items.get(i).getPrecio()))/items.size();
            }
            prm_sear.setText(String.format("$ %.2f", average));
            prm_sear.setVisibility(View.VISIBLE);
            text_prom.setVisibility(View.VISIBLE);
        }


        return false;
    }
}
