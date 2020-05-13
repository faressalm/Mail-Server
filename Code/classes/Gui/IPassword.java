package Gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPasswordField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class IPassword extends JPasswordField  {
private String ph;
public IPassword(String ph,int limit) {
this.ph = ph;
super.setDocument(new JTextFieldLimit(limit));
}
public IPassword() {
this.ph = null;
}
/**
* Gets text, returns placeholder if nothing specified
*/

public String getText() {
@SuppressWarnings("deprecation")
String text = super.getText();
if (text.trim().length() == 0 && ph != null) {
text = ph;
}
return text;
}
@SuppressWarnings("deprecation")
@Override
public void paintComponent(Graphics g) {
super.paintComponent(g);
if (super.getText().length() > 0 || ph == null) {
return;
}
Graphics2D g2 = (Graphics2D) g;
g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
g2.setColor(super.getDisabledTextColor());
g2.drawString(ph, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
}
private class JTextFieldLimit extends PlainDocument {
	  private int limit;
	  JTextFieldLimit(int limit) {
	    super();
	    this.limit = limit;
	  }
	  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	    if (str == null)
	      return;

	    if ((getLength() + str.length()) <= limit) {
	      super.insertString(offset, str, attr);
	    }
	  }
	}

}