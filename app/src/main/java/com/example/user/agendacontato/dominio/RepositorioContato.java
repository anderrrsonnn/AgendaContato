package com.example.user.agendacontato.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.user.agendacontato.dominio.entidades.Contato;

/**
 * Created by User on 07/11/2016.
 */

public class RepositorioContato {

    private SQLiteDatabase conn;

    public RepositorioContato(SQLiteDatabase conn){

        this.conn = conn;

    }

    public void Inserir(Contato contato){

        ContentValues values = new ContentValues();

        values.put("NOME", contato.getNome());
        values.put("TELEFONE", contato.getTelefone());
        values.put("EMAIL", contato.getEmail());
        values.put("ENDERECO", contato.getEndereco());

        conn.insertOrThrow("CONTATO", null, values);


    }


    public ArrayAdapter<Contato> buscaContatos(Context context){

        ArrayAdapter<Contato> adpContatos = new ArrayAdapter<Contato>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conn.query("CONTATO", null, null, null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
                do {

                    Contato contato = new Contato();
                    contato.setNome(cursor.getString(1));
                    contato.setTelefone(cursor.getString(2));
                    contato.setEmail(cursor.getString(3));
                    contato.setEndereco(cursor.getString(4));
                    adpContatos.add(contato);

            } while (cursor.moveToNext());

        }

        return adpContatos;

    }

}
