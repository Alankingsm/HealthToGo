package com.example.prueba2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba2.Helper.Management;
import com.example.prueba2.R;
import com.example.prueba2.adaptador.CardAdapter;
import com.example.prueba2.model.ChangeNumberItemsListener;
import com.example.prueba2.model.ItemList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CardListActivity2 extends AppCompatActivity implements OnMapReadyCallback {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private Management management;
    private TextView totalFeeTxt,totalTxt,emptyTxt;
    private ScrollView scrollView;
    private GoogleMap mMap;
    private Spinner met_pag;

    String[] datos = {"Tajeta Bancomer **2599","Tajeta Bancomer **9812"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list2);

        management = new Management(this);

        initView();
        initList();
        calculateCard();

        BarraNavegacion();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Elementos
        met_pag = (Spinner) findViewById(R.id.met_pag);


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,datos);

        met_pag.setAdapter(adaptador);


        met_pag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void BarraNavegacion(){
        //inicializamos y asignamos valriables
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        //colocamos el Home Selected
        bottomNavigationView.setSelectedItemId(R.id.carrito);

        //Perdorm ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(CardListActivity2.this, MainActivity.class));
                        //startActivity(new Intent(getApplicationContext(), Carrito.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.carrito:
                        finish();
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.opciones:
                        //mensaje();
                        Toast.makeText(CardListActivity2.this, "Eso no esta disponible", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.perfil:
                        //mensaje();
                        Toast.makeText(CardListActivity2.this, "Eso no esta disponible", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });
    }


    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CardAdapter(management.getListCard(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(management.getListCard().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }


    }

    private void calculateCard(){
        double total = Math.round((management.getTotalFee())*100.0)/100.0;
        totalFeeTxt.setText("$" + total);
        totalTxt.setText("$" + total);
    }

    private void initView() {
        recyclerViewList=findViewById((R.id.recyclerview));
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        totalTxt=findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView2);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<ItemList> listaProductos=management.getListCard();

        for (int i=0;i <listaProductos.size();i++){
            LatLng ubicacion = new LatLng(listaProductos.get(i).getLatitude(), listaProductos.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(ubicacion)
                    .title(listaProductos.get(i).getNombreFarmacia()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 16));
        }
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 16));
    }

    public void BottonLogo(View view){
        startActivity(new Intent(CardListActivity2.this, MainActivity.class));
        //startActivity(new Intent(getApplicationContext(), Carrito.class));
        overridePendingTransition(0, 0);
        finish();
    }

    public void BottonProfile(View view){
        Toast.makeText(CardListActivity2.this, "Eso no esta disponible", Toast.LENGTH_SHORT).show();
    }
}