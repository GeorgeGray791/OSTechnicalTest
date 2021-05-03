package org.georgegray.entity;

import org.georgegray.exceptions.WarehouseLocationNotEmptyException;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private Crate[][] locations;
    private Vec2i minBounds;
    private Vec2i maxBounds;
    public Warehouse(int sizeX, int sizeY){
        minBounds = new Vec2i(0,0);
        maxBounds = new Vec2i(sizeX, sizeY);
        locations = new Crate[sizeX+1][sizeY+1];
    }


    public Vec2i getMinBounds() {
        return minBounds;
    }

    public void setMinBounds(Vec2i minBounds) {
        this.minBounds = minBounds;
    }

    public Vec2i getMaxBounds() {
        return maxBounds;
    }

    public void setMaxBounds(Vec2i maxBounds) {
        this.maxBounds = maxBounds;
    }

    /**
     * Checks is a crate is at a given location
     * @param position
     * @return returns true if a crate is present, false if a crate is nto present
     */
    public boolean checkCrateAt(Vec2i position) {
        return locations[position.getX()][position.getY()] !=null;
    }

    /**
     * returns and removes from the warehouse floor a crate at a given location
     * @param position the position to remove the crate form
     * @return
     */
    public Crate removeCrateAt(Vec2i position) {
        Crate c = locations[position.getX()][position.getY()];
        locations[position.getX()][position.getY()] = null;
        return c;
    }

    /**
     * Places a crate at a given location on the warehouse floor,
     * @param position The position to place the crate
     * @param c The crate to place
     * @throws WarehouseLocationNotEmptyException thrown if a crate is already in the location.
     */

    public void addCrateAt(Vec2i position, Crate c) throws WarehouseLocationNotEmptyException {
        if (locations[position.getX()][position.getY()] != null) {
            throw new WarehouseLocationNotEmptyException(String.format("Could not place Crate at %s, position not empty", position));
        }
        locations[position.getX()][position.getY()] = c;
    }

    /**
     * generate a list of all crate locations
     * @return
     */
    public List<Vec2i> getCratePositions() {
        List<Vec2i> outLocations = new ArrayList<>();
        for (int x =0; x < locations.length; x++){
            for (int y = 0; y < locations[x].length; y++){
                if (locations[x][y]!=null) {
                    outLocations.add(new Vec2i(x,y));
                }
            }
        }
        return outLocations;
    }
}
