package com.example.partiucompras;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.ContextMenu;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.partiucompras.BDHelper.ListaBD;
import com.example.partiucompras.model.Lista;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listagemListas;
    private ListaBD bdHelperLista;
    private ArrayList<Lista> listas;
    private Lista lista;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pegando as view
        listagemListas = (ListView) findViewById(R.id.idListagemListas);
        registerForContextMenu(listagemListas);

        // Ao dar um clique longo, pegar o id
        listagemListas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                lista = (Lista) adapter.getItemAtPosition(position);
                return false;
            }
        });

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    //Deleta a lista selecionada com o clique longo
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuEditar = menu.add("Editar esta lista");
        MenuItem menuDelete = menu.add("Deletar esta lista");

        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                bdHelperLista = new ListaBD(MainActivity.this);
                bdHelperLista.deletarLista(lista);
                //bdHelperLista.close();
                carregarLista();
                return true;
            }
        });

        menuEditar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                final Lista listaEscolhida = listas.get(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
                Intent i = new Intent(MainActivity.this, ListaActivity.class);
                i.putExtra("lista_escolhida", listaEscolhida);
                startActivity(i);
                return true;
            }
        });

    }

    //Carregar lista automaticamente
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    //Lista as listas
    public void carregarLista() {
        bdHelperLista = new ListaBD(MainActivity.this);
        listas = bdHelperLista.getLista();
        //bdHelperLista.close();
        if (listas != null) {
            arrayAdapter = new ArrayAdapter<Lista>(MainActivity.this, android.R.layout.simple_list_item_1, listas);
            listagemListas.setAdapter(arrayAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_listas) {
            // Handle the camera action
        } else if (id == R.id.nav_produtos) {
            Intent intencao = new Intent(MainActivity.this, ListarProduto.class);
            //Erro ao listar os produtos. Necessario verificar
            startActivity(intencao);
        } else if (id == R.id.nav_historico_compras) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
