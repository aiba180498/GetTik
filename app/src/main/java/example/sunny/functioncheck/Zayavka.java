package example.sunny.functioncheck;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;

public class Zayavka //implements Serializable
{
    private String date, nomer, zakazchik, otpravitel, poluchatel, upakovka, povrezhden;
    private int ves, volume, kolichestvo;

    public Zayavka(String data, String nomer) {
        this.date = data;
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

    public String getZakazchik() {
        return zakazchik;
    }

    public void setZakazchik(String zakazchik) {
        this.zakazchik = zakazchik;
    }

    public String getOtpravitel() {
        return otpravitel;
    }

    public void setOtpravitel(String otpravitel) {
        this.otpravitel = otpravitel;
    }

    public String getPoluchatel() {
        return poluchatel;
    }

    public void setPoluchatel(String poluchatel) {
        this.poluchatel = poluchatel;
    }

    public String getUpakovka() {
        return upakovka;
    }

    public void setUpakovka(String upakovka) {
        this.upakovka = upakovka;
    }

    public String getPovrezhden() {
        return povrezhden;
    }

    public void setPovrezhden(String povrezhden) {
        this.povrezhden = povrezhden;
    }

    public int getVes() {
        return ves;
    }

    public void setVes(int ves) {
        this.ves = ves;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getKolichestvo() {
        return kolichestvo;
    }

    public void setKolichestvo(int kolichestvo) {
        this.kolichestvo = kolichestvo;
    }
}