package shop.ui;

import java.util.ArrayList;
import java.util.List;

final class UIMenuBuilder implements SuperUIInterface, MenuBuilder{
  private final List _menu;
  public UIMenuBuilder() {
    _menu = new ArrayList();
  }
  private static SuperUIFactory SuperUI = new SuperUIFactory();

  public UIFormMenu toUIMenu(String heading) {
    if (null == heading)
      throw new IllegalArgumentException();
    if (_menu.size() <= 1)
      throw new IllegalStateException();
    UIFormMenu.Pair[] array = new UIFormMenu.Pair[_menu.size()];
    for (int i = 0; i < _menu.size(); i++)
      array[i] = (UIFormMenu.Pair) (_menu.get(i));
    return (UIFormMenu)SuperUI.launch("UIFM",heading,array);//new UIFormMenu(heading, array);
  }
  public void add(String prompt, UIMenuAction action) {
    if (null == action)
      throw new IllegalArgumentException();
    _menu.add(new UIFormMenu.Pair(prompt, action));
  }
}
