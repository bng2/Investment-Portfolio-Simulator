import java.util.*;
import java.lang.Math;
import java.io.*;
import java.util.Locale;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class User implements Serializable {
  private static final long serialVersionUID = 4L;
  private Map<String, SecuritiesCollection> portfolio = new HashMap<>();
  double balance;
  String name;
  Cash account;
    
  public User(String name, double balance) {
    this.name = name;
    this.balance = balance;
    account = new Cash();
    SecuritiesCollection cashSecurity = new SecuritiesCollection(account, account.getPrice());
    portfolio.put("Cash", cashSecurity);
  }

  public void viewBalance() {
    double roundOff = Math.round(balance * 100.0) / 100.0;
    System.out.println("Balance: $" + roundOff);
  }

  public double addBalance(double value) {
    balance = balance + value;
    return balance;
  }

  public double subtractBalance(double value) {
    balance = balance - value;
    return balance;
  }

  public void viewStock(String input) throws IOException, ParseException {
    Properties props = new Properties();
    InputStream inputStream = User.class.getClassLoader().getResourceAsStream("api.properties");
    if(inputStream != null) {
        props.load(inputStream);
    }
    final String apiKey = props.getProperty("apiKey");

    String apiRequest1 = "https://finnhub.io/api/v1/quote?symbol="+input+"&token="+apiKey;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
      .GET()
      .uri(URI.create(apiRequest1))
      .build();

    try {
      final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(input + ": " + response.body());

      JSONObject object = (JSONObject) new JSONParser().parse(response.body());
      Object object1 = object.get("c");

      double price = Double.parseDouble(object1.toString());

      if (price != 0) {
        System.out.println(input + ": " + object1.toString());
      }
      else {
        System.out.println("This stock does not exist.");
      }

    } catch (IOException | InterruptedException | ParseException e) {
      e.printStackTrace();
    }
  }

  public void buyStock(String input, double quantity) throws IOException, ParseException {
    Properties props = new Properties();
    InputStream inputStream = User.class.getClassLoader().getResourceAsStream("api.properties");
    if(inputStream != null) {
        props.load(inputStream);
    }
    final String apiKey = props.getProperty("apiKey");

    String apiRequest1 = "https://finnhub.io/api/v1/quote?symbol="+input+"&token="+apiKey;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
      .GET()
      .uri(URI.create(apiRequest1))
      .build();

    try {
      final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(input + ": " + response.body());

      JSONObject object = (JSONObject) new JSONParser().parse(response.body());
      Object object1 = object.get("c");
      double price = Double.parseDouble(object1.toString());

      /*
      portfolio.put(hold, quantity);
      System.out.println(object1.toString());
      */
      
      if (price != 0) {
        if (price * quantity <= balance) {
          double count = 0;

          if (portfolio.get(input) != null) {
            count = portfolio.get(input).getQuantity();
          }

          Stock currentStock = new Stock(input, price, 0, 0);
          SecuritiesCollection secCollection = new SecuritiesCollection(currentStock, quantity + count);

          portfolio.put(input, secCollection);
          subtractBalance(price * quantity);
          
          System.out.println("You just purchased " + quantity + " of " + input + " stock for $" + (price * quantity) + ".");
        }
        else {
          System.out.println("You do not have enough money to purchase this amount of stock.");
        }
      }
      else {
        System.out.println("This stock does not exist.");
      }
    } catch (IOException | InterruptedException | ParseException e) {
      e.printStackTrace();
    }

    /*
    if ((input.getPrice() * quantity) <= balance) {
      double count = 0;

      if (portfolio.get(input) != null) {
        count = portfolio.get(input);
      }

      portfolio.put(input, count + quantity);
      subtractBalance(input.getPrice() * quantity);
      
      System.out.println("You just purchased " + quantity + " of " + input.getName() + " stock for $" + input.getPrice() * quantity + ".");
    }
    else {
      System.out.println("You do not have enough money to purchase this amount of stock.");
    }
    */
  }

  public void sellStock(String input, double quantity) throws IOException, ParseException  {
    Properties props = new Properties();
    InputStream inputStream = User.class.getClassLoader().getResourceAsStream("api.properties");
    if(inputStream != null) {
        props.load(inputStream);
    }
    final String apiKey = props.getProperty("apiKey");

    String apiRequest1 = "https://finnhub.io/api/v1/quote?symbol="+input+"&token="+apiKey;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
      .GET()
      .uri(URI.create(apiRequest1))
      .build();
    
    
    try {
      final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(input + ": " + response.body());

      JSONObject object = (JSONObject) new JSONParser().parse(response.body());
      Object object1 = object.get("c");
      double price = Double.parseDouble(object1.toString());

      if (price != 0) {
        if (portfolio.get(input) != null) {
          if (quantity <= portfolio.get(input).getQuantity()) {
            double count = portfolio.get(input).getQuantity();

            Stock currentStock = new Stock(input, price, 0, 0);
            SecuritiesCollection secCollection = new SecuritiesCollection(currentStock, count - quantity);

            portfolio.put(input, secCollection);
            addBalance(price * quantity);

            System.out.println("You just sold " + quantity + " of " + input + " stock for $" + price * quantity + ".");
          }
          else {
            System.out.println("You do not have the specicifed number of stocks to sell.");
          }
        }
        else {
          System.out.println("Your portfolio does not contain this stock.");
        }
      }
      else {
        System.out.println("This stock does not exist.");
      }
    } catch (IOException | InterruptedException | ParseException e) {
      e.printStackTrace();
    }
  }

  // TODO: implement below with real treasury bonds
  /*
  public void buyBond(TreasuryBond input, double quantity) {
    if ((input.getPrice() * quantity) <= balance) {
      double count = 0;

      if (portfolio.get(input) != null) {
        count = portfolio.get(input);
      }

      portfolio.put(input, count + quantity);
      subtractBalance(input.getPrice() * quantity);

      System.out.println("You just purchased " + quantity + " of " + input.getName() + " bond for $" + input.getPrice() * quantity + ".");
    }
    else {
      System.out.println("You do not have enough money to purchase this amount of bond.");
    }
  }

  public void sellBond(TreasuryBond input, double quantity) {
    if (portfolio.get(input) != null) {
      if (quantity <= portfolio.get(input)) {
        double count = portfolio.get(input);

        portfolio.put(input, count - quantity);
        addBalance(input.getPrice() * quantity);

        System.out.println("You just sold " + quantity + " of " + input.getName() + " bond for $" + (input.getPrice() * quantity) + ".");
      }
      else {
        System.out.println("You do not have the specified number of bonds to sell.");
      }
    }
    else {
      System.out.println("Your portfolio does not contain this bond.");
    }
  }
  */

  public void deposit(double amount) {
    if (amount <= balance) {
      balance = balance - amount;
      account.addBalance(amount);
      SecuritiesCollection secCollection = new SecuritiesCollection(account, account.getPrice());
      portfolio.put("Cash", secCollection);

      System.out.println("You just deposited $" + amount + " into your savings account.");
    }
    else {
      System.out.println("Your account does not have enough to deposit.");
    }
  }

  public void withdraw(double amount) {
    if (amount <= account.getPrice()) {
      balance = balance + amount;
      account.subtractBalance(amount);
      SecuritiesCollection secCollection = new SecuritiesCollection(account, account.getPrice());
      portfolio.put("Cash", secCollection);

      System.out.println("You just withdrew $" + amount + " from your savings account.");
    }
    else {
      System.out.println("Your account does not have enough to withdraw.");
    }
  }

  public void totalPortfolioValue() throws IOException, ParseException {
    double total = 0;
    Properties props = new Properties();
    InputStream inputStream = User.class.getClassLoader().getResourceAsStream("api.properties");
    if(inputStream != null) {
        props.load(inputStream);
    }
    final String apiKey = props.getProperty("apiKey");

    try {
      for (String a : portfolio.keySet()) {
        if(portfolio.get(a).getSecurity() instanceof Stock) {
          String apiRequest1 = "https://finnhub.io/api/v1/quote?symbol="+a+"&token="+apiKey;
          HttpClient client = HttpClient.newHttpClient();
          HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(apiRequest1))
            .build();

          final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
          System.out.println(a + ": " + response.body());
          JSONObject object = (JSONObject) new JSONParser().parse(response.body());
          Object object1 = object.get("c");
          double price = Double.parseDouble(object1.toString());

          //updates price of stock TODO: (update wtihout creating new securtity or collection)
          Stock currentStock = new Stock(a, price, 0, 0);
          SecuritiesCollection secCollection = new SecuritiesCollection(currentStock, portfolio.get(a).getQuantity());
          portfolio.put(a, secCollection);

          total += secCollection.getSecurity().getPrice() * secCollection.getQuantity();
        } 
        else if (portfolio.get(a).getSecurity() instanceof Cash){
          total += portfolio.get(a).getSecurity().getPrice();
        }
        else {
          total += portfolio.get(a).getSecurity().getPrice() * portfolio.get(a).getQuantity();
        }
      }
      System.out.println("Total Portfolio Value: $" + total);

    } catch (IOException | InterruptedException | ParseException e) {
      e.printStackTrace();
    }

    /*
    for(Instruments a : portfolio.keySet()) {
      if(a instanceof Cash) {
        total += a.getPrice();
      }
      else {
        total += a.getPrice() * portfolio.get(a);
      }
    }
    System.out.println("Total Portfolio Value: $" + total);
    */
  }

  public void viewPortfolio() throws IOException, ParseException {
    String output = "\nPortfolio:\n";
    Properties props = new Properties();
    InputStream inputStream = User.class.getClassLoader().getResourceAsStream("api.properties");
    if(inputStream != null) {
        props.load(inputStream);
    }
    final String apiKey = props.getProperty("apiKey");

    try {
      for (String a : portfolio.keySet()) {
        if(portfolio.get(a).getSecurity() instanceof Stock) {
          String apiRequest1 = "https://finnhub.io/api/v1/quote?symbol="+a+"&token="+apiKey;
          HttpClient client = HttpClient.newHttpClient();
          HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(apiRequest1))
            .build();

          final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
          System.out.println(a + ": " + response.body());
          JSONObject object = (JSONObject) new JSONParser().parse(response.body());
          Object object1 = object.get("c");
          double price = Double.parseDouble(object1.toString());

          //updates price of stock
          Stock currentStock = new Stock(a, price, 0, 0);
          SecuritiesCollection secCollection = new SecuritiesCollection(currentStock, portfolio.get(a).getQuantity());
          portfolio.put(a , secCollection);

          output += portfolio.get(a).toString() + "\n";
        } 
        else {
          output += portfolio.get(a).toString() + "\n";
        }
      }
      System.out.println(output);

      /*
      String output = "\nPortfolio:\n";
      //portfolio.forEach((key, value) -> {output += (key + ", Amount = " + value + "\n");});
      for(String a : portfolio.keySet()) {
        output += (a + ", Amount = " + portfolio.get(a) + "\n");
      }
      System.out.println(output);
      */

    } catch (IOException | InterruptedException | ParseException e) {
      e.printStackTrace();
    }
  }
}