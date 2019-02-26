package shop.data;

import shop.command.UndoableCommand;

/**
 * Implementation of command to check in a video.
 * @see Data
 */
final class CmdIn implements UndoableCommand {
  private InventorySet _inventory;
  private Video _video;
  private Record _oldvalue;
  CmdIn(InventorySet inventory, Video video) {
    _inventory = inventory;
    _video = video;
  }
  public boolean run() {
    try {
        _oldvalue = _inventory.checkIn(_video);
        _inventory.getHistory().add(this);
        return true;
    } catch (IllegalArgumentException e) {
        return false;
    } catch (ClassCastException e) {
        return false;
    }
  }
  public void undo() { _inventory.replaceEntry(_video, _oldvalue);}
  public void redo() {
    _inventory.checkIn(_video);
  }
}
