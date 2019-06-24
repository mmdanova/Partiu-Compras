package com.example.partiucompras.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.partiucompras.model.Lista;
import com.example.partiucompras.model.Produto;

import java.util.ArrayList;


public class ProdutoBD extends SQLiteOpenHelper {


    public ProdutoBD(Context context) {
        super(context, ListaBD.DATABASE, null, ListaBD.VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_create_tab_produto = "CREATE TABLE PRODUTO(idProduto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT NOT NULL, categoria TEXT NOT NULL);";
        db.execSQL(query_create_tab_produto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query_drop_tab_produto = "DROP TABLE IF EXISTS PRODUTO";
        db.execSQL(query_drop_tab_produto);
    }

    public void salvarProduto(Produto produto) {
        ContentValues values = new ContentValues();

        values.put("nome", produto.getNome());
        values.put("categoria", produto.getCategoria());

        getWritableDatabase().insert("PRODUTO",null, values);

    }

    public void alterarProduto(Produto produto) {
        ContentValues values = new ContentValues();

        values.put("nome", produto.getNome());
        values.put("categoria", produto.getCategoria());

        String[] args = {produto.getId().toString()};
        getWritableDatabase().update("PRODUTO", values, "idProduto=?",args);
    }

    public void deletarProduto(Produto produto) {
        String[] args = {produto.getId().toString()};
        getWritableDatabase().delete("PRODUTO", "idProduto=?", args);
    }

    public ArrayList<Produto> getProduto() {
        String [] colunas ={"idProduto","nome", "categoria"};
        Cursor cursor = getWritableDatabase().query("PRODUTO", colunas, null, null, null,null,null);
        ArrayList<Produto> arrayProduto = new ArrayList<Produto>();

        while(cursor.moveToNext()) {
            Produto produto = new Produto();
            produto.setId(cursor.getLong(0));
            produto.setNome(cursor.getString(1));
            arrayProduto.add(produto);
        }
        return arrayProduto;
    }
}
