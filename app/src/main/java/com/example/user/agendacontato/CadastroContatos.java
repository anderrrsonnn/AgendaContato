package com.example.user.agendacontato;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.agendacontato.database.DataBase;
import com.example.user.agendacontato.dominio.RepositorioContato;
import com.example.user.agendacontato.dominio.entidades.Contato;

/**
 * Created by User on 01/11/2016.
 */

public class CadastroContatos extends AppCompatActivity {

    private EditText edt_nome;
    private EditText edt_telefone;
    private EditText edt_email;
    private EditText edt_endereco;

    private Button bt_salvar;
    private Button bt_incluir;
    private Button bt_excluir;

    private DataBase dataBase;
    private SQLiteDatabase con;
    private RepositorioContato repositorioContato;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_cadastro);

        edt_nome = (EditText) findViewById(R.id.edt_nome);
        edt_telefone = (EditText) findViewById(R.id.edt_telefone);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_endereco = (EditText) findViewById(R.id.edt_endereco);

        try {


            dataBase = new DataBase(this);

            con = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(con);


        }catch (SQLiteException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar banco!" + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();

        }
    }

    public void Salvar(View v){
        bt_salvar = (Button) findViewById(R.id.salvar);

        if(contato == null ){
            Inserir();
        }
        finish();
    }

    private void Inserir(){

        try {

        contato = new Contato();

        contato.setNome(edt_nome.getText().toString());
        contato.setTelefone(edt_telefone.getText().toString());
        contato.setEmail(edt_email.getText().toString());
        contato.setEndereco(edt_endereco.getText().toString());

        repositorioContato.Inserir(contato);
        }catch (Exception ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir os dados!" + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();

        }
    }
}
