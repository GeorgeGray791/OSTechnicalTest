package org.georgegray;

import org.georgegray.entity.Crate;
import org.georgegray.enums.Command;
import org.georgegray.entity.Robot;
import org.georgegray.entity.Vec2i;
import org.georgegray.entity.Warehouse;
import org.georgegray.exceptions.RobotCommandProcessingException;
import org.georgegray.exceptions.RobotException;
import org.georgegray.exceptions.WarehouseLocationNotEmptyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to control a robot in a warehouse via command string
 */
public class RoboControl {

    private final Warehouse warehouse;
    private final Robot robot;

    public RoboControl(Vec2i warehouseSize, Vec2i startLocation, Vec2i... crateLocations) throws WarehouseLocationNotEmptyException {
        this.warehouse = new Warehouse(warehouseSize.getX(), warehouseSize.getY());
        this.robot = new Robot(warehouse,startLocation);
        for (Vec2i crateLocation : crateLocations){
            this.warehouse.addCrateAt(crateLocation, new Crate());
        }
    }

    /**
     * Send a command String to the Robot, in the form of a space delimited string with the chars
     * N E S W representing the cardinal directions, and G D representing  Grab and Drop Operations.
     * @param commandString The command string to parse
     * @throws RobotCommandProcessingException Thrown in the command string is not recognised.
     */
    public void processCommands(String commandString) throws RobotException {

        // Parse the command string
        if (commandString==null || commandString.isEmpty()) return;
        List<Command> commands = extractCommands(commandString);

        // Execute the commands
        for (Command command : commands){
            processCommand(command);
        }

    }

    public void processCommand(Command command) throws RobotException {
        switch (command){
            case N:
                robot.move(Vec2i.NORTH);
                break;
            case S:
                robot.move(Vec2i.SOUTH);
                break;
            case E:
                robot.move(Vec2i.EAST);
                break;
            case W:
                robot.move(Vec2i.WEST);
                break;
            case G:
                robot.grab();
                break;
            case D:
                robot.drop();
                break;
        }
    }

    /**
     * Parse and validate the command String into a List of command enums.
     * @param commandString
     * @return List of Command enums
     * @throws RobotCommandProcessingException
     */
    private List<Command> extractCommands(String commandString) throws RobotCommandProcessingException {
        String[] commands = commandString.split(" ");
        List<Command> commandsOut = new ArrayList<>();

        for (int i = 0; i < commands.length; i++) {
            // Check the command is valid
            if (commands[i].length() != 1 || !Command.contains(commands[i])) {
                throw new RobotCommandProcessingException(
                        String.format("Unrecognised command %s in command string %s, aborting.",
                                commands[i],
                                commandString));
            }

            // Convert to enum and add output list
            Command command = Command.valueOf(commands[i]);
            commandsOut.add(command);
        }
        return commandsOut;
    }

    public Vec2i getRobotLocation() {
        return robot.getPosition();
    }
    public List<Vec2i> getCratePositions(){
        return warehouse.getCratePositions();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Robot is in Location %s. Robot is %sholding a crate. Crate Locations: %n",
                robot.getPosition(),
                robot.getHeldCrate()==null?"not ":""));
        for(Vec2i vec2i : warehouse.getCratePositions()){
            sb.append(String.format("%S%n", vec2i));
        }
        return sb.toString();
    }
}
