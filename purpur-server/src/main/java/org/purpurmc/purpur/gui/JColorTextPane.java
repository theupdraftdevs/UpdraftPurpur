package org.purpurmc.purpur.gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
public class JColorTextPane extends JTextPane {
    private static final int MAX_DOCUMENT_CHARACTERS = 1_000_000;
    private static final GUIColor DEFAULT_COLOR;
    static {
        DEFAULT_COLOR = UIManager.getSystemLookAndFeelClassName().equals("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
            ? GUIColor.WHITE : GUIColor.BLACK;
    }


    public void append(String msg) {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(() -> append(msg));
            return;
        }

        // TODO: update to use adventure instead
        BaseComponent[] components = TextComponent.fromLegacyText(DEFAULT_COLOR.getCode() + msg, DEFAULT_COLOR.getChatColor());
        for (BaseComponent component : components) {
            String text = component.toPlainText();
            if (text == null || text.isEmpty()) {
                continue;
            }

            GUIColor guiColor = GUIColor.getColor(component.getColor());
            if (guiColor == null) {
                guiColor = DEFAULT_COLOR;
            }

            StyleContext context = StyleContext.getDefaultStyleContext();
            AttributeSet attr = context.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, guiColor.getColor());
            attr = context.addAttribute(attr, StyleConstants.CharacterConstants.Bold, component.isBold() || guiColor != DEFAULT_COLOR);
            attr = context.addAttribute(attr, StyleConstants.CharacterConstants.Italic, component.isItalic());
            attr = context.addAttribute(attr, StyleConstants.CharacterConstants.Underline, component.isUnderlined());
            attr = context.addAttribute(attr, StyleConstants.CharacterConstants.StrikeThrough, component.isStrikethrough());

            try {
                int pos = getDocument().getLength();
                getDocument().insertString(pos, text, attr);
                trimDocument();
            } catch (BadLocationException ignore) {
            }
        }
    }

    private void trimDocument() throws BadLocationException {
        int excess = getDocument().getLength() - MAX_DOCUMENT_CHARACTERS;
        if (excess <= 0) {
            return;
        }

        String content = getDocument().getText(0, excess);
        int newline = content.lastIndexOf('\n');
        getDocument().remove(0, newline >= 0 ? newline + 1 : excess);
    }
}
