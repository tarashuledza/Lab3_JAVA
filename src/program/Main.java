package program;

import java.io.IOException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, Exception {
        Market market = new Market();

        Product apple = new Product(1L, "Apple", 1.0, ProductType.FRUITS);
        Product banana = new Product(2L, "Banana", 0.5, ProductType.FRUITS);

        market.addProduct(apple, 100);
        market.addProduct(banana, 50);

        Customer customer = new Customer("Taras", "Huledza");

        Receipt receipt = new Receipt(customer, Calendar.getInstance());
        receipt.addProduct(apple, 5);
        receipt.addProduct(banana, 3);

        receipt.pay();

        customer.addReceipt(receipt);

        Receipt anotherReceipt = new Receipt(customer, Calendar.getInstance());
        anotherReceipt.addProduct(apple, 2);

        anotherReceipt.pay();

        customer.addReceipt(anotherReceipt);

        Customer anotherCustomer = new Customer("Yarik", "Teslitskiy");
        Receipt thirdReceipt = new Receipt(anotherCustomer, Calendar.getInstance());
        thirdReceipt.addProduct(banana, 10);
        thirdReceipt.addProduct(apple, 3);

        thirdReceipt.pay();

        anotherCustomer.addReceipt(thirdReceipt);

        System.out.println("Total Product Quantities: " + customer.getTotalProductQuantities());

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_MONTH, -7);

        double totalCost = customer.costBetweenDates(startDate, endDate);
        System.out.println("Total Cost between dates: " + totalCost);

        market.printProductList();

        market.printReceipts();

        System.out.println("Average Product Price: " + market.getAveragePrice());

        System.out.println("Most Popular Product: " + market.mostPopularProduct());

        market.biggestIncomeByDay();
    }
}