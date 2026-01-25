package stockage;

public class CountVisitor extends Visitor {

  private int count = 0;

  public int getCount() {
    return count;
  }

  @Override
  protected void visitDirectory(Directory d) {
    // parcours récursif de l'arbre (Composite)
    for (ElementStockage e : d.elements) {   // elements est protected dans Directory
      e.accept(this);
    }
  }

  @Override
  protected void visitFile(File f) {
    if (f.size() > 10) {   // size() = nombre de caractères du contenu
      count++;
    }
  }

  @Override
  protected void visitLink(Link l) {
    // on ignore (sinon ça devient un graphe et il faut gérer les cycles)
  }

  @Override
  protected void visitSymlink(Symlink symlink) {
    // idem
  }
}
