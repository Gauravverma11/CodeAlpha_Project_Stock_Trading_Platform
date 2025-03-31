
import java.util.*;
public class Stock_price {

        private static Map<String, Double> stocks = new HashMap<>();
        private static Map<String, Integer> portfolio = new HashMap<>();
        private static List<String> transactions = new ArrayList<>();

        public static void main(String[] args) {
            initializeStocks();
            Scanner sc = new Scanner(System.in);

            while(true) {
                System.out.println("\n1. Show Stocks\n2. Buy\n3. Sell\n4. Portfolio\n5. Exit");
                int choice = sc.nextInt();

                switch(choice) {
                    case 1: showStocks(); break;
                    case 2: trade(sc, "Buy"); break;
                    case 3: trade(sc, "Sell"); break;
                    case 4: showPortfolio(); break;
                    case 5: System.exit(0);
                    default: System.out.println("Invalid choice!");
                }
            }
        }

        private static void initializeStocks() {
            stocks.put("ZOMATO", 150.0);
            stocks.put("BOAT", 750.0);
            stocks.put("JIO", 300.0);
            stocks.put("SBI", 600.0);
        }

        private static void showStocks() {
            System.out.println("\nAvailable Stocks:");
            stocks.forEach((k,v) -> System.out.printf("%-6s ₹%.2f%n", k, v));
        }

        private static void trade(Scanner sc, String type) {
            System.out.print("Enter stock symbol: ");
            String symbol = sc.next().toUpperCase();

            if(!stocks.containsKey(symbol)) {
                System.out.println("Invalid stock!");
                return;
            }

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            double price = stocks.get(symbol);

            if(type.equals("Sell")) {
                if(portfolio.getOrDefault(symbol, 0) < qty) {
                    System.out.println("Not enough shares!");
                    return;
                }
                portfolio.put(symbol, portfolio.get(symbol) - qty);
            } else {
                portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + qty);
            }

            String transaction = String.format("%s | %s | %d @ ₹%.2f",
                    new Date(), type, qty, price);
            transactions.add(transaction);
            System.out.println("Trade successful!");
        }

        private static void showPortfolio() {
            System.out.println("\nYour Holdings:");
            portfolio.forEach((k,v) ->
                    System.out.printf("%-6s : %d shares%n", k, v));

            System.out.println("\nTransaction History:");
            transactions.forEach(System.out::println);
        }
    }

