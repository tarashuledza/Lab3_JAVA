package program;

import java.util.*;
import java.util.stream.Collectors;

public class Customer {
    private String firstName;
    private String lastName;
    private List<Receipt> receiptList = new ArrayList<>();
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void addReceipt(final Receipt receipt) {
        this.receiptList.add(receipt);
    }

    public double costBetweenDates(final Calendar startDate, final Calendar endDate) {
        Date start = startDate.getTime();
        Date end = endDate.getTime();
        return receiptList.stream().filter(receipt  -> {
            Date receiptDate = receipt.getDate().getTime();
            return receiptDate.after(start) && receiptDate.before(end);
        }).mapToDouble(Receipt::getPrice).sum();
    }

    public Map<Product, Integer> getTotalProductQuantities() {
        return this.receiptList.stream()
                .flatMap(receipt -> receipt.getProductList().entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum
                ));
    }


    public void displayReceipts() {
        receiptList.forEach(System.out::println);
    }

}
