public class Stock implements Instruments {
  String name;
  double price;
  double dividend;
  double yields;

  public Stock(double price, double dividend, double yields) {
    this.price = price;
    this.dividend = dividend;
    this.yields = yields;
  }
  
  public void display() {
    System.out.println("This is a Stocks.");
    System.out.println("Price: "+price+", Dividend: "+dividend+", Yield: "+yields);
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
    return "(Stock)Name: " + name + ",Price: " + price; 
  }
}
