package org.georgegray.entity;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class Vec2iTest {

    @org.junit.jupiter.api.Test
    void add() {
        Assertions.assertEquals(new Vec2i(5, 11), new Vec2i(1,4).add(new Vec2i(4,7)));
        Assertions.assertEquals(new Vec2i(3, 7), new Vec2i(1,4).add(new Vec2i(2,3)));
    }

    @org.junit.jupiter.api.Test
    void isInBounds() {
        Vec2i topLeft = new Vec2i(0,0);
        Vec2i botRight = new Vec2i(10,10);

        Assertions.assertTrue(new Vec2i(0,3).isInBounds(topLeft, botRight));
        Assertions.assertTrue(new Vec2i(5,8).isInBounds(topLeft, botRight));
        Assertions.assertTrue(new Vec2i(0,10).isInBounds(topLeft, botRight));
        Assertions.assertTrue(new Vec2i(10,10).isInBounds(topLeft, botRight));
        Assertions.assertTrue(new Vec2i(0,0).isInBounds(topLeft, botRight));

        Assertions.assertFalse(new Vec2i(-1,0).isInBounds(topLeft, botRight));
        Assertions.assertFalse(new Vec2i(11,0).isInBounds(topLeft, botRight));
        Assertions.assertFalse(new Vec2i(0,-1).isInBounds(topLeft, botRight));
        Assertions.assertFalse(new Vec2i(0,11).isInBounds(topLeft, botRight));
        Assertions.assertFalse(new Vec2i(-1,-1).isInBounds(topLeft, botRight));
        Assertions.assertFalse(new Vec2i(11,11).isInBounds(topLeft, botRight));
    }
}