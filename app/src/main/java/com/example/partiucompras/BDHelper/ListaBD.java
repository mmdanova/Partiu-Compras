package com.example.partiucompras.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.partiucompras.model.Lista;
import java.util.ArrayList;

public class ListaBD extends SQLiteOpenHelper {

        public static final String DATABASE = "bd_partiucompras";
        public static final Integer VERSION = 1;

        public ListaBD(Context context) {
            super(context, DATABASE, null, VERSION);
        }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_create_tab_lista = "CREATE TABLE LISTA(idLista INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT NOT NULL);";
        db.execSQL(query_create_tab_lista);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query_drop_tab_lista = "DROP TABLE IF EXISTS LISTA";
         db.execSQL(query_drop_tab_lista);
    }

    public void salvarLista(Lista lista) {
        ContentValues values = new ContentValues();

        values.put("nome", lista.getNome());

        getWritableDatabase().insert("lista",null, values);

    }

    public void alterarLista(Lista lista) {
        ContentValues values = new ContentValues();

        values.put("nome", lista.getNome());

        String[] args = {lista.getId().toString()};
        getWritableDatabase().update("lista", values, "idLista=?",args);
    }

    public void deletarLista(Lista lista) {
        String[] args = {lista.getId().toString()};
        getWritableDatabase().delete("lista", "idLista=?", args);
    }

    public ArrayList<Lista> getLista() {
        String [] colunas ={"idLista","nome"};
        Cursor cursor = getWritableDatabase().query("lista", colunas, null, null, null,null,null);
        ArrayList<Lista> arrayLista = new ArrayList<Lista>();

        while(cursor.moveToNext()) {
            Lista lista = new Lista();
            lista.setId(cursor.getLong(0));
            lista.setNome(cursor.getString(1));
            arrayLista.add(lista);
        }
        return arrayLista;
    }

}
