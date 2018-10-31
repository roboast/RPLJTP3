package com.example.astidhiyaa.myapplication;



public class Post {
    private String judul;
    private String deskripsi;
    private String id;

    public Post(String judul, String deskripsi){
        this.judul = judul;
        this.deskripsi = deskripsi;
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
}
