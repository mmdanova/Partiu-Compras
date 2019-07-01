package com.example.partiucompras;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.partiucompras.model.Lista;

import java.util.ArrayList;


public class ProdutosDaLista extends AppCompatActivity {

    private Lista listaSelecionada;
    private ArrayList<String> produtos = new ArrayList<String>();
    private ListView listViewProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_da_lista);


        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        listaSelecionada = (Lista) intent.getSerializableExtra("lista_escolhida");
        listViewProdutos = (ListView) findViewById(R.id.idListagemProdutosDaLista);
        produtos.add("Batata - Mercado");
        produtos.add("Cenoura - Mercado");
        produtos.add("Maçã - Mercado");
        produtos.add("Banana - Mercado");
        produtos.add("Leite - Mercado");
        produtos.add("Chuveiro - Casa");
        produtos.add("Camiseta - Casa");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                produtos);

        listViewProdutos.setAdapter(arrayAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(ProdutosDaLista.this, AdicionarProdutosDaLista.class);
                //startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_produtos_da_lista, menu);
        return true;
    }



}
