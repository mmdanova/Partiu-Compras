package com.example.partiucompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.partiucompras.BDHelper.ListaBD;
import com.example.partiucompras.model.Lista;

public class ListaActivity extends AppCompatActivity {


    public static final String nomeDaNovaLista = null;

    private Button btnSalvar;
    private Button btnCancelar;
    private EditText editTextNomeLista;
    private Lista editarLista, lista;
    private ListaBD bdHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista = new Lista();
        bdHelper = new ListaBD(ListaActivity.this);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        editarLista = (Lista) intent.getSerializableExtra("lista_escolhida");

        btnSalvar = (Button)findViewById(R.id.idSalvarLista);
        btnCancelar = (Button)findViewById(R.id.idCancelarLista);
        editTextNomeLista = (EditText) findViewById(R.id.idNomeNovaLista);

        if(editarLista != null) {
            btnSalvar.setText("Modificar Lista");
            editTextNomeLista.setText(editarLista.getNome());
            lista.setId(editarLista.getId());

        } else {
            btnSalvar.setText("Cadastrar");
        }

        btnSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lista.setNome(editTextNomeLista.getText().toString());

                if(btnSalvar.getText().toString().equals("Cadastrar")) {
                    bdHelper.salvarLista(lista);
                    bdHelper.close();
                } else {
                    bdHelper.alterarLista(lista);
                    bdHelper.close();
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
