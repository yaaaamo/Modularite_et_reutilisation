package stockage.visitors;

import stockage.*;

public class RazVisitor extends Visitor {
  @Override
  protected void visitDirectory(Directory d) {
    // Parcours récursif : on visite tous les enfants
    for (ElementStockage e : d.elements) {
      e.accept(this); // double-dispatch : appelle la bonne méthode visitX(...)
    }
  }

  @Override
  protected void visitFile(File f) {
    // Remettre le fichier à "vide" => size() devient 0
    f.setContents("");
  }

  @Override
  protected void visitLink(Link l) {
    // Rien à faire : un lien n'a pas de contenu à vider.
    // (Optionnel : suivre la référence, mais attention aux cycles)
  }

  @Override
  protected void visitSymlink(Symlink symlink) {
    // Rien à faire : idem.
    // En plus, sa référence est private dans Symlink (pas de getter), donc on ne peut pas la suivre ici.
  }
}
