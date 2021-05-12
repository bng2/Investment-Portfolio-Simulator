import java.util.*;
import java.io.*;
import java.util.Locale;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class Main {
  private static final long serialVersionUID = 4L;
  // Loads and returns the saved User
  public static User load() {
    User u = new User("", 0);
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

  // Saves the User via Java Serializable
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

  // Initializes a new User with a starting balance of $10,000
  public static User setup() {
    return new User("Main", 10000.0);
  }
  
  public static void main(String[] args) throws IOException, ParseException {
    // TODO: Take user input -> Scanner
    // TODO: Finnhub API

    System.out.println("Welcome to the Investment Portfolio Simulator!");

    User mainUser;
    File f = new File("user.ser");    // Create a File object
    if (f.exists()) {                 // Check that the file exists and if it does, load into mainUser
      mainUser = load();
      System.out.println("Your old portfolio has been loaded in.");
    } else {                          // If the file does not exist, initialize mainUser via setup() method
      mainUser = setup();
      System.out.println("You have started a new portfolio with the starting balance of $10,000.");
    }

    // Test Stocks
    
    /*
    Map<String, Stock> listOfStocks = new HashMap<>();
    Stock AAPL = new Stock("AAPL", 132.54, 0.20, 0.002);
    Stock TSLA = new Stock("TLSA", 684.90, 0.00, 0.00);
    Stock AMZN = new Stock("AMZN", 3386.49, 0.00, 0.00);
    listOfStocks.put("AAPL", AAPL);
    listOfStocks.put("TSLA", TSLA);
    listOfStocks.put("AMZN", AMZN);
    */
    

    // Read User Input
    Scanner myObj = new Scanner(System.in);
    int option = 0;
    
    // User Interface
    while (option < 13) { // Selecting any option besides 13 will keep the program running
      System.out.println("Enter the number of the action you would like to perform.");
      System.out.println("1: Check Balance");
      System.out.println("2: Add Balance");
      System.out.println("3: Subtract Balance");
      System.out.println("4: View Portfolio");
      System.out.println("5: View Stock Information");
      System.out.println("6: Buy Stock");
      System.out.println("7: Sell Stock");
      System.out.println("8: Buy Bond");
      System.out.println("9: Sell Bond");
      System.out.println("10: Deposit into Savings");
      System.out.println("11: Withdraw from Savings");
      System.out.println("12: Reset User (Warning: Resets entire portfolio!)");
      System.out.println("13: Exit");
      String symbol = myObj.nextLine();
      option = Integer.parseInt(symbol);
      String ticker = "";
      String amount = "";

      switch (option) {
        case 1: // 1: Check Balance
          mainUser.viewBalance();
          break;
        case 2: // 2: Add Balance
          System.out.println("Please input the amount you would like to add to your balance:");
          amount = myObj.nextLine();
          mainUser.addBalance(Double.parseDouble(amount));
          mainUser.viewBalance();
          break;
        case 3: // 3: Subtract Balance
          System.out.println("Please input the amount you would like to subtract from your balance:");
          amount = myObj.nextLine();
          mainUser.subtractBalance(Double.parseDouble(amount));
          mainUser.viewBalance();
          break;
        case 4: // 4: View Portfolio
          mainUser.viewPortfolio();
          break;
        case 5: // 5: View Stock
          // TODO: Needs to be updated with implementation of Finnhub
          System.out.println("Please input a stock ticker you would like to know more about (ex: AAPL).");
          ticker = myObj.nextLine();
          mainUser.viewStock(ticker);
          // Does listOfStocks contain ticker?
          /*
          if (listOfStocks.containsKey(ticker)) {
            System.out.println(ticker + ": " + listOfStocks.get(ticker));
          }
          else {
            System.out.println("No stock with name: " + ticker);
            break;
          }
          */
          break;
        case 6: // 6: Buy Stock
          // TODO: Fix buying problem
          System.out.println("Please input a stock ticker you would like to buy (ex: AAPL).");
          ticker = myObj.nextLine();

          // Does listOfStocks contain ticker?
          /*
          if (listOfStocks.containsKey(ticker)) {
            System.out.println("Found " + ticker + ": " + listOfStocks.get(ticker));
          }
          else {
            System.out.println("No stock with name: " + ticker);
            break;
          }
          */
          
          System.out.println("Please input the amount you would like to buy.");
          amount = myObj.nextLine();
          mainUser.buyStock(ticker, Double.parseDouble(amount));
          break;
        case 7: // 7: Sell Stock
          // TODO: Fix selling problem
          System.out.println("Please input a stock ticker you would like to sell (ex: AAPL).");
          ticker = myObj.nextLine();
          System.out.println("Please input the amount you would like to sell.");
          amount = myObj.nextLine();
          mainUser.sellStock(ticker, Double.parseDouble(amount));
          //mainUser.sellStock(listOfStocks.get(ticker), Double.parseDouble(amount));
          break;
        case 8: // 8: Buy Bond
          // TODO
          System.out.println("To be implemented");
          System.out.println("Buy Bond Case");
          break;
        case 9: // 9: Sell Bond
          // TODO
          System.out.println("To be implemented");
          System.out.println("Sell Bond Case");
          break;
        case 10: // 10: Deposit
          System.out.println("Please input the amount you would like to deposit into your savings account.");
          amount = myObj.nextLine();
          mainUser.deposit(Double.parseDouble(amount));
          mainUser.viewBalance();
          break;
        case 11: // 11: Withdraw
          System.out.println("Please input the amount you would like to withdraw from your savings account.");
          amount = myObj.nextLine();
          mainUser.withdraw(Double.parseDouble(amount));
          mainUser.viewBalance();
          break;
        case 12: // 12: Reset User
          mainUser = setup();
          System.out.println("Your portfolio have been reset to the initial state.");
          break;
        case 13: // 13: Exit
          save(mainUser);
          System.out.println("Your portfolio has been saved.");
          System.out.println("Thank you for using the Investment Portfolio Simulator!");
          break;
        default:
          System.out.println("Invalid Command");
          break;
      }
      System.out.println("");
    }
  }
}
