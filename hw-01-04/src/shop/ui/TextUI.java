package shop.ui;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

final class TextUI implements UI,SuperUIInterface {
  final BufferedReader _in;
  final PrintStream _out;

  TextUI() {
    _in = new BufferedReader(new InputStreamReader(System.in));
    _out = System.out;
  }

  public void displayMessage(String message) {
    _out.println(message);
  }

  public void displayError(String message) {
    _out.println(message);
  }

  private String getResponse() {
    String result;
    try {
      result = _in.readLine();
    } catch (IOException e) {
      throw new UIError(); // re-throw UIError
    }
    if (result == null) {
      throw new UIError(); // input closed
    }
    return result;
  }

  public void processMenu(UI_FM_Interface menu){
    _out.println(menu.getHeading());
    _out.println("Enter choice by number:");

    for (int i = 1; i < menu.size(); i++) {
      _out.println("  " + i + ". " + menu.getPrompt(i));
    }

    String response = getResponse();
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

  public String[] processForm(UI_FM_Interface form) {
    String[] pF = new String[form.size()];
    for(int i = 0; i < pF.length; i++){
      System.out.println(form.getPrompt(i));
      String j = getResponse();

      if(form.checkInput(i, j) == true){
        pF[i] = j;
      } else {
        System.out.println("Please re-enter year.");
        i-=1;
      }

    }
    return pF;
  }
}
