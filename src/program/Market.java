package program;

import exceptions.DuplicateItemException;
import exceptions.ItemIsNotExistException;
import exceptions.ObjectIsEmptyException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

    public class Market {
        private static Map<Product, Integer> productList;
        private List<Receipt> receiptsHistory = new ArrayList<>();
        public Market() throws ClassNotFoundException {
            this.productList = FileService.readObjectAsMap("Products.txt");
        }

        public void createReceipt(final Receipt receipt) throws DuplicateItemException, IOException {
                productList.forEach((product1, count1) ->{
                    receipt.getProductList().forEach((product2, count2)->{
                    if(!product1.equals(product2)) {
                        throw new DuplicateItemException();
                    }

                });
            });
            this.receiptsHistory.add(receipt);
            updateReceiptsHistory();
        }
        public void addProduct(final Product product, final int count) throws Exception {
            if (productList.keySet().stream().anyMatch(p -> Objects.equals(p.getName(), product.getName()))) {
                throw new DuplicateItemException();
            }
            productList.put(product, count);
            FileService.writeObjectToFile("Products.txt", productList);
        }

        public static Map<Product, Integer> getProductList() {
            return productList;
        }

        public void printProductList() {
            productList.forEach((key, value) -> System.out.println(key.getName() + ": " + value));
        }
        public void printReceipts() throws ObjectIsEmptyException {
            if(!receiptsHistory.isEmpty()) {
                receiptsHistory.forEach(Receipt::createReceipt);
            } else {
               throw new ObjectIsEmptyException();
            }
        }
        public void editProduct(final Product product, final int count) throws ItemIsNotExistException {
            if (productList.keySet().stream().anyMatch(p -> Objects.equals(p.getName(), product.getName()))) {
                throw new ItemIsNotExistException("This product doesn't exist!");
            }
            productList.put(product, count);
        }
        public double getAveragePrice() {
            return productList.keySet().stream().mapToDouble(Product::getPrice).average().orElse(0.0);
        }
        public void sortProductsByPrice(final int minPrice, final int maxPrice) {
            productList.keySet()
                    .stream().filter(p -> minPrice < p.getPrice() && maxPrice > p.getPrice())
                    .sorted(Comparator.comparingDouble(Product::getPrice)).forEach(System.out::println);
        }
        public Map<Product, Integer> mostPopularProduct() {
            return this.receiptsHistory.stream()
                    .flatMap(receipt -> receipt.getProductList().entrySet().stream())
                    .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            Integer::sum
                    ));
        }

        public void biggestIncomeByDay() throws ItemIsNotExistException {
            Map<Calendar, Double> dailyIncomes = new HashMap<>();
            this.receiptsHistory.forEach(r -> {
                Calendar receiptDate = r.getDate();
                double income = r.calculateTotalPrice();
                dailyIncomes.put(receiptDate, dailyIncomes.getOrDefault(receiptDate, 0.0) + income);
            });

            Map<Calendar, Double> maxIncomeDays = new HashMap<>();

            final double[] maxIncome = {0.0};
            dailyIncomes.forEach((key, value) -> {
                if (value > maxIncome[0]) {
                    maxIncome[0] = value;
                    maxIncomeDays.clear();
                    maxIncomeDays.put(key, value);
                } else if (value == maxIncome[0]) {
                    maxIncomeDays.put(key, value);
                }
            });

            if(!maxIncomeDays.isEmpty()) {
                System.out.println("Days with biggest income:");
                maxIncomeDays.forEach((date, income) -> {
                    System.out.println(date.getTime() + " with income: " + income);
                });
            } else {
                throw new ItemIsNotExistException("Tickets doesn't exist");
            }
        }
        private void updateReceiptsHistory() throws IOException {
                FileService.writeObjectToFile("ReceiptHistory.txt", this.receiptsHistory);
        }
    }
