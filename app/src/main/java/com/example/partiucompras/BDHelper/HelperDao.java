package com.example.partiucompras.BDHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperDao extends SQLiteOpenHelper {



    private static final String DATABASE = "bd_partiucompras";
    private static final Integer VERSION = 1;

    public HelperDao(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_create_tab_produto = "CREATE TABLE Produto(idProduto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT NOT NULL, categoria TEXT NOT NULL);";
        String query_create_tab_lista = "CREATE TABLE LISTA(idLista INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT NOT NULL);";
        String query_create_tab_lista_produto = "CREATE TABLE LISTA_PRODUTO (idListaProduto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, quantidadeProduto INTEGER NOT NULL, idProduto INTEGER, idLista INTEGER, FOREIGN KEY(idProduto) REFERENCES PRODUTO(idProduto), FOREIGN KEY(idLista) REFERENCES LISTA(idLista));";
        db.execSQL(query_create_tab_lista);
        db.execSQL(query_create_tab_produto);
        db.execSQL(query_create_tab_lista_produto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query_drop_tab_produto = "DROP TABLE IF EXISTS Produto";
        String query_drop_tab_lista = "DROP TABLE IF EXISTS LISTA";
        String query_drop_tab_lista_produto = "DROP TABLE IF EXISTS LISTA_PRODUTO";
        db.execSQL(query_drop_tab_lista);
        db.execSQL(query_drop_tab_produto);
        db.execSQL(query_drop_tab_lista_produto);
    }
}
