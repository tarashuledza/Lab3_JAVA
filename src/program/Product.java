package program;

import java.io.Serializable;

public class Product implements Serializable {
    private Long id;
    private String name;
    private double price;
    private ProductType productType;

    public Product(final Long id, final String name, final double price, final ProductType productType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productType = productType;
    }

    public double getPrice() {
        return price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "program.Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", productType=" + productType +
                '}';
    }
}
