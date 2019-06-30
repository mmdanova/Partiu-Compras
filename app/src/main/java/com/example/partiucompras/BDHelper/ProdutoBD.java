package com.example.partiucompras.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.partiucompras.model.Produto;
import java.util.ArrayList;


public class ProdutoBD {


    private HelperDao dao;
    private SQLiteDatabase banco;

    public ProdutoBD(Context context) {
        dao = new HelperDao(context);
        banco = dao.getWritableDatabase();
    }

    public void salvarProduto(Produto produto) {
        ContentValues values = new ContentValues();

        values.put("nome", produto.getNome());
        values.put("categoria", produto.getCategoria());

        dao.getWritableDatabase().insert("Produto",null, values);
    }

    public void alterarProduto(Produto produto) {
        ContentValues values = new ContentValues();

        values.put("nome", produto.getNome());
        values.put("categoria", produto.getCategoria());

        String[] args = {produto.getId().toString()};
        dao.getWritableDatabase().update("Produto", values, "idProduto=?",args);
    }

    public void deletarProduto(Produto produto) {
        String[] args = {produto.getId().toString()};
        dao.getWritableDatabase().delete("Produto", "idProduto=?", args);
    }

    public ArrayList<Produto> getProduto() {
        String [] colunasP ={"idProduto","nome","categoria"};
        Cursor cursorP = dao.getWritableDatabase().query("Produto", colunasP, null, null, null,null,null);
        ArrayList<Produto> arrayProduto = new ArrayList<Produto>();

        while(cursorP.moveToNext()) {
            Produto produto = new Produto();
            produto.setId(cursorP.getLong(0));
            produto.setNome(cursorP.getString(1));
            produto.setCategoria(cursorP.getString(2));
            arrayProduto.add(produto);
        }
        return arrayProduto;
    }
}