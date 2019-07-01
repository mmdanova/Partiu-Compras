package com.example.partiucompras;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.partiucompras.BDHelper.ProdutoBD;
import com.example.partiucompras.model.Produto;

import java.util.ArrayList;

public class AdicionarProdutosDaLista extends AppCompatActivity {

    private ListView listViewListagemProdutosParaAdicionarNaLista;
    private ProdutoBD bdHelperProduto;
    private ArrayList<Produto> arrayListProdutos;
    private Produto produto;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_produtos_da_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Pegando as view
        listViewListagemProdutosParaAdicionarNaLista = (ListView) findViewById(R.id.idAdicionarProdutosDaLista);


        //registerForContextMenu(listViewListagemProdutosParaAdicionarNaLista);

        //
//        listViewListagemProdutosParaAdicionarNaLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
//                produto = (Produto) adapter.getItemAtPosition(position);
//                return false;
//            }
//        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdicionarProdutosDaLista.this, FormularioProdutos.class);
                startActivity(intent);
            }
        });

    }
    /*
    //Deleta o produto selecionado com o clique longo
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuEditar = menu.add("Editar este produto");
        MenuItem menuDelete = menu.add("Deletar este produto");

        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                bdHelperProduto = new ProdutoBD(AdicionarProdutosDaLista.this);
                bdHelperProduto.deletarProduto(produto);
                //bdHelperProduto.close();
                carregarProdutos();
                return true;
            }
        });

        menuEditar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                final Produto produtoEscolhido = arrayListProdutos.get(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
                Intent i = new Intent(AdicionarProdutosDaLista.this, FormularioProdutos.class);
                i.putExtra("produto_escolhido", produtoEscolhido);
                startActivity(i);
                return true;
            }
        });
    }
*/
    //Carregar produtos automaticamente
    protected void onResume() {
        super.onResume();
        carregarProdutos();
    }

    //Lista os produtos
    public void carregarProdutos() {
        bdHelperProduto = new ProdutoBD(AdicionarProdutosDaLista.this);
        arrayListProdutos = bdHelperProduto.getProduto();
        //bdHelperProduto.close();
        if (arrayListProdutos != null) {
            arrayAdapter = new ArrayAdapter<Produto>(AdicionarProdutosDaLista.this, android.R.layout.simple_list_item_multiple_choice, arrayListProdutos);
            listViewListagemProdutosParaAdicionarNaLista.setAdapter(arrayAdapter);
        }
    }

}
