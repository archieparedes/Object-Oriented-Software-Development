package shop.command;

public class CommandHistoryFactory {
  private CommandHistoryFactory() {}

  /**
   *
   * @return commandHistoryObj is returned
   */
  static public CommandHistory newCommandHistory() {
    CommandHistory ch = new CommandHistoryObj();
    return ch;
  }
}
