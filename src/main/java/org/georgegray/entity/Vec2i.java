package org.georgegray.entity;

import java.util.Objects;

/**
 * A 2 dimensional integer Vector defining a point in space or direction
 */
public class Vec2i {

    public static Vec2i NORTH = new Vec2i(0, 1);
    public static Vec2i SOUTH = new Vec2i(0, -1);
    public static Vec2i EAST = new Vec2i(1, 0);
    public static Vec2i WEST = new Vec2i(-1, 0);


    private final int x;
    private final int y;

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Add one Vec2i to another and return a new vector
     * @param that
     * @return a new Vector that is the sum of the provided vector and this.
     */
    public Vec2i add(Vec2i that) {
        return new Vec2i(
                this.getX() + that.getX(),
                this.getY() + that.getY());
    }

    /**
     * Determine is this point is inside a rectangle, defined by two points
     * @param minBounds the upper left point in the rectangle
     * @param maxBounds the lower right point in the rectangle
     * @return if this point is inside the defined rectangle
     */
    public boolean isInBounds(Vec2i minBounds, Vec2i maxBounds) {
        if (this.getX() < minBounds.getX()) return false;
        if (this.getX() > maxBounds.getX()) return false;
        if (this.getY() < minBounds.getY()) return false;
        if (this.getY() > maxBounds.getY()) return false;
        return true;
    }

    @Override
    public String toString(){
        return String.format("(%d, %d)", getX(), getY());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2i vec2i = (Vec2i) o;
        return x == vec2i.x && y == vec2i.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
