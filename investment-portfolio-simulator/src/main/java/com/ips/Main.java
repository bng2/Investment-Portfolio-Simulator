import java.util.*;
import java.io.*;
import java.util.Locale;

public class Main {

  public static User load() {
    User u = new User("",0);
    try {
      final FileInputStream fileInputStream1 = new FileInputStream(new File("user.ser"));
      final ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);
      u = (User) objectInputStream1.readObject();
      fileInputStream1.close();
      objectInputStream1.close();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return u;
  }

  public static void save(User input) {
    try {
      final FileOutputStream fileOutputStream1 = new FileOutputStream(new File("user.ser"));
      final ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
      objectOutputStream1.writeObject(input);
      fileOutputStream1.close();
      objectOutputStream1.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  //
  public static User setup() {
    return new User("Main", 10000.0);
  }
  
  public static void main(String[] args) {
    /*
    User test = new User("John", 10000.0);
    User user1 = load();
    */

    // TODO: Take user input -> Scanner
    // TODO: Finnhub API

    User mainUser;
    File f = new File("user.ser");
    if (f.exists()) {
      mainUser = load();
    }
    else {
      mainUser = setup();
    }

    Scanner myObj = new Scanner(System.in);
    int option = 0;

    while(option < 12) {
      System.out.println("1: Check Balance");
      System.out.println("2: Add Balance");
      System.out.println("3: View Portfolio");
      System.out.println("4: View Stock");
      System.out.println("5: Buy Stock");
      System.out.println("6: Sell Stock");
      System.out.println("7: Buy Bond");
      System.out.println("8: Sell Bond");
      System.out.println("9: Deposit");
      System.out.println("10: Withdraw");
      System.out.println("11: Reset User (Warning: sets all holdings to default!)");
      System.out.println("12: Exit");
      String symbol = myObj.nextLine();
      option = Integer.parseInt(symbol);
      String ticker = "";
      String amount = "";
      
      switch (option) {
        case 1: // 1: Check Balance
            System.out.println(mainUser.viewBalance());
            break;
        case 3: // 3: View Portfolio
            System.out.println(mainUser.viewPortfolio());
            break;
        case 5: // 5: Buy Stock
            System.out.println("Please input a stock ticker you would like to buy (ex: AAPL)");
            ticker = myObj.nextLine();
            System.out.println("Please input the amount you would like to buy");
            amount = myObj.nextLine();
            System.out.println(mainUser.buyStock(ticker, Double.parseDouble(amount)));
            break;
        case 6: // 6: Sell Stock
            System.out.println("Please input a stock ticker you would like to sell (ex: AAPL)");
            ticker = myObj.nextLine();
            System.out.println("Please input the amount you would like to sell");
            amount = myObj.nextLine();
            System.out.println(mainUser.sellStock(ticker, Double.parseDouble(amount)));
            break;
        case 7: // 7: Buy Bond"
            System.out.println("Please input a stock ticker you would like to buy (ex: AAPL)");
            ticker = myObj.nextLine();
            System.out.println("Please input the amount you would like to buy");
            amount = myObj.nextLine();
            System.out.println(mainUser.buyBond(ticker, Integer.parseInt(amount)));
            break;
        case 8: // 8: Sell Bond
            System.out.println("Please input a stock ticker you would like to sell (ex: AAPL)");
            ticker = myObj.nextLine();
            System.out.println("Please input the amount you would like to sell");
            amount = myObj.nextLine();
            System.out.println(mainUser.sellBond(ticker, Integer.parseInt(amount)));
            break;
        case 12: // 12: Exit
            System.out.println("Thank you for using the Investment Portfolio Simulator!");
            System.out.println("Thank you for using the Investment Portfolio Simulator!");
            break;
            
      }
    }
    /*
    mainUser.viewBalance();
    mainUser.viewPortfolio();
    //mainUser.addBalance(500.0);

    save(mainUser);
    */

    /*
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
    */
  }
}
