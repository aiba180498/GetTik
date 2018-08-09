package example.sunny.functioncheck.Models;

public class DummyModel {

    private String id;
    private String title;
    private String created_at;
    private String zakazchik;
    private String Otpravitel;
    private String Poluchatel;
    private int kolichestvo;
    private int ves;
    private int obem;
    private String upakovka;
    private String brak;

    public DummyModel(){

    }


    public DummyModel(String id, String title, String created_at, String zakazchik, String otpravitel, String poluchatel, int kolichestvo, int ves, int obem, String upakovka, String brak) {
        this.id = id;
        this.title = title;
        this.created_at = created_at;
        this.zakazchik = zakazchik;
        Otpravitel = otpravitel;
        Poluchatel = poluchatel;
        this.kolichestvo = kolichestvo;
        this.ves = ves;
        this.obem = obem;
        this.upakovka = upakovka;
        this.brak = brak;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getZakazchik() {
        return zakazchik;
    }

    public void setZakazchik(String zakazchik) {
        this.zakazchik = zakazchik;
    }

    public String getOtpravitel() {
        return Otpravitel;
    }

    public void setOtpravitel(String otpravitel) {
        Otpravitel = otpravitel;
    }

    public String getPoluchatel() {
        return Poluchatel;
    }

    public void setPoluchatel(String poluchatel) {
        Poluchatel = poluchatel;
    }

    public int getKolichestvo() {
        return kolichestvo;
    }

    public void setKolichestvo(int kolichestvo) {
        this.kolichestvo = kolichestvo;
    }

    public int getVes() {
        return ves;
    }

    public void setVes(int ves) {
        this.ves = ves;
    }

    public int getObem() {
        return obem;
    }

    public void setObem(int obem) {
        this.obem = obem;
    }

    public String getUpakovka() {
        return upakovka;
    }

    public void setUpakovka(String upakovka) {
        this.upakovka = upakovka;
    }

    public String getBrak() {
        return brak;
    }

    public void setBrak(String brak) {
        this.brak = brak;
    }
}
