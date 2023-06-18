package es.granel.forn.model;

import java.io.Serializable;
import java.util.Date;

public class Movement implements Serializable {

    private int id;
    private int type;
    private Date date;
    private String description;
    private float amount;
    private Product Productrigin;
    private Product productDestination;

    public Movement(int id, int type, Date date, String description, float amount, Product Productrigin, Product productDestination) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.Productrigin = Productrigin;
        this.productDestination = productDestination;
    }

    public Movement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Product getProductrigin() {
        return Productrigin;
    }

    public void setProductrigin(Product Productrigin) {
        this.Productrigin = Productrigin;
    }

    public Product getProductDestination() {
        return productDestination;
    }

    public void setProductDestination(Product productDestination) {
        this.productDestination = productDestination;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "id=" + id +
                ", type=" + type +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", Productrigin=" + Productrigin +
                ", productDestination=" + productDestination +
                '}';
    }

}
