package shop.main;

import shop.ui.SuperUIFactory;
import shop.data.Data;

public class Main {
  private Main() {}
  public static void main(String[] args) {
    Control control = new Control(Data.newInventory(), SuperUIFactory.ui());
    control.run();
  }
}
