import java.io.*;

interface Instruments implements Serializable{
  public void display();
  public void setPrice(double amount);
  public double getPrice();
  public void setName(String input);
  public String getName();
}