package com.example.partiucompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ListaActivity extends AppCompatActivity {

    private Button salvar;
    private Button cancelar;
    private EditText nomeLista;
    Intent i = new Intent(this, MainActivity.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        salvar = (Button)findViewById(R.id.salvarLista);
        cancelar = (Button)findViewById(R.id.cancelarLista);
        nomeLista = (EditText) findViewById(R.id.idNomeNovaLista);

        salvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i.putExtra("key", nomeLista.getText());
                startActivity(i);
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish(); // Finaliza essa activity e volta para anterior
            }
        });
    }
}
