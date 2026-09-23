/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author ADVAN
 */
public class Penonton extends Pengguna {

    private String password;

    public Penonton(
            String nama,
            String email,
            String password) {

        super(nama, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRole() {
        return "Penonton";
    }

    public boolean login(String email, String password) {

        return getEmail().equals(email)
                && this.password.equals(password);
    }

    @Override
    public String toString() {

        return "Nama  : " + getNama()
                + "\nEmail : " + getEmail()
                + "\nRole  : " + getRole();
    }
}