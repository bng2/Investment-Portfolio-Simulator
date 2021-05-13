import java.util.*;
import java.io.*;

public class SecuritiesCollection implements Serializable {
  private static final long serialVersionUID = 4L;
  public Instruments security;
  public double quantity;

  public SecuritiesCollection(Instruments security, double quantity) {
    this.security = security;
    this.quantity = quantity;
  }

  public Instruments getSecurity() {
    return security;
  }

  public double getQuantity() {
    return quantity;
  }

  public void setSecurity(Instruments security) {
    this.security = security;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    if(security instanceof Stock) {
      return security.toString() + "; Quantity: " + quantity;
    }
    return security.toString();
  }
}
