package shop.main;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import shop.command.Command;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.data.Inventory;
import java.util.Iterator;

class TEST1 extends TestCase {
  private Inventory _inventory = Data.newInventory();
  public TEST1(String name) {
    super(name);
  }
  private void expect(Video v, String s) {
    assertEquals(s,_inventory.get(v).toString());
  }
  private void expect(Record r, String s) {
    assertEquals(s,r.toString());
  }

  @Test
  public void test1() {
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();
    
    Video v1 = Data.newVideo("Title1", 2000, "Director1");
    assertEquals(0, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    // System.out.println(_inventory.get(v1));
    expect(v1,"Title1 (2000) : Director1 [10,0,0]");
    
    Video v2 = Data.newVideo("Title2", 2001, "Director2");
    assertTrue(Data.newAddCmd(_inventory, v2, 1).run());
    assertEquals(2, _inventory.size());
    expect(v2,"Title2 (2001) : Director2 [1,0,0]");
    
    assertFalse(Data.newAddCmd(_inventory, null, 5).run());
    assertEquals(2, _inventory.size());
    
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    expect(v2,"Title2 (2001) : Director2 [1,1,1]");
    
    assertTrue(Data.newInCmd(_inventory, v2).run());
    expect(v2,"Title2 (2001) : Director2 [1,0,1]");
    
    assertTrue(Data.newAddCmd(_inventory, v2, -1).run());
    assertEquals(1, _inventory.size());
    expect(v1,"Title1 (2000) : Director1 [10,0,0]");
    
    Command outCmd = Data.newOutCmd(_inventory, v1);
    assertTrue(outCmd.run());
    assertTrue(outCmd.run());
    assertTrue(outCmd.run());
    assertTrue(outCmd.run());
    expect(v1,"Title1 (2000) : Director1 [10,4,4]");
    
    assertTrue(Data.newInCmd(_inventory, v1).run());
    expect(v1,"Title1 (2000) : Director1 [10,3,4]");
    
    assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
    assertEquals(2, _inventory.size());
    expect(v2,"Title2 (2001) : Director2 [5,0,0]");
    expect(v1,"Title1 (2000) : Director1 [10,3,4]");

    Iterator<Record> it = _inventory.iterator(new java.util.Comparator<Record>() {
        public int compare (Record r1, Record r2) {
          return r2.numRentals() - r1.numRentals();
        }
      });
    expect(it.next(),"Title1 (2000) : Director1 [10,3,4]");
    expect(it.next(),"Title2 (2001) : Director2 [5,0,0]");
    assertFalse(it.hasNext());
  }


}
