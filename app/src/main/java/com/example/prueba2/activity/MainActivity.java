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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapter adapter;
    private List<ItemList> items;
    private TextView search_botton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initViews();
        //initValues();
        //initListener();

        //bottonNavigation();
        search_botton = (TextView) findViewById(R.id.search_botton);
        search_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Search_List.class );
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });
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
                        return true;
                    case R.id.carrito:
                        startActivity(new Intent(MainActivity.this, CardListActivity2.class));
                        //startActivity(new Intent(getApplicationContext(), Carrito.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.opciones:
                        Toast.makeText(MainActivity.this, "Eso no esta disponible", Toast.LENGTH_SHORT).show();
                        //mensaje();
                        return true;
                    case R.id.perfil:
                        //mensaje();
                        Toast.makeText(MainActivity.this, "Eso no esta disponible", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });
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
        return false;
    }


    public void BottonLogo(View view){

    }

    public void BottonProfile(View view){
        Toast.makeText(MainActivity.this, "Eso no esta disponible", Toast.LENGTH_SHORT).show();
    }

}
