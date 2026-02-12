package stockage.visitors;

import stockage.*;

public class CountVisitor extends Visitor {

  private int count = 0;

  public int getCount() {
    return count;
  }



  protected void visitDirectory(Directory d) {

  }

  protected void visitFile(File f) {
    if (f.size() > 10) {   // size() = nombre de caractères du contenu
      count++;
    }
  }


  protected void visitLink(Link l) {

  }


  protected void visitSymlink(Symlink symlink) {

  }


}
