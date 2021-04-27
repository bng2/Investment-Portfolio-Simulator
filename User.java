import java.util.*;

public class User {
  List<Instruments> portfolio = new ArrayList<>();
  double balance;
  String name;
    
  public User(String name, double balance) {
    this.name = name;
    this.balance = balance;
  }
    
  public double viewBalance() {
    return balance;
  }

  public double addBalance(double num) {
    balance = balance + num;
    return balance;
  }

  public double subtractBalance(double num) {
    balance = balance - num;
    return balance;
  }

  public List<Instruments> viewPortfolio() {
    return portfolio;
  }
}