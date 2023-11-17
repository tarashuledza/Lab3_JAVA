package program;

import exceptions.ItemIsNotExistException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Receipt {
    private double price;
    private boolean isPaid;
    private Customer customer;
    private Calendar date;
    private Map<Product, Integer> productList;

    public Receipt(final Customer customer, final Calendar date) {
        this.customer = customer;
        this.productList = new HashMap<>();
        this.date = date;
    }
    public double calculateTotalPrice() {
        return this.productList.entrySet().stream().mapToDouble(p -> p.getKey().getPrice() * p.getValue()).sum();
    }
    public Map<Product, Integer> getProductList() {
        return productList;
    }

    public double getPrice() {
        return price;
    }

    public Calendar getDate() {
        return date;
    }

    public void pay() {
        this.isPaid = true;
    }

    public void addProduct(final Product product, final Integer count) throws ItemIsNotExistException {
        if(Market.getProductList().keySet().stream().anyMatch(p -> Objects.equals(p.getName(), product.getName())) && !this.isPaid) {
            this.productList.put(product, count);
            Product bag = new Product(3L, "Package", 0.10, ProductType.GOODS);
            switch (product.getProductType()) {
                case FRUITS, VEGETABLES -> productList.put(bag, 1);
            }
        }else {
                throw new ItemIsNotExistException();
        }
    }

    public String createReceipt() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.customer.getFirstName()).append(' ');
        sb.append(this.customer.getLastName()).append('\n');
        sb.append("program.Receipt list:\n");

        this.productList.forEach((key, value) -> {
            sb.append(key.getName()).append('\n');
            sb.append(value).append(" x ").append(key.getPrice())
                    .append("\t | \t").append(value * key.getPrice()).append("\n");
            sb.append("__________________________________\n");
        });
        sb.append("Total price: ").append('\t').append(calculateTotalPrice()).append('\n');
        sb.append("__________________________________\n");

        boolean hasMeatOrFish = this.productList.keySet().stream()
                .anyMatch(p -> p.getProductType() == ProductType.MEAT || p.getProductType() == ProductType.FISH);

        if (hasMeatOrFish) {
            sb.append("Don't forget to store ");
            this.productList.keySet().forEach(p -> {
                if (p.getProductType() == ProductType.MEAT || p.getProductType() == ProductType.FISH) {
                    sb.append(p.getName()).append(", ");
                }
            });
            sb.append("in the refrigerator.\n");
        }

        sb.append(this.date.getTime());

        return sb.toString();
    }


}
