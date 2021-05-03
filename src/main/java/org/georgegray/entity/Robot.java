package org.georgegray.entity;

import org.georgegray.exceptions.*;

public class Robot {

    private final Warehouse warehouse;
    private Vec2i position;
    private Crate heldCrate = null;

    public Robot(Warehouse warehouse, Vec2i position) {
        this.warehouse = warehouse;
        this.position = position;
    }

    public void move(Vec2i direction) throws RobotOutOfBoundsException {
        // Calculate the new position of the Robot after the move
        Vec2i newPosition = position.add(direction);

        // Check the robot is still inside the warehouse, and throw an exception if it is not
        if (!newPosition.isInBounds(warehouse.getMinBounds(), warehouse.getMaxBounds())) {
            throw new RobotOutOfBoundsException(String.format("%s is outside the warehouse bounds (%s x %s), cannot move robot in that direction",
                    newPosition,
                    warehouse.getMinBounds(),
                    warehouse.getMaxBounds()));
        }

        position = newPosition;
    }

    public void grab() throws RobotCannotGrabException {
        if (heldCrate!=null) throw new RobotCannotGrabException("Cannot grab a crate when holding a crate");
        if (!warehouse.checkCrateAt(position)) throw new RobotCannotGrabException(String.format("Cannot grab, no crate found at %s", position));
        heldCrate = warehouse.removeCrateAt(position);
    }

    public void drop() throws RobotCannotDropException, WarehouseLocationNotEmptyException {
        if (heldCrate==null) throw new RobotCannotDropException("Cannot drop a crate when no crate is held");
        if (warehouse.checkCrateAt(position)) throw new RobotCannotDropException(String.format("Cannot Drop a crate at position %s, crate already present", position));
        warehouse.addCrateAt(position, heldCrate);
        heldCrate = null;
    }

    public Vec2i getPosition() {
        return position;
    }

    public Crate getHeldCrate() {
        return heldCrate;
    }
}
