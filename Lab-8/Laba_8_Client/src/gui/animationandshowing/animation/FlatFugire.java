package gui.animationandshowing.animation;

import typesfiles.Flat;

import java.awt.*;
import java.util.Objects;

public class FlatFugire extends Rectangle {
    public int x;
    public int y;
    public int width;
    public int height;
    private Color color;
    private Flat flat;

    public FlatFugire(int x, int y, int width, int height, Color color, Flat flat) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.flat = flat;
    }

    public Color getColor() {
        return color;
    }

    public String getText() {
        return String.valueOf(flat.getId());
    }

    public boolean contains(int X, int Y) {
        if ((x < X) && (X < x + width) && (y < Y) && (Y < y + height)) return true;
        return false;
    }

    public Flat getFlat() {
        return this.flat;
    }

    public void setColor(int r, int g, int b) {
        this.color = new Color(r, g, b);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatFugire rectangle = (FlatFugire) o;
        return flat.getId() == rectangle.getFlat().getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(flat);
    }
}
