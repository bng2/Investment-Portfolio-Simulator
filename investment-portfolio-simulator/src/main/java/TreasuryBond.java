import java.io.*;

public class TreasuryBond implements Instruments {
  private static final long serialVersionUID = 4L;
  String name;
  double price;
  double interestRate = 0.161; //March 2021 10 years government bond interest rate
  String maturityDate;

  public TreasuryBond(String name, double price, String maturityDate, double interestRate) {
    this.name = name;
    this.price = price;
    this.maturityDate = maturityDate;
    this.interestRate = interestRate;
  }

  public void display() {
    double interestRatePercentage = interestRate * 100;
    System.out.println("This is a Treasury Bond.");
    System.out.println("Price: "+price+", Maturity Date: "+maturityDate+", Interest Rate: "+interestRatePercentage + "%.");
  }

  public void setPrice(double amount) {
    price = amount;
  }
  
  public double getPrice() {
    return price;
  }

  public void setName(String input) {
    name = input;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "(Treasury Bond)Name: " + name + ", Price: " + price; 
  }
}