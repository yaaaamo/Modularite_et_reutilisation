package stockage;


import java.util.Iterator;

public class JavaCleanVisitor extends Visitor {

  @Override
  protected void visitDirectory(Directory d) {

    Iterator<ElementStockage> it = d.elements.iterator();

    while (it.hasNext()) {
      ElementStockage e = it.next();

      // Si c'est un fichier .class → suppression
      if (e instanceof File && e.getName().endsWith(".class")) {
        it.remove();   // ✅ suppression SAFE
      }
      // Sinon, on continue le parcours récursif
      else {
        e.accept(this);
      }
    }
  }
  //mauvaise 1 : for-each + remove
  /*@Override
  protected void visitDirectory(Directory d) {
    for (ElementStockage e : d.elements) {
      if (e instanceof File && e.getName().endsWith(".class")) {
        d.elements.remove(e); // ❌ BOUM
      } else {
        e.accept(this);
      }
    }
  }*/

  //mauvaise 2 : supprimer dans visitFile
  /*
  @Override
  protected void visitFile(File f) {
    if (f.getName().endsWith(".class")) {
      // je supprime le fichier
      f.parent.elements.remove(f); // ❌
    }
  }
  */

  //mauvaise 3 : parcours par index (et remove(i))
  /*@Override
  protected void visitDirectory(Directory d) {
    for (int i = 0; i < d.elements.size(); i++) {
      ElementStockage e = d.elements.get(i);

      if (e instanceof File && e.getName().endsWith(".class")) {
        d.elements.remove(i); // ❌ saute des éléments
      } else {
        e.accept(this);
      }
    }
  }
  */




  @Override
  protected void visitFile(File f) {
    // Rien à faire ici
    // La suppression est gérée dans visitDirectory
  }

  @Override
  protected void visitLink(Link l) {
    // ignoré (safe)
  }

  @Override
  protected void visitSymlink(Symlink symlink) {
    // ignoré
  }
}

