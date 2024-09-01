package src.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderPasswordField extends JPasswordField {
    private String placeholder;

    public PlaceholderPasswordField(String placeholder, int columns) {
        super(columns);
        this.placeholder = placeholder;
        setEchoChar((char) 0); // Exibe o placeholder sem ocultar o texto
        setText(placeholder);
        setForeground(Color.black);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(getPassword()).equals(placeholder)) {
                    setText("");
                    setForeground(Color.BLACK);
                    setEchoChar('\u2022'); // Volta a ocultar o texto
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(getPassword()).isEmpty()) {
                    setEchoChar((char) 0); // Remove o echo char para mostrar o placeholder
                    setText(placeholder);
                    setForeground(Color.black);
                }
            }
        });
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
