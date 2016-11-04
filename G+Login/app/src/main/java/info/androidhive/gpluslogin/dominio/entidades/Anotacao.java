package info.androidhive.gpluslogin.dominio.entidades;

import java.io.Serializable;

/**
 * Created by Douglas on 05/10/2016.
 */
public class Anotacao implements Serializable{

    private long id;
    private String texto;


    public Anotacao(){
        id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString(){
        return texto;



    }
}
