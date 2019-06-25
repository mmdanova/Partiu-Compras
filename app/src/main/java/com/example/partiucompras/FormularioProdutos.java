package com.example.partiucompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.partiucompras.BDHelper.ListaBD;
import com.example.partiucompras.BDHelper.ProdutoBD;
import com.example.partiucompras.model.Lista;
import com.example.partiucompras.model.Produto;

public class FormularioProdutos extends AppCompatActivity {


    private Button btnSalvar;
    private Button btnCancelar;
    private EditText editTextNomeProduto;
    private EditText editTextCategoriaProduto;
    private Produto editarProduto;
    private Produto produto;
    private ProdutoBD bdHelperProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produtos);


        produto = new Produto();
        bdHelperProduto = new ProdutoBD(FormularioProdutos.this);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        editarProduto = (Produto) intent.getSerializableExtra("produto_escolhido");

        btnSalvar = (Button)findViewById(R.id.idSalvarProduto);
        btnCancelar = (Button)findViewById(R.id.idCancelarProduto);
        editTextNomeProduto = (EditText) findViewById(R.id.idEditTextNomeProduto);
        editTextCategoriaProduto = (EditText) findViewById(R.id.idEditTextCategoriaProduto);

        if(editarProduto != null) {
            btnSalvar.setText("Modificar Produto");
            editTextNomeProduto.setText(editarProduto.getNome());
            editTextCategoriaProduto.setText(editarProduto.getCategoria());
            produto.setId(editarProduto.getId());

        } else {
            btnSalvar.setText("Cadastrar");
        }

        btnSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                produto.setNome(editTextCategoriaProduto.getText().toString());

                if(btnSalvar.getText().toString().equals("Cadastrar")) {
                    bdHelperProduto.salvarProduto(produto);
                    bdHelperProduto.close();
                } else {
                    bdHelperProduto.alterarProduto(produto);
                    bdHelperProduto.close();
                }
                finish();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish(); // Finaliza essa activity e volta para anterior
            }
        });
    }
}
