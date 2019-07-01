package com.example.partiucompras.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.partiucompras.model.Lista;
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

    public void deletarProdutoLista(Lista lista, Produto produto) {
        String[] args = {produto.getId().toString(), lista.getId().toString()};
        dao.getWritableDatabase().delete("LISTA_PRODUTO", "idProduto=? and idLista=? ", args);
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



    public void cargaProduto() {
        String query = "INSERT INTO Produto(nome, categoria) " +
                "values ('Batata','Mercado')," +
                "('Cenoura','Mercado')," +
                "('Maçã','Mercado')," +
                "('Banana','Mercado')," +
                "('Leite','Mercado')," +
                "('Macarrão','Mercado')," +
                "('Feijão','Mercado')," +
                "('Arroz','Mercado')," +
                "('Carne de Porco','Mercado')," +
                "('Carne de Frango','Mercado')," +
                "('Chuveiro','Casa')," +
                "('Maçaneta','Casa')," +
                "('Lâmpada','Casa')," +
                "('Abajur','Casa')," +
                "('Mesa de Jantar','Casa')," +
                "('Camiseta','Casa')," +
                "('Calça','Casa')," +
                "('Tênis','Casa')," +
                "('Meia','Casa')," +
                "('Moletom','Casa');";
        banco.execSQL(query);
    }
    public void cargaProdutoLista() {
        String query = "INSERT INTO Lista_Produto(idLista, idProduto, quantidade) " +
                "values (1,10,1)," +
                "(1,1,1)," +
                "(1,2,1)," +
                "(1,3,1)," +
                "(2,4,1)," +
                "(2,5,1)," +
                "(2,6,1)," +
                "(2,7,1)," +
                "(2,8,1)," +
                "(2,9,1)," +
                "(2,10,1)," +
                "(3,11,1)," +
                "(3,8,1)," +
                "(3,9,1)," +
                "(3,10,1);";
        banco.execSQL(query);
    }
}