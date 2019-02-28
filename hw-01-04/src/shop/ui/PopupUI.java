package shop.ui;

import sun.reflect.annotation.ExceptionProxy;

import javax.swing.*;
//import java.io.IOException;

final class PopupUI implements UI {
  PopupUI() {}

  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null,message);
  }

  public void displayError(String message) {
    JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
  }

  public void processMenu(UIMenu menu) {
    StringBuffer b = new StringBuffer();
    b.append(menu.getHeading());
    b.append("\n");
    b.append("Enter choice by number:");
    b.append("\n");

    for (int i = 1; i < menu.size(); i++) {
      b.append("  " + i + ". " + menu.getPrompt(i));
      b.append("\n");
    }

    String response = JOptionPane.showInputDialog(b.toString());
    int selection;
    try {
      selection = Integer.parseInt(response, 10);
      if ((selection < 0) || (selection >= menu.size()))
        selection = 0;
    } catch (NumberFormatException e) {
      selection = 0;
    }

    menu.runAction(selection);
  }

  public String[] processForm(UIForm form) {
    String[] pF = new String[form.size()];
    int i = 0;
    UIMenuBuilder menuBuilder = new UIMenuBuilder();
    String j;
    JFrame jf = new JFrame();
    while(i < pF.length){
      j = JOptionPane.showInputDialog(form.getPrompt(i));
      if(j == null){
        break;
      }
      if (form.checkInput(i, j) == true){
        pF[i] = j;
      } else {
        displayError("Invalid input. Try again.");
        i--;
      }
      i++;
    }

    return pF;
  }
}
