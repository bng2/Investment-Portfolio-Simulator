import java.io.*;

public class TreasuryBond implements Instruments {
  private static final long serialVersionUID = 4L;
  //maturity date
  String name;
  double price;
  double interestRate; // 20 year - 2.20%, 30 year - 2.30%, data obtained from https://fred.stlouisfed.org/series/GS30

  public TreasuryBond(String name, double price) {
    this.name = name;
    this.price = price;
    if(name == "30 year") {
      interestRate = 0.0230;
    }
    else if(name == "20 year") {
      interestRate = 0.0220;
    }
  }

  public void display() {
    double interestRatePercentage = interestRate * 100;
    System.out.println("This is a Treasury Bond.");
    System.out.println("Type: " + name + ", Price: " + price + ", Interest Rate: "+ interestRatePercentage + "%.");
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