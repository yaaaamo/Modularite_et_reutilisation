package ok;

public class Child extends Person {
  Child(String n) {
    super(n);
  }
  public String toString() {
    return ("enfant " + super.toString());
  }

}
