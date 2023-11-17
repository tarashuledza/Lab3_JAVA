package program;

import java.io.IOException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, Exception {
      Map<Product, Integer> productList = new HashMap<>();
        Product product = new Product(1L, "Bananas", 500, ProductType.FRUITS);
        Product product2 = new Product(2L, "Chicken", 300, ProductType.MEAT);
        Product product3 = new Product(3L, "Package", 0.1, ProductType.GOODS);
       productList.put(product, 5);
       productList.put(product2, 5);
       productList.put(product3, 10);
       FileService.writeObjectToFile("Products.txt", productList);

        Market market = new Market();
       // market.addProduct(new program.Product(2L, "yuri2", 200, program.ProductType.MEAT), 6);

        System.out.println(market.getAveragePrice());
        System.out.println(FileService.readObjectAsMap("Products.txt"));
        market.sortProductsByPrice(100, 350);

        Calendar date = new GregorianCalendar(2023, Calendar.MARCH,25);
        Calendar date2 = new GregorianCalendar(2023, Calendar.MARCH,28);

        Customer customer = new Customer("Taras", "Huledza");
        Receipt receipt = new Receipt(customer, date);
        Receipt receipt1 = new Receipt(customer, date2);



        receipt.addProduct(product, 2);
     System.out.println(receipt.createReceipt());
       // receipt.addProduct(product2, 3);
        //receipt1.addProduct(product2, 2);
        //System.out.println(receipt.createReceipt());
        //program.FileService.writeTextToFile(receipt.createReceipt(), "program.Receipt.txt");
//market.printProductList();
        //market.createReceipt(receipt);
        //customer.displayReceipts();
        //customer.getTotalProductQuantities().forEach((key, value) -> System.out.println(key.getName() + ": " + value));
        //System.out.println(market.mostPopularProduct());
        //market.printReceipts();
        //market.biggestIncomeByDay();
        Product product1 = new Product(5L, "Bohdan", 12, ProductType.GOODS);
       // market.addProduct(product1, 4);
       // market.printProductList();
//        market.addProduct(product1, 1);
//        market.addProduct(product1, 2);
        market.createReceipt(receipt);
        market.createReceipt(receipt1);
        market.mostPopularProduct();
    }
}