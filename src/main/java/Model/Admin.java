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
public class Admin extends Pengguna {

    private String password;

    public Admin(
            String nama,
            String email,
            String noHP,
            String password) {

        super(nama, email, noHP);
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
        return "Admin";
    }

    public boolean login(String email, String password) {

        return getEmail().equals(email)
                && this.password.equals(password);
    }

    @Override
    public String toString() {

        return "Nama  : " + getNama()
                + "\nEmail : " + getEmail()
                + "\nNo HP : " + getNoHP()
                + "\nRole  : " + getRole();
    }
}