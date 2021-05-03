public class Stock implements Instruments {
  String ticker;
  double price;
  double dividend;
  double yields;

  public Stock(String ticker, double price, double dividend, double yields) {
    this.ticker = ticker;
    this.price = price; 
    this.dividend = dividend;
    this.yields = yields;
  }
  
  public void display() {
    System.out.println("This is a Stocks.");
    System.out.println("Price: "+price+", Dividend: "+dividend+", Yield: "+yields);
  }

  public void setPrice(double amount) {
    price = amount;
  }
  
  public double getPrice() {
    return price;
  }

  public void setName(String input) {
    //N/A
  }

  public String getName() {
    return ticker;
  }

  @Override
  public String toString() {
    return "(Stock)Name: " + ticker + ", Price: " + price; 
  }
}
