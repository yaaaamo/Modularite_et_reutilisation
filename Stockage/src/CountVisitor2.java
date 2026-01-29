package stockage;

import java.util.function.Predicate;

public class CountVisitor2 extends Visitor {

  private final Predicate<File> predicate;
  private int count = 0;

  public CountVisitor2(Predicate<File> predicate) {
    this.predicate = predicate;
  }

  public int getCount() {
    return count;
  }

  @Override
  protected void visitDirectory(Directory d) {
    // parcours récursif (Composite)
    for (ElementStockage e : d.elements) {
      e.accept(this);
    }
  }

  @Override
  protected void visitFile(File f) {
    if (predicate.test(f)) {
      count++;
    }
  }

  @Override
  protected void visitLink(Link l) {
    // simple & safe : on ignore (sinon graphe + cycles)
  }

  @Override
  protected void visitSymlink(Symlink symlink) {
    // idem
  }
}

