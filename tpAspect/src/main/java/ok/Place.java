package ok;

import java.util.ArrayList;
import java.util.List;

public class Place {
  String name;
  List<Person> l = new ArrayList<Person>(); Place(String n){name = n;}
  public void add(Person p) {l.add(p);}
  public String toString() {
    String s = name + ": "; for (Person p: l){
      s += p.toString();
      s += ", ";} return(s);}
}
