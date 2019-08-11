package de.bogin.tenjiku.mcts;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

/**
 * Created by Simon M. Lucas
 * sml@essex.ac.uk
 * Date: 29-Nov-2010
 * Time: 22:23:45
 * <p/>
 * Simple TreeView for a MCTS Tree
 */
public class TreeView extends JComponent {
    def root;
    int nw = 50;//original = 30
    int nh = 40;//original = 20
    int inset = 20;
    int minWidth = 300;
    int heightPerLevel = 40;
    Color fg = Color.black;
    Color bg = Color.cyan;
    Color nodeBg = Color.white;
    Color highlighted = Color.red;
    // the highlighted set of nodes...
    HashMap<TreeNode, Color> high;

    public TreeView(def root) {
        this.root = root;
        high = new HashMap<TreeNode, Color>();
    }

    public void paintComponent(Graphics gg) {
        // Font font =
        // g.setFont();
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int y = inset;
        int x = getWidth() / 2;
        g.setColor(bg);
        g.fillRect(0, 0, getWidth(), getHeight());
        draw(g, root, x, y, (int) (1.1 * getWidth()) - inset * 0);
    }

    private void draw(Graphics2D g, def cur, int x, int y, int wFac) {
        // draw this one, then it's children

        int arity = cur.arity();
		cur.children.eachWithIndex{it, i ->
            if (it.nVisits > 0) {
                int xx = (int) ((i + 1.0) * wFac / (arity + 1) + (x - wFac / 2));
                int yy = y + heightPerLevel;
                g.setColor(fg);
                g.drawLine(x, y, xx, yy);
                draw(g, it, xx, yy, (int) wFac/arity );
            }
        }
        drawNode(g, cur, x, y);
    }

    private void drawNode(Graphics2D g, def node, int x, int y) {
        String s = node.totValue + "/" + node.nVisits + "/n" + "$node.position.lastMove";
        g.setColor(nodeBg);
        // if (high.contains(node)) g.setColor(highlighted);
        g.fillOval((int) x - nw / 2, (int) y - nh / 2, nw, nh);
        g.setColor(fg);
        g.drawOval((int) x - nw / 2, (int) y - nh / 2, nw, nh);
        g.setColor(fg);
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D rect = fm.getStringBounds(s, g);
        g.drawString(s, x - (int) (rect.getWidth() / 2), (int) (y + rect.getHeight() / 2));
    }

    public Dimension getPreferredSize() {
        // should make this depend on the tree ...
        return new Dimension(minWidth, heightPerLevel * (10 - 1) + inset * 2);
    }

    public TreeView showTree(String title) {
        new JEasyFrame(this, title);
        return this;
    }
}