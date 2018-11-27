package com.example.astidhiyaa.myapplication;
public class User {
    private String nama;
    private String pass;

    public User(String nama, String pass){
        this.nama = nama;
        this.pass =pass;
    }
    public User(){

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
