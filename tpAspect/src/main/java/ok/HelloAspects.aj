package ok;

public aspect HelloAspects {
  /*pointcut toPerson(): call (* Helloer.*(Person)); pointcut toPlace():
  call (* Helloer.*(Place)); before(): toPerson(){
    System.out.println("Appel individuel");}
  before(): toPlace(){
    System.out.println("Appel aux personnes dans un lieu");}
}


void around(): call (∗ Helloer.∗(..)) {
System.out.println("---"); proceed(); System.out.println("---");
}
 */
  pointcut toPerson(Helloer h, Person p):
          target(h) && //h sera li ́ee au receveur
                  args(p) && //p sera li ́ee `a la ‘‘Person’’ argument
                  call (* Helloer.*(Person)); //des appels aux m ́ethodes compatibles
  before(Helloer h, Person p): toPerson(h, p){ System.out.println("Appel individuel `a " + h + " pour " + p); }
 }