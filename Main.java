public class Main {
  public static void main(String[] args) {
    User test = new User("John", 12.00);
    System.out.println(test.viewBalance());
    test.addBalance(13);
    System.out.println(test.viewBalance());
  }
}
