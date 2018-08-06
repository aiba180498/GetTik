package example.sunny.functioncheck;

import java.io.Serializable;

public class Zayavka implements Serializable {
    private String date;
    private String nomer;

    public Zayavka(String date, String nomer) {
        this.date = date;
        this.nomer = nomer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }
}