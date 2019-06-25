package com.example.partiucompras;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.partiucompras.BDHelper.ListaBD;
import com.example.partiucompras.BDHelper.ProdutoBD;
import com.example.partiucompras.model.Lista;
import com.example.partiucompras.model.Produto;

import java.util.ArrayList;

public class ListarProduto extends AppCompatActivity {

    private ListView listViewListagemProdutos;
    private ProdutoBD bdHelperProduto;
    private ArrayList<Produto> arrayListProdutos;
    private Produto produto;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Pegando as view
        listViewListagemProdutos = (ListView) findViewById(R.id.idListagemProdutos);
        Button btnIncluirProduto = (Button) findViewById(R.id.btnIncluirProduto);
        registerForContextMenu(listViewListagemProdutos);

        //
        listViewListagemProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                produto = (Produto) adapter.getItemAtPosition(position);
                return false;
            }
        });

        // Ao clicar no botão de Incluir, o método abaixo é executado.
        btnIncluirProduto.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ListarProduto.this, FormularioProdutos.class);
                startActivity(intent);
            }
        });

        // Abrindo a tela para modificar o produto
        listViewListagemProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Produto produtoEscolhido = (Produto) adapter.getItemAtPosition(position);
                Intent i = new Intent(ListarProduto.this, FormularioProdutos.class);
                i.putExtra("produto_escolhido", produtoEscolhido);
                startActivity(i);
            }
        });


    }
    //Deleta o produto selecionado com o clique longo
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar este produto");

        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                bdHelperProduto = new ProdutoBD(ListarProduto.this);
                bdHelperProduto.deletarProduto(produto);
                bdHelperProduto.close();
                carregarProdutos();
                return true;
            }
        });

    }

    //Carregar produtos automaticamente
    protected void onResume() {
        super.onResume();
        carregarProdutos();
    }

    //Lista os produtos
    public void carregarProdutos() {
        bdHelperProduto = new ProdutoBD(ListarProduto.this);
        arrayListProdutos = bdHelperProduto.getProduto();
        bdHelperProduto.close();
        if (arrayListProdutos != null) {
            arrayAdapter = new ArrayAdapter<Produto>(ListarProduto.this, android.R.layout.simple_list_item_1, arrayListProdutos);
            listViewListagemProdutos.setAdapter(arrayAdapter);
        }
    }

}
