package es.granel.forn.model;

import java.io.Serializable;
import java.util.List;

public class Client implements Serializable {

    private int id;
    private String nif;
    private String name;
    private String surname;
    private String password;
    private String email;
    private List<Product> listProduct;

    public Client(int id, String nif, String name, String surname, String password, String email, List<Product> listProduct) {
        this.id = id;
        this.nif = nif;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.listProduct = listProduct;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nif='" + nif + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
