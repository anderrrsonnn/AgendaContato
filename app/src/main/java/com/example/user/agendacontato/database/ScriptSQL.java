package com.example.user.agendacontato.database;

/**
 * Created by User on 04/11/2016.
 */

public class ScriptSQL {

    public static String getCreateContato(){


        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS CONTATO( ");
        sqlBuilder.append("id         INTEGER      NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append(" NOME       VARCHAR(200)," );
        sqlBuilder.append("TELEFONE   VARCHAR(14), ");
        sqlBuilder.append("EMAIL      VARCHAR(255), ");
        sqlBuilder.append("ENDERECO   VARCHAR(255) ");
        sqlBuilder.append(" );" );

        return sqlBuilder.toString();
    }

}
