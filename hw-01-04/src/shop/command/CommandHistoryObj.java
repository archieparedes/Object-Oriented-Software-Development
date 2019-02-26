package shop.command;
import java.util.Stack;

final class CommandHistoryObj implements CommandHistory {
  Stack<UndoableCommand> _undoStack = new Stack<UndoableCommand>();
  Stack<UndoableCommand> _redoStack = new Stack<UndoableCommand>();
  RerunnableCommand _undoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_undoStack.empty();
        if (result) {
          // Undo
          UndoableCommand u = _undoStack.pop(); // remove from undo stack
          u.undo();
          _redoStack.push(u); // put it in redo to reverse u command
        }
        return result;
      }
    };
  RerunnableCommand _redoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_redoStack.empty();
        if (result) {
          // Redo
          UndoableCommand r = _redoStack.pop(); // remove from redo and grab the command
          r.redo();
          _undoStack.push(r); // put it in undo to reverse r command
        }
        return result;
      }
    };

  public void add(UndoableCommand cmd) {
    _undoStack.push(cmd); // add to undo stack
    _redoStack.clear(); // clear redo, you cant redo a recently added
  }
  
  public RerunnableCommand getUndo() {
    return _undoCmd;
  }
  
  public RerunnableCommand getRedo() {
    return _redoCmd;
  }
  
  // For testing
  UndoableCommand topUndoCommand() {
    if (_undoStack.empty())
      return null;
    else
      return _undoStack.peek();
  }
  // For testing
  UndoableCommand topRedoCommand() {
    if (_redoStack.empty())
      return null;
    else
      return _redoStack.peek();
  }
}
