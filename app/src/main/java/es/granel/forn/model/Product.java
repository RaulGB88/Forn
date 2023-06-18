package es.granel.forn.model;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    private int id;
    private String image;
    private String name;
    private float price;
    private int stock;
    private Client client;
    private List<Movement> listMovement;

    public Product(int id, String name, float price, int stock, Client client, List<Movement> listMovement) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.client = client;
        this.listMovement = listMovement;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Movement> getListMovement() {
        return listMovement;
    }

    public void setListMovement(List<Movement> listMovement) {
        this.listMovement = listMovement;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

}
