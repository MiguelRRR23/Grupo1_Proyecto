package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

/**
 * Borde redondeado personalizado para los botones.
 * Permite definir un radio de curvatura y un color de borde.
 * Se utiliza en las ventanas para mejorar la estética de los controles.
 */
public class RoundedBorder implements Border {
    private int radius;
    private Color borderColor;

    /**
     * Construye un borde redondeado.
     *
     * @param radius      radio de las esquinas redondeadas
     * @param borderColor color del borde
     */
    public RoundedBorder(int radius, Color borderColor) {
        this.radius = radius;
        this.borderColor = borderColor;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(borderColor);
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}