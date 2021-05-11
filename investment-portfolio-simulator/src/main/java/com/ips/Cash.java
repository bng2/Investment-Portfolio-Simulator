import java.io.*;

public class Cash implements Instruments {
  //Cash is different from balance
  //Cash is the amount of money deposited to gain interest
  double cash;
  double interestRate = 0.07; // 2021 National Average Interest Rate for Savings Accounts

  public Cash() {
    cash = 0.0;
  }

  public void display() {
    double interestRatePercentage = interestRate * 100;
    System.out.println("The current interest rate is " + interestRatePercentage + "%.");
  }
  
  public void setPrice(double amount) {
    //N/A
  }

  public void addBalance(double amount) {
    cash += amount;
  }

  public void subtractBalance(double amount) {
    cash -= amount;
  }

  public double getPrice() {
    return cash;
  }

  public void setName(String input) {
    //N/A
  }

  public String getName() {
    return "";
  }

  @Override
  public String toString() {
    return "(Cash)Total Cash: " + cash; 
  }

}
