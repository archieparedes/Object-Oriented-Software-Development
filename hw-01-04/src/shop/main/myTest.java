package shop.main;
import org.junit.Test;
import shop.command.RerunnableCommand;
import shop.data.Data;
import shop.data.Video;
import shop.data.Inventory;

import junit.framework.TestCase;


class myTest extends TestCase{
    private Inventory _inventory = Data.newInventory();
    final RerunnableCommand UNDO = Data.newUndoCmd(_inventory);
    final RerunnableCommand REDO = Data.newRedoCmd(_inventory);

    Video i = Data.newVideo("The Protector", 2005, "Prachya Pinkaew");
    Video i_2 = Data.newVideo("The_Protector", 2005, "Prachya Pinkaew");
    Video j = Data.newVideo("The Protector 2", 2013, "Prachya Pinkaew");
    Video k = Data.newVideo("Batman Begins", 2005, "Christopher Nolan");
    Video k_2 = Data.newVideo("Batman Begins", 2005, "Christopher Polan");

    @Test
    public void testAdd(){
        assertEquals(0, _inventory.size());
        assertTrue(Data.newAddCmd(_inventory, i, 20).run());
        assertEquals(1, _inventory.size());
        assertTrue(Data.newAddCmd(_inventory, i, 5).run());
        assertEquals(1, _inventory.size());
        assertTrue(UNDO.run());
        assertEquals(20, _inventory.get(i).numOwned());
        assertTrue(REDO.run());
        assertEquals(1, _inventory.size());
        assertEquals(25, _inventory.get(i).numOwned());
        assertTrue(Data.newAddCmd(_inventory, i_2, 20).run());
        assertEquals(2, _inventory.size());
        assertTrue(UNDO.run());
        assertEquals(1, _inventory.size());
        assertTrue(UNDO.run()); // undo change i,5
        assertTrue(UNDO.run()); // undo change i,20
        assertEquals(0, _inventory.size());
        assertTrue(Data.newAddCmd(_inventory, i, 5).run());
        assertEquals(1, _inventory.size());
        assertTrue(Data.newAddCmd(_inventory, i, -5).run()); // removing
        assertTrue(UNDO.run());
        assertEquals(1, _inventory.size());
        assertTrue(REDO.run());
        assertEquals(0, _inventory.size());
    }

    @Test
    public void testClear(){
        assertTrue(Data.newAddCmd(_inventory, i, 20).run());
        assertTrue(Data.newAddCmd(_inventory, i, 5).run());
        assertTrue(Data.newOutCmd(_inventory, i).run()); // check out 1. should fail to clear
        assertTrue(Data.newAddCmd(_inventory, i_2, 20).run());
        assertTrue(Data.newInCmd(_inventory, i).run()); // check in 1. should succeed to clear
        assertTrue(Data.newClearCmd(_inventory).run());
        assertEquals(0, _inventory.size());
        assertTrue(UNDO.run()); // get back records
        assertEquals(2, _inventory.size());
        assertTrue(REDO.run());
        assertEquals(0, _inventory.size());

    }

    @Test
    public void testNewOutnewIn(){
        assertTrue(Data.newAddCmd(_inventory, i, 20).run());
        assertTrue(Data.newOutCmd(_inventory, i).run());
        assertTrue(_inventory.get(i).numOut() == 1);
        assertTrue(UNDO.run()); // undo check out
        assertTrue(_inventory.get(i).numOut() == 0);
        assertTrue(REDO.run());
        assertTrue(_inventory.get(i).numRentals() == 1);
        assertTrue(Data.newOutCmd(_inventory, i).run());
        assertTrue(Data.newOutCmd(_inventory, i).run());
        assertTrue(_inventory.get(i).numRentals() == 3);
        assertTrue(Data.newInCmd(_inventory,i).run());
        assertTrue(_inventory.get(i).numOut() == 2);
        assertTrue(UNDO.run());
        assertTrue(_inventory.get(i).numOut() == 3);

    }
}
