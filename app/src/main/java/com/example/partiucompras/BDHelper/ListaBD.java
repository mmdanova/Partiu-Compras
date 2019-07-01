package com.example.partiucompras.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.partiucompras.model.Lista;
import com.example.partiucompras.model.Produto;

import java.util.ArrayList;

public class ListaBD {

    private HelperDao dao;
    private SQLiteDatabase banco;

    public ListaBD(Context context) {
        dao = new HelperDao(context);
        banco = dao.getWritableDatabase();
    }

    public void salvarLista(Lista lista) {
        ContentValues values = new ContentValues();

        values.put("nome", lista.getNome());

        dao.getWritableDatabase().insert("lista",null, values);

    }

    public void alterarLista(Lista lista) {
        ContentValues values = new ContentValues();

        values.put("nome", lista.getNome());

        String[] args = {lista.getId().toString()};
        dao.getWritableDatabase().update("lista", values, "idLista=?",args);
    }

    public void deletarLista(Lista lista) {
        String[] args = {lista.getId().toString()};
        dao.getWritableDatabase().delete("lista", "idLista=?", args);
    }

    public ArrayList<Lista> getLista() {
        String [] colunas ={"idLista","nome"};
        Cursor cursor = dao.getWritableDatabase().query("lista", colunas, null, null, null,null,null);
        ArrayList<Lista> arrayLista = new ArrayList<Lista>();

        while(cursor.moveToNext()) {
            Lista lista = new Lista();
            lista.setId(cursor.getLong(0));
            lista.setNome(cursor.getString(1));
            arrayLista.add(lista);
        }
        return arrayLista;
    }

    public ArrayList<Produto> getProdutosDaLista(Integer listaSelecionada) {
        String [] colunas ={listaSelecionada.toString()};
        Cursor cursor = banco.rawQuery("SELECT * FROM LISTA_PRODUTO LP" +
                "INNER JOIN PRODUTO P ON P.idProduto = LP.idProduto" +
                "WHERE LP.idLista ="+listaSelecionada, null);
        cursor.moveToFirst();

        //
        ArrayList<Produto> arrayLista = new ArrayList<Produto>();

        while(cursor.moveToNext()) {
            Produto produto = new Produto();
            produto.setId(cursor.getLong(0));
            produto.setNome(cursor.getString(1));
            arrayLista.add(produto);
        }
        return arrayLista;
    }

}
