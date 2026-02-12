package stockage;


import stockage.visitors.*;

import java.util.Collection;

public class Principale
{
  public static void main(String[] args)
  {
		/*Directory racine = new Directory("racine") ;
		Directory d1 = new Directory("JAVA") ;
		Directory d2 = new Directory("src") ;
		
		File f1 = new File("File.java","classe File") ;
		File f2 = new File("Principale.java","classe Principale") ;
		
		Symlink sl = new Symlink("mon_lien_1",f2) ;
		Symlink s2 = new Symlink("JAVA") ;
		
		racine.add(d1) ;
		d1.add(d2) ;
		d2.add(f1) ;
		d2.add(f2) ;
		d2.add(s2) ;
		racine.add(sl) ;
		
		System.out.println(racine.absoluteAdress()) ;
		System.out.println("ls : ") ;
		racine.ls() ;
		System.out.println("taille : "+racine.size()) ;
		System.out.println("\n") ;
		
		System.out.println(d1.absoluteAdress()) ;
		System.out.println("ls : ") ;
		d1.ls() ;
		System.out.println("taille : "+d1.size()) ;
		System.out.println("\n") ;
		
		System.out.println(d2.absoluteAdress()) ;
		System.out.println("ls : ") ;
		d2.ls() ;
		System.out.println("taille : "+d2.size()) ;
		System.out.println("\n") ;
		
		System.out.println(f1.absoluteAdress()) ;
		System.out.println("cat : ") ;
		f1.cat() ;
		System.out.println("taille : "+f1.size()) ;
		System.out.println("\n") ;
		
		System.out.println(sl.absoluteAdress()) ;
		System.out.println("cat : ") ;
		sl.cat() ;
		System.out.println("taille : "+sl.size()) ;
		System.out.println("\n") ;
		
		ArrayList<String> rechercheSimple = racine.find("JAVA") ;
		
		System.out.println("resultat de notre recherche simple sur JAVA dans racine : ") ;
		racine.afficheCollection(rechercheSimple) ;
		System.out.println("\n") ;
		
		ArrayList<String> rechercheTransitive = racine.findR("JAVA") ;
		
		System.out.println("resultat de notre recherche transitive sur JAVA dans racine : ") ;
		racine.afficheCollection(rechercheTransitive) ;
		System.out.println("\n") ;
		
		racine.remove(d1) ;
		
		System.out.println(racine.absoluteAdress()) ;
		System.out.println("ls : ") ;
		racine.ls() ;
		System.out.println("taille : "+racine.size()) ;
		System.out.println("\n") ;
		
		*/

    Directory d = new Directory("Pastis") ;
    File f = new File("Martini.class","martini") ;
    File f2 = new File("Ricard","ricard") ;
    Directory d2 = new Directory("Eau") ;
    File f3 = new File("Glaçons.class","glaçons") ;

    d.add(f) ;
    d.add(f2) ;
    d.add(d2) ;
    d2.add(f3) ;

    File f4 = new File("Duval","duval") ;

    System.out.println("AVANT"+"\n") ;
    System.out.println("taille "+d.name+" : "+d.size()) ;
    System.out.println("taille "+d2.name+" : "+d2.size()) ;
    System.out.println("taille "+f.name+" : "+f.size()) ;
    System.out.println("taille "+f2.name+" : "+f2.size()) ;
    System.out.println("taille "+f3.name+" : "+f3.size()) ;
    System.out.println("taille "+f4.name+" : "+f4.size()) ;

    d.ls();
    d2.ls();

    // ==========================================================
    // 1) Test RazVisitor : vider tous les fichiers (dans l'arbre)
    // ==========================================================
    RazVisitor raz = new RazVisitor();
    d.accept(raz);

    System.out.println("\n=== APRES RazVisitor (tout vidé) ===");
    System.out.println("taille " + f.name  + " : " + f.size());
    System.out.println("taille " + f2.name + " : " + f2.size());
    System.out.println("taille " + f3.name + " : " + f3.size());
    System.out.println("taille " + f4.name + " : " + f4.size() + " (non attaché donc inchangé)");

    // On remet un peu de contenu pour les tests suivants
    f.setContents("12345678901");   // 11
    f2.setContents("abc");          // 3
    f3.setContents("0123456789012");// 13

    // ==========================================================
    // 2) Test CountVisitor : nb de fichiers dont size > 10
    // ==========================================================
    CountVisitor cv = new CountVisitor();
    d.accept(cv);
    System.out.println("\n=== CountVisitor (>10) ===");
    System.out.println("count = " + cv.getCount()); // attendu : 2 (Martini.class + Glaçons.class)

    // ==========================================================
    // 3) Test CountVisitor2 (lambda) : ex fichiers suffixe ".class"
    // ==========================================================
    CountVisitor2 cv2 = new CountVisitor2(file -> file.getName().endsWith(".class"));
    d.accept(cv2);
    System.out.println("\n=== CountVisitor2 (suffixe .class) ===");
    System.out.println("count = " + cv2.getCount()); // attendu : 2

    // Autre exemple : fichiers vides
    CountVisitor2 cvEmpty = new CountVisitor2(file -> file.size() == 0);
    d.accept(cvEmpty);
    System.out.println("\n=== CountVisitor2 (vides) ===");
    System.out.println("count = " + cvEmpty.getCount());

    // ==========================================================
    // 4) Test FindVisitor : adresses absolues des elements nommés "Eau"
    // ==========================================================
    FindVisitor fv = new FindVisitor();
    fv.find("Eau");
    d.accept(fv);

    System.out.println("\n=== FindVisitor (name = \"Eau\") ===");
    // si tu as getResults() :
    // Collection<String> res = fv.getResults();
    // sinon, si results est renvoyé directement par findFrom(...) adapte
    Collection<String> res = fv.getResults(); // <-- ajoute ce getter si tu l'as pas
    for (String s : res) {
      System.out.println(s);
    }

    // ==========================================================
    // 5) Test JavaCleanVisitor : supprimer les fichiers ".class"
    // ==========================================================
    System.out.println("\n=== AVANT JavaCleanVisitor ===");
    d.ls();

    JavaCleanVisitor clean = new JavaCleanVisitor();
    d.accept(clean);

    System.out.println("\n=== APRES JavaCleanVisitor (suppression .class) ===");
    d.ls();
    System.out.println("taille Pastis : " + d.size());


  }


}