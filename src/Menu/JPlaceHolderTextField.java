package Menu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Florent Lefebvre on 11/10/14.
 */
public class JPlaceHolderTextField extends JTextField {

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty() && !(FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setBackground(Color.gray);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            g2.drawString("Username", MenuTemplate.FIELD_WIDTH, MenuTemplate.FIELD_HEIGHT);
            g2.dispose();
        }
    }
}