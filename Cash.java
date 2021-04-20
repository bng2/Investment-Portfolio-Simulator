public class Cash implements Instruments {
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
}
