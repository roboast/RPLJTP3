package com.example.astidhiyaa.myapplication;

import java.io.Serializable;

public class Wahana implements Serializable{
    private String nama;
    private String deskripsi;
    private String tanggal;
    private String id;
    private String foto;

    public Wahana(String nama, String deskripsi, String tanggal, String foto){
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
        this.foto = foto;
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

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
