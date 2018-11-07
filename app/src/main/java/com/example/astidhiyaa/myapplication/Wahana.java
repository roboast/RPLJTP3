package com.example.astidhiyaa.myapplication;

import java.io.Serializable;

public class Wahana implements Serializable{
    private String nama;
    private String deskripsi;
    private String id;

    public Wahana(String nama, String deskripsi){
        this.nama = nama;
        this.deskripsi = deskripsi;
    }
    public Wahana(){

    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

}
