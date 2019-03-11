package shop.ui;

public interface UI {
  public void processMenu(UI_FM_Interface menu);
  public String[] processForm(UI_FM_Interface form);
  public void displayMessage(String message);
  public void displayError(String message);
}
