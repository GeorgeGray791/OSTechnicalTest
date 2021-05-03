package org.georgegray.entity;

import org.georgegray.exceptions.WarehouseLocationNotEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WarehouseTest {

    @Test
    void checkCrateAt() throws WarehouseLocationNotEmptyException {
        Warehouse warehouse = new Warehouse(9,9);
        warehouse.addCrateAt(new Vec2i(0,0), new Crate());
        Assertions.assertTrue(warehouse.checkCrateAt(new Vec2i(0,0)));
        Assertions.assertFalse(warehouse.checkCrateAt(new Vec2i(0,1)));
    }

    @Test
    void removeCrateAt() throws WarehouseLocationNotEmptyException {
        Crate crate = new Crate();
        Warehouse warehouse = new Warehouse(9,9);
        warehouse.addCrateAt(new Vec2i(0,0), crate);

        Assertions.assertEquals(crate, warehouse.removeCrateAt(new Vec2i(0,0)));
        Assertions.assertFalse(warehouse.checkCrateAt(new Vec2i(0,0)));

    }

    @Test
    void addCrateAt() throws WarehouseLocationNotEmptyException {
        Crate crate = new Crate();
        Warehouse warehouse = new Warehouse(9,9);
        Assertions.assertFalse(warehouse.checkCrateAt(new Vec2i(0,0)));
        warehouse.addCrateAt(new Vec2i(0, 0), crate);
        Assertions.assertTrue(warehouse.checkCrateAt(new Vec2i(0,0)));
        Assertions.assertThrows(WarehouseLocationNotEmptyException.class, () -> {
            warehouse.addCrateAt(new Vec2i(0, 0), crate);
        });
    }
}