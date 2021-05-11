import java.util.*;
import java.lang.Math;
import java.io.*;
import java.util.Locale;

public class User implements Serializable {
  Map<Instruments, Double> portfolio = new HashMap<>();
  double balance;
  String name;
  Cash account = new Cash();
    
  public User(String name, double balance) {
    this.name = name;
    this.balance = balance;
    portfolio.put(account, account.getPrice());
  }
    
  public void viewBalance() {
    double roundOff = Math.round(balance * 100.0) / 100.0;
    System.out.println("Balance: $" + roundOff);
  }

  public double addBalance(double value) {
    balance = balance + value;
    return balance;
  }

  public double subtractBalance(double value) {
    balance = balance - value;
    return balance;
  }

  public void buyStock(Stock input, double quantity) {
    if ((input.getPrice() * quantity) <= balance) {
      double count = 0;

      if (portfolio.get(input) != null) {
        count = portfolio.get(input);
      }

      portfolio.put(input, count + quantity);
      subtractBalance(input.getPrice() * quantity);
      
      System.out.println("You just purchased " + quantity + " of " + input.getName() + " stock for $" + input.getPrice() * quantity + ".");
    }
    else {
      System.out.println("You do not have enough money to purchase this amount of stock.");
    }
  }

  public void sellStock(Stock input, double quantity) {
    if (portfolio.get(input) != null) {
      if (quantity <= portfolio.get(input)) {
        double count = portfolio.get(input);

        portfolio.put(input, count - quantity);
        addBalance(input.getPrice() * quantity);

        System.out.println("You just sold " + quantity + " of " + input.getName() + " stock for $" + input.getPrice() * quantity + ".");
      }
      else {
        System.out.println("You do not have the specicifed number of stocks to sell.");
      }
    }
    else {
      System.out.println("Your portfolio does not contain this stock.");
    }
  }

  public void buyBond(TreasuryBond input, double quantity) {
    if ((input.getPrice() * quantity) <= balance) {
      double count = 0;

      if (portfolio.get(input) != null) {
        count = portfolio.get(input);
      }

      portfolio.put(input, count + quantity);
      subtractBalance(input.getPrice() * quantity);

      System.out.println("You just purchased " + quantity + " of " + input.getName() + " bond for $" + input.getPrice() * quantity + ".");
    }
    else {
      System.out.println("You do not have enough money to purchase this amount of bond.");
    }
  }

  public void sellBond(TreasuryBond input, double quantity) {
    if (portfolio.get(input) != null) {
      if (quantity <= portfolio.get(input)) {
        double count = portfolio.get(input);

        portfolio.put(input, count - quantity);
        addBalance(input.getPrice() * quantity);

        System.out.println("You just sold " + quantity + " of " + input.getName() + " bond for $" + (input.getPrice() * quantity) + ".");
      }
      else {
        System.out.println("You do not have the specified number of bonds to sell.");
      }
    }
    else {
      System.out.println("Your portfolio does not contain this bond.");
    }
  }

  public void deposit(double amount) {
    if (amount <= balance) {
      account.addBalance(amount);
      portfolio.put(account, account.getPrice());

      System.out.println("You just deposited $" + amount + " into your savings account.");
    }
    else {
      System.out.println("Your account does not have enough to deposit.");
    }
  }

  public void withdraw(double amount) {
    if (amount <= account.getPrice()) {
      account.subtractBalance(amount);
      portfolio.put(account, account.getPrice());

      System.out.println("You just withdrew $" + amount + " from your savings account.");
    }
    else {
      System.out.println("Your account does not have enough to withdraw.");
    }
  }

  public void totalPortfolioValue() {
    double total = 0;
    for(Instruments a : portfolio.keySet()) {
      if(a instanceof Cash) {
        total += a.getPrice();
      }
      else {
        total += a.getPrice() * portfolio.get(a);
      }
    }
    System.out.println("Total Portfolio Value: $" + total);
  }

  public void viewPortfolio() {
    String output = "\nPortfolio:\n";
    //portfolio.forEach((key, value) -> {output += (key + ", Amount = " + value + "\n");});
    for(Instruments a : portfolio.keySet()) {
      if(a instanceof Cash) {
        output += a + "\n";
      }
      else {
        output += (a + ", Amount = " + portfolio.get(a) + "\n");
      }
    }
    System.out.println(output);
  }
}