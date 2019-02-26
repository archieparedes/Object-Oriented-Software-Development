package shop.data;

import shop.command.Command;
import java.util.Comparator;
import java.util.Iterator;

/**
 * A collection of Records.
 * Records can only be created and destroyed using the Inventory.
 * @see Data
 */
public interface Inventory extends Iterable<Record> {
  /**
   *  @return the number of Records.
   */
  public int size();

  /**
   * @param v video
   *  @return the record for a given Video.
   */
  public Record get(Video v);

  /**
   *  @return an iterator over Records in the Inventory.
   *  The iterator returns objects that implement the Record interface.
   *  The Records are unordered.
   */
  public Iterator<Record> iterator();

  /**
   *  @return an iterator over the Inventory, sorted accoring the
   *  Comparator.
   *  The iterator returns objects that implement the
   *  Record interface.
   *  The iteration order is determined by the comparator (least first)
   *  The comparator may assume that its arguments implement
   *  Record.
   *  @param comparator determines the order of the records returned.
   */
  public Iterator<Record> iterator(Comparator<Record> comparator);

  /**
   * @return the inventory as a string; one record per line.
   */
  public String toString();
}
