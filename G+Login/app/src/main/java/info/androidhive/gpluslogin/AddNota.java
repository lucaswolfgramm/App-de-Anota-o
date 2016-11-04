package info.androidhive.gpluslogin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import info.androidhive.gpluslogin.database.DataBase;
import info.androidhive.gpluslogin.dominio.entidades.Anotacao;
import info.androidhive.gpluslogin.dominio.RepositorioContato;

import java.util.ArrayList;


/**
 * Created by Douglas on 31/08/2016.
 */
public class AddNota extends Activity {


    ArrayList<AddNota> lista = new ArrayList<>();

    private EditText edtAnota;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioContato repositorioContato;
    private Anotacao anotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edtAnota = (EditText) findViewById(R.id.editAnota);

        Button ok = (Button) findViewById(R.id.buttonSalvar);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(AddNota.this);
                alerta.setTitle("Aviso");
                alerta
                        .setIcon(R.mipmap.alerta)
                        .setMessage("Confirma a inclusão do contato?")
                        .setCancelable(false)
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Cancelada a inclusão do contato!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SystemClock.sleep(500);

                                salvar();

                                Toast.makeText(getApplicationContext(), "Inclusão realizada com sucesso! ", Toast.LENGTH_LONG).show();
                                finish();

                            }
                        });
                AlertDialog alertDialog = alerta.create();
                alertDialog.show();


            }
        });

        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey("ANOTACAO"))){
            anotacao = (Anotacao) bundle.getSerializable("ANOTACAO");
            preencheDados();
        }else{
            anotacao = new Anotacao();
        }

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Não criou banco!" + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }
    public void cancelar (View v){

        finish();
    }
    /*public void chamarMenu (View v){
        Intent menu = new Intent(this, TelaMenu.class);
        startActivity(menu);
    }*/

    private void preencheDados(){
        edtAnota.setText(anotacao.getTexto());
    }

    public void salvar(){
        try {

            anotacao = new Anotacao();

            anotacao.setTexto(edtAnota.getText().toString());


            if (anotacao.getId() == 0) {
                repositorioContato.inserir(anotacao);
            }else{
                repositorioContato.alterar(anotacao);
            }
        }catch (Exception ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir os dados!" + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

}
