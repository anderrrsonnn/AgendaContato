package com.example.user.agendacontato;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import  android.database.sqlite.*;
import  android.database.*;

import com.example.user.agendacontato.database.DataBase;
import com.example.user.agendacontato.dominio.RepositorioContato;
import com.example.user.agendacontato.dominio.entidades.Contato;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnAdicionar;
    private EditText edtPesquisar;
    private ListView lstContatos;
    private ArrayAdapter<Contato> adpContatos;

    private DataBase dataBase;
    private SQLiteDatabase con;
    private RepositorioContato repositorioContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdicionar = (ImageButton)findViewById(R.id.btn_adicionar);
        edtPesquisar = (EditText)findViewById(R.id.edt_pesquisa);
        lstContatos = (ListView)findViewById(R.id.lst_contatos);

        btnAdicionar.setOnClickListener(this);

        try {


            dataBase = new DataBase(this);

            con = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(con);

            adpContatos = repositorioContato.buscaContatos(this);

            lstContatos.setAdapter(adpContatos);


        }catch (SQLiteException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar banco!" + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();

        }
    }

    @Override
    public void onClick(View v) {

        Intent it = new Intent(this, CadastroContatos.class);
        startActivityForResult(it, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        adpContatos = repositorioContato.buscaContatos(this);
        lstContatos.setAdapter(adpContatos);
    }
}
