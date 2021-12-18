package com.example.prueba2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba2.Helper.Management;
import com.example.prueba2.R;
import com.example.prueba2.model.ItemList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ImageView imgItemDetail;
    private TextView tvTituloDetail;
    private TextView tvDescripcionDetail;
    private TextView tvPrecioDetail;
    private ItemList itemDetail;
    WebView mapsDetail;

    //Para agregar al carrito
    private TextView addtoCardBtn;
    private TextView feeTxt,numberOrderTxt;
    private ImageView plusBtn,minusBtn;
    private int numberOrder = 1;

    //Para el carrito
    private Management management;

    //para Mapa
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        management = new Management(this);
        setTitle(getClass().getSimpleName());

        initViews();
        initValues();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getBundle();

        BarraNavegacion();
    }

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
                        startActivity(new Intent(DetailActivity.this, MainActivity.class));
                        //startActivity(new Intent(getApplicationContext(), Carrito.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.carrito:
                        startActivity(new Intent(DetailActivity.this, CardListActivity2.class));
                        //startActivity(new Intent(getApplicationContext(), Carrito.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.opciones:
                        //mensaje();
                        Toast.makeText(DetailActivity.this, "Eso no esta disponible", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.perfil:
                        //mensaje();
                        Toast.makeText(DetailActivity.this, "Eso no esta disponible", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });
    }

    private void getBundle() {
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOrder>1){
                    numberOrder = numberOrder-1;

                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addtoCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemDetail.setNumberInCart(numberOrder);
                management.insertFood(itemDetail);
            }
        });
    }

    private void initViews() {
        imgItemDetail = findViewById(R.id.imgItemDetail);
        tvTituloDetail = findViewById(R.id.tvTituloDetail);
        tvDescripcionDetail = findViewById(R.id.tvDescripcionDetail);
        tvPrecioDetail = findViewById(R.id.tvPrecioDetail);
        addtoCardBtn = findViewById(R.id.AddDetail);
        numberOrderTxt = findViewById(R.id.number_item);
        plusBtn = findViewById(R.id.masDetail);
        minusBtn = findViewById(R.id.menosDetail);

    }

    private void initValues(){
        //addtoCardBtn= (R.id.AddDetail);
        itemDetail = (ItemList) getIntent().getExtras().getSerializable("itemDetail");
        imgItemDetail.setImageResource(itemDetail.getImgResource());
        tvTituloDetail.setText(itemDetail.getTitulo());
        tvDescripcionDetail.setText(itemDetail.getDescripcion());
        tvPrecioDetail.setText(String.valueOf(itemDetail.getPrecio()));
        numberOrderTxt.setText(String.valueOf(numberOrder));
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(itemDetail.getLatitude(), itemDetail.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title(itemDetail.getNombreFarmacia()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));
    }

    public void BottonLogo(View view){
        overridePendingTransition(0, 0);
        finish();
    }

    public void BottonProfile(View view){
        Toast.makeText(DetailActivity.this, "Eso no esta disponible", Toast.LENGTH_SHORT).show();
    }
}
