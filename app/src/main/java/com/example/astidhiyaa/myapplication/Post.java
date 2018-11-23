package com.example.astidhiyaa.myapplication;


import java.io.Serializable;

public class Post implements Serializable{
    private String judul;
    private String deskripsi;
    private String tanggal;
    private String id;


    public Post(String judul, String deskripsi, String tanggal){
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
    }
    public Post(){

    }
    @Override
    public String toString() {
        return " "+judul+"\n" +
                " "+deskripsi;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getJudul() {
        return judul;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTanggal() {
        return tanggal;
    }
}
