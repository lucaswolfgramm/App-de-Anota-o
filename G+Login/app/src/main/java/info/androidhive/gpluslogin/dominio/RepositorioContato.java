package info.androidhive.gpluslogin.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import info.androidhive.gpluslogin.dominio.entidades.Anotacao;


/**
 * Created by Douglas on 05/10/2016.
 */
public class RepositorioContato {

    private SQLiteDatabase conn;

    public RepositorioContato(SQLiteDatabase conn){

        this.conn = conn;

    }

    public void inserir(Anotacao contato){
        ContentValues values = new ContentValues();

        values.put("TEXTO", contato.getTexto());

        conn.insertOrThrow("ANOTACAO", null, values);
    }

    public void alterar(Anotacao contato){
        ContentValues values = new ContentValues();

        values.put("TEXTO", contato.getTexto());

        conn.update("ANOTACAO", values, "_id = ?", new String[]{String.valueOf(contato.getId())});
    }

    public ArrayAdapter<Anotacao> buscaContatos (Context context){

        ArrayAdapter<Anotacao> adpContatos = new ArrayAdapter<Anotacao>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conn.query("ANOTACAO", null, null, null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do {
                Anotacao contato = new Anotacao();

                contato.setId(cursor.getLong(0));
                contato.setTexto(cursor.getString(1));

                adpContatos.add(contato);
            }while (cursor.moveToNext());
        }

        return adpContatos;

    }

}
