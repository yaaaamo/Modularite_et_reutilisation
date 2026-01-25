package stockage;

public abstract class Visitor {
  protected abstract void visitDirectory(Directory d);
  protected abstract void visitFile(File f);
  protected abstract void visitLink(Link l);
  protected abstract void visitSymlink(Symlink symlink);
}
