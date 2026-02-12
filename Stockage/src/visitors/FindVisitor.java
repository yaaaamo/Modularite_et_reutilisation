package stockage.visitors;



import stockage.*;

import java.util.ArrayList;
import java.util.Collection;

public class FindVisitor extends Visitor {

  private String targetName;
  private final Collection<String> results = new ArrayList<>();

  /**
   * Lance la recherche (sur l'élément sur lequel tu appelles accept)
   * et renvoie la collection des adresses absolues.
   */
  public Collection<String> find(String name) {
    this.targetName = name;
    this.results.clear();
    return results;
  }

  public Collection<String> getResults() { return results; }


  // Petit helper
  private void collectIfMatch(ElementStockage e) {
    if (e.getName().equals(targetName)) {          // getName() existe
      results.add(e.absoluteAdress());             // absoluteAdress() existe
    }
  }

  @Override
  protected void visitDirectory(Directory d) {
    collectIfMatch(d);

    // transitivité = descendre récursivement
    for (ElementStockage e : d.elements) {         // elements est protected
      e.accept(this);                              // double-dispatch
    }
  }

  @Override
  protected void visitFile(File f) {
    collectIfMatch(f);
  }

  @Override
  protected void visitLink(Link l) {
    collectIfMatch(l);
    // Option simple : ne pas suivre la référence (sinon graphe + cycles)
    // l.reference.accept(this);  // <- possible mais à sécuriser
  }

  @Override
  protected void visitSymlink(Symlink symlink) {
    collectIfMatch(symlink);
    // Symlink.reference est private et pas de getter -> on ne peut pas la suivre ici
  }
}

