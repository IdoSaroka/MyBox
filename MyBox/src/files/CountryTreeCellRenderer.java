package files;

import java.awt.Component;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class CountryTreeCellRenderer implements TreeCellRenderer {
    private JLabel label;

    public CountryTreeCellRenderer() {
        label = new JLabel();
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        Object o = ((DefaultMutableTreeNode) value).getUserObject();
        if (o instanceof Country) {
            Country country = (Country) o;
            URL imageUrl = getClass().getResource(country.getFlagIcon());
            if (imageUrl != null) {
                label.setIcon(new ImageIcon(imageUrl));
            }
            label.setText(country.getName());
        } else {
            label.setIcon(null);
            label.setText("" + value);
        }
        return label;
    }
}