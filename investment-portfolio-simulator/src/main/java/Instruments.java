import java.io.*;

public interface Instruments extends Serializable {
  public void display();
  public void setPrice(double amount);
  public double getPrice();
  public void setName(String input);
  public String getName();
}