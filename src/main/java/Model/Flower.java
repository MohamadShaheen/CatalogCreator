package Model;

import javax.persistence.*;


@Entity
@Table(name = "flowers_list")
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Image_Source")
    private String imgSource;
    @Column(name = "Name")
    private String name;
    @Column(name = "Price")
    private double price;
    @Column(name = "Details")
    private String details;
    @Column(name = "Size")
    private String size;
    @Column(name = "Color")
    private String color;

    public Flower() {}

    public Flower(String imgSource, String name, double price, String details, String size, String color) {
        this.imgSource = imgSource;
        this.name = name;
        this.price = price;
        this.details = details;
        this.size = size;
        this.color = color;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
