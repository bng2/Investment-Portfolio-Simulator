public class Main {
  public static void main(String[] args) {
    //
    User test = new User("John", 10000.0);
    test.viewBalance();
    System.out.println("Adding $500 to our balance");
    test.addBalance(500);
    test.viewBalance();

    Stock AAPL = new Stock("AAPL", 132.54, 0.20, 0.002);
    Stock TSLA = new Stock("TLSA", 684.90, 0.00, 0.00);
    Stock AMZN = new Stock("AMZN", 3386.49, 0.00, 0.00);

    System.out.println("Purchasing 2 shares of AAPL and 1 share of TSLA.");

    test.buyStock(AAPL, 1);
    test.buyStock(TSLA, 1);
    test.buyStock(AAPL, 1);
    test.buyStock(AAPL, 1);
    test.sellStock(AAPL, 1);

    test.viewPortfolio();
    test.viewBalance();
    test.totalPortfolioValue();
    System.out.println("$9550.02 + $949.98 = $10500, which is our starting balance.");
  }
}
