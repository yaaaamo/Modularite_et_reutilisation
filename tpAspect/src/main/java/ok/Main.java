package ok;

public class Main {
  public static void main(String[] args) { Helloer h = new Helloer();
    Person pierre = new Person("pierre"); Person paul = new Person("paul"); Person jean = new Person("jean"); Place ecole = new Place("ecole"); ecole.add(pierre);
    ecole.add(paul);
    Place theatre = new Place("theatre"); theatre.add(paul);
    theatre.add(jean);
    h.helloTo(pierre); h.helloTo(theatre);}
}
