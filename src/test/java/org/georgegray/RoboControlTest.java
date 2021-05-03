package org.georgegray;

import org.georgegray.entity.Vec2i;
import org.georgegray.enums.Command;
import org.georgegray.exceptions.RobotException;
import org.georgegray.exceptions.WarehouseLocationNotEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoboControlTest {

    @Test
    void processCommandsPartOne() throws RobotException {

        RoboControl roboControl = new RoboControl(new Vec2i(9,9), new Vec2i(0,0), new Vec2i(4,4), new Vec2i(9,9));
        Assertions.assertEquals(new Vec2i(0,0), roboControl.getRobotLocation());
        roboControl.processCommands("N E S W");
        Assertions.assertEquals(new Vec2i(0,0), roboControl.getRobotLocation());
        roboControl.processCommands("N E N E N E N E");
        Assertions.assertEquals(new Vec2i(4,4), roboControl.getRobotLocation());

    }

    @Test
    void processCommandsPartTwo() throws RobotException {

        RoboControl roboControl = new RoboControl(new Vec2i(9,9), new Vec2i(0,0), new Vec2i(4,4), new Vec2i(9,9));
        Assertions.assertEquals(new Vec2i(0,0), roboControl.getRobotLocation());

        // Pickup the crate in the center and drop it in the starting position of the robot
        roboControl.processCommands("N E N E N E N E G S W S W S W S W D");
        Assertions.assertEquals(new Vec2i(0,0), roboControl.getRobotLocation());
        Assertions.assertTrue(roboControl.getCratePositions().contains(new Vec2i(0,0)));

        // Pickup the crate in the North East corner and drop it in the second cell on the most southerly row.
        roboControl.processCommands("N N N N N N N N N E E E E E E E E E G S S S S S S S S S W W W W W W W W D");
        Assertions.assertEquals(new Vec2i(1,0), roboControl.getRobotLocation());
        Assertions.assertTrue(roboControl.getCratePositions().contains(new Vec2i(1,0)));
        Assertions.assertTrue(roboControl.getCratePositions().contains(new Vec2i(0,0)));

    }

    @Test
    void processCommand() throws RobotException {
        RoboControl roboControl = new RoboControl(new Vec2i(9,9), new Vec2i(0,0), new Vec2i(2,2), new Vec2i(9,9));

        Assertions.assertEquals(new Vec2i(0,0), roboControl.getRobotLocation());
        roboControl.processCommand(Command.N);
        Assertions.assertEquals(new Vec2i(0,1), roboControl.getRobotLocation());
        roboControl.processCommand(Command.E);
        Assertions.assertEquals(new Vec2i(1,1), roboControl.getRobotLocation());
        roboControl.processCommand(Command.S);
        Assertions.assertEquals(new Vec2i(1,0), roboControl.getRobotLocation());
        roboControl.processCommand(Command.W);
        Assertions.assertEquals(new Vec2i(0,0), roboControl.getRobotLocation());

        Assertions.assertTrue(roboControl.getCratePositions().contains(new Vec2i(2,2)));
        roboControl.processCommand(Command.N);
        roboControl.processCommand(Command.N);
        roboControl.processCommand(Command.E);
        roboControl.processCommand(Command.E);
        roboControl.processCommand(Command.G);
        Assertions.assertTrue(!roboControl.getCratePositions().contains(new Vec2i(2,2)));
        roboControl.processCommand(Command.S);
        roboControl.processCommand(Command.S);
        roboControl.processCommand(Command.W);
        roboControl.processCommand(Command.W);
        roboControl.processCommand(Command.D);
        Assertions.assertTrue(roboControl.getCratePositions().contains(new Vec2i(0,0)));

    }
}