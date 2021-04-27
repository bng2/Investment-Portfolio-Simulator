public class TreasuryBond implements Instruments {
  String name;
  double price;
  double maturityDate;
  double interestRate;

  public TreasuryBond(double price, double maturityDate, double interestRate) {
    this.price = price;
    this.maturityDate = maturityDate;
    this.interestRate = interestRate;
  }

  public void display() {
    System.out.println("This is a Treasury Bond.");
    System.out.println("Price: "+price+", Maturity Date: "+maturityDate+", Interest Rate: "+interestRate);
  }

  public void setPrice() {
    //N/A
  }
  
  public double getPrice() {
    return price;
  }

  public void setName() {
    //N/A
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "(Treasury Balance)Name: " + name + ",Price: " + price; 
  }
}