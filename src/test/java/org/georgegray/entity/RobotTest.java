package org.georgegray.entity;

import org.georgegray.exceptions.RobotCannotDropException;
import org.georgegray.exceptions.RobotCannotGrabException;
import org.georgegray.exceptions.RobotOutOfBoundsException;
import org.georgegray.exceptions.WarehouseLocationNotEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RobotTest {

    @Test
    void move() throws RobotOutOfBoundsException {
        Warehouse mockWarehouse = mock(Warehouse.class);
        when(mockWarehouse.getMinBounds()).thenReturn(new Vec2i(0,0));
        when(mockWarehouse.getMaxBounds()).thenReturn(new Vec2i(9,9));
        Robot robot = new Robot(mockWarehouse, new Vec2i(5,5));
        robot.move(new Vec2i(0,1));
        Assertions.assertEquals(new Vec2i(5, 6), robot.getPosition());
    }

    @Test
    void moveOutOfBoundsUpper(){
        Warehouse mockWarehouse = mock(Warehouse.class);
        when(mockWarehouse.getMinBounds()).thenReturn(new Vec2i(0,0));
        when(mockWarehouse.getMaxBounds()).thenReturn(new Vec2i(9,9));


        Robot robot = new Robot(mockWarehouse, new Vec2i(5,5));
        Assertions.assertThrows(RobotOutOfBoundsException.class, () -> {
            robot.move(new Vec2i(0, 1));
            robot.move(new Vec2i(0, 1));
            robot.move(new Vec2i(0, 1));
            robot.move(new Vec2i(0, 1));
            robot.move(new Vec2i(0, 1));

        });

        Assertions.assertThrows(RobotOutOfBoundsException.class, () -> {
            robot.move(new Vec2i(1, 0));
            robot.move(new Vec2i(1, 0));
            robot.move(new Vec2i(1, 0));
            robot.move(new Vec2i(1, 0));
            robot.move(new Vec2i(1, 0));

        });



    }

    @Test
    void moveOutOfBoundsLower() {

        Warehouse mockWarehouse = mock(Warehouse.class);
        when(mockWarehouse.getMinBounds()).thenReturn(new Vec2i(0,0));
        when(mockWarehouse.getMaxBounds()).thenReturn(new Vec2i(9,9));

        Robot robot = new Robot(mockWarehouse, new Vec2i(5,5));

        Assertions.assertThrows(RobotOutOfBoundsException.class, () -> {
            robot.move(new Vec2i(-1, 0));
            robot.move(new Vec2i(-1, 0));
            robot.move(new Vec2i(-1, 0));
            robot.move(new Vec2i(-1, 0));
            robot.move(new Vec2i(-1, 0));
            robot.move(new Vec2i(-1, 0));
        });

        Assertions.assertThrows(RobotOutOfBoundsException.class, () -> {
            robot.move(new Vec2i(0, -1));
            robot.move(new Vec2i(0, -1));
            robot.move(new Vec2i(0, -1));
            robot.move(new Vec2i(0, -1));
            robot.move(new Vec2i(0, -1));
            robot.move(new Vec2i(0, -1));
        });

    }


    @Test
    void grabAndDrop() throws RobotCannotGrabException, RobotOutOfBoundsException, RobotCannotDropException, WarehouseLocationNotEmptyException {
        Warehouse mockWarehouse = mock(Warehouse.class);
        when(mockWarehouse.getMinBounds()).thenReturn(new Vec2i(0,0));
        when(mockWarehouse.getMaxBounds()).thenReturn(new Vec2i(9,9));
        when(mockWarehouse.checkCrateAt(new Vec2i(1,0))).thenReturn(false);
        when(mockWarehouse.checkCrateAt(new Vec2i(0,0))).thenReturn(true);
        when(mockWarehouse.checkCrateAt(new Vec2i(0,1))).thenReturn(true);
        when(mockWarehouse.checkCrateAt(new Vec2i(0,2))).thenReturn(false);
        when (mockWarehouse.removeCrateAt(new Vec2i(0,0))).thenReturn(new Crate());
        Robot robot = new Robot(mockWarehouse, new Vec2i(0,0));

        Assertions.assertNull(robot.getHeldCrate());
        robot.move(Vec2i.EAST);
        Assertions.assertThrows(RobotCannotGrabException.class, () -> {
            robot.grab();
        });
        Assertions.assertNull(robot.getHeldCrate());
        robot.move(Vec2i.WEST);
        robot.grab();
        Assertions.assertNotNull(robot.getHeldCrate());
        robot.move(Vec2i.NORTH);
        Assertions.assertThrows(RobotCannotDropException.class, () -> {
            robot.drop();
        });
        Assertions.assertNotNull(robot.getHeldCrate());
        robot.move(Vec2i.NORTH);
        robot.drop();
        Assertions.assertNull(robot.getHeldCrate());
    }
}