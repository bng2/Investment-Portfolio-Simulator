public class Cash implements Instruments {
  //Cash is differnt from balance
  //Cash is the amount of money deposited to gain interest
  double cash;
  double interestRate = 0.07; // 2021 National Average Interst Rate for Savings Accounts

  public Cash(double cash) {
    this.cash = cash;
  }

  public void display() {
    double interestRatePercentage = interestRate * 100;
    System.out.println("The current interest rate is " + interestRatePercentage + "%.");
  }
  
  public void setPrice() {
    // N/A
  }

  public double getPrice() {
    return 0.0;
  }

  public void setName() {
    // N/A
  }

  public String getName() {
    return "";
  }

  @Override
  public String toString() {
    return "(Cash)Total Cash: " + cash; 
  }
}
