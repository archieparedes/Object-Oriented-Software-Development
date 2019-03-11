package shop.data;

import shop.command.RerunnableCommand;
import shop.command.UndoableCommand;

/**
 * A static class for accessing data objects.
 */
public class Data {

  private Data() {}
  /**
   * @return a new Inventory.
   */
  static public final Inventory newInventory() {
    return new InventorySet();
  }

  /**
   * Factory method for Video objects.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if Video invariant violated.
     *
     * @param title title of video
     * @param year year made
     * @param director director name
     * @return new VideoObj
     */
  static public Video newVideo(String title, int year, String director) {
      return new VideoObj(title, year, director);
  }

  /**
   * Returns a command to add or remove copies of a video from the inventory.
   * The returned command has the following behavior:
   *
   * If a video record is not already present (and change is
   * positive), a record is created.
   * If a record is already present, numOwned is
   * modified using change.
   * If change brings the number of copies to be less
   * than one, the record is removed from the inventory.
   * @param inventory inventoryObj
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if inventory not created by a call to newInventory.
   * @return new CmdAdd
   */
  static public UndoableCommand newAddCmd(Inventory inventory, Video video, int change) {
      if (!(inventory instanceof InventorySet))
          throw new IllegalArgumentException();
      return new CmdAdd((InventorySet) inventory, video, change);
  }

  static public String intern(Inventory inventory, Video video) {
      if(!(inventory instanceof InventorySet))
          throw new IllegalArgumentException();
      return((InventorySet) inventory).intern(video.toString());

    }

  /**
   * @param inventory inventory
   * @param video the video to be checked out.
   * @return Returns a command to check out a video.
   */
  static public UndoableCommand newOutCmd(Inventory inventory, Video video) {
      if (!(inventory instanceof InventorySet))
          throw new IllegalArgumentException();
      return new CmdOut((InventorySet)inventory, video);
  }
  
  /**
   * @param inventory inventory
   * @param video the video to be checked in.
   * @return Returns a command to check in a video.
   */
  static public UndoableCommand newInCmd(Inventory inventory, Video video) {
      if (!(inventory instanceof InventorySet))
          throw new IllegalArgumentException();
      return new CmdIn((InventorySet)inventory, video);
  }
  
  /**
   * @param inventory inventoryObj
   * @return a command to remove all records from the inventory.
   */
  static public UndoableCommand newClearCmd(Inventory inventory) {
//      if (!(inventory instanceof InventorySet))
//          throw new IllegalArgumentException();
      return new CmdClear((InventorySet) inventory);
  }

    /**
     *
     * @param inventory inventory
     * @return a command to undo that will undo the last successful UndoableCommand.
     */
  static public RerunnableCommand newUndoCmd(Inventory inventory) {
      // get command history and undo
      return ((InventorySet)inventory).getHistory().getUndo();
  }

    /**
     *
     * @param inventory  inventory
     * @return a command to redo that last successfully undone command.
     */
  static public RerunnableCommand newRedoCmd(Inventory inventory) {
      // get command history from inventory then redo.
      return ((InventorySet)inventory).getHistory().getRedo();
  }
}  
