package shop.main;

import shop.ui.*;
import shop.data.Inventory;
import shop.main.ControlEnumState.INIT;

// unused imports
//import shop.data.Data;
//import shop.data.Video;
//import shop.data.Record;
//import shop.command.Command;
//import java.util.Iterator;
//import java.util.Comparator;

final class Control{
  StatesEnum states;
  UIFormTest_v2 UIFormTests;
  private UI_FM_Interface[] _menus;
  private int _state;
  private UI_FM_Interface _getVideoForm;
  private UIFormTest _numberTest, _stringTest;
  private Inventory _inventory;
  private UI _ui;
  private INIT uiAct;
  private static SuperUIFactory SuperUI = new SuperUIFactory();


  Control(Inventory inventory, UI ui) {
    _inventory = inventory;
    _ui = ui;

    _menus = new UI_FM_Interface[states.NUMSTATES.get()];
    _state = states.START.get();
    addSTART(states.START.get());
    addEXIT(states.EXIT.get());

    UIFormTest yearTest = UIFormTests.YEARTEST.go();
    _numberTest = UIFormTests.NUMBERTEST.go();
    _stringTest = UIFormTests.STRINGTEST.go();

    FormBuilder f = (FormBuilder)SuperUI.launch("UIFB",null,null); //new UIFormBuilder();
    f.add("Title", _stringTest);
    f.add("Year", yearTest);
    f.add("Director", _stringTest);
    _getVideoForm =  f.toUIForm("Enter Video");
    uiAct = new INIT(_ui, _inventory, _getVideoForm, _numberTest);
  }
  
  void run() {
    try {
      while (_state != states.EXITED.get()) {
        _ui.processMenu(_menus[_state]);
      }
    } catch (UIError e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(int stateNum) {
    MenuBuilder m = (MenuBuilder)SuperUI.launch("UIMB",null,null); //new UIMenuBuilder();
    
    m.add("Default",ControlEnumState.DEFAULT.go());
    m.add("Add/Remove copies of a video", ControlEnumState.ADD_REMOVE.go());
    m.add("Check in a video", ControlEnumState.CHECKIN.go());
    m.add("Check out a video",ControlEnumState.CHECKOUT.go());
    m.add("Print the inventory",ControlEnumState.PRINT.go());
    m.add("Clear the inventory", ControlEnumState.CLEAR.go());
    m.add("Undo", ControlEnumState.UNDO.go());
    m.add("Redo",ControlEnumState.REDO.go());
    m.add("Print top ten all time rentals in order", ControlEnumState.TOP10.go());
    m.add("Initialize with bogus contents", ControlEnumState.INIT.go());
    m.add("Exit", new UIMenuAction() {
      public void run() {
        _state = states.EXIT.get();
      }});
    
    _menus[stateNum] = m.toUIMenu("Bob's Video");
  }
  private void addEXIT(int stateNum) {
    MenuBuilder m = (MenuBuilder)SuperUI.launch("UIMB",null,null); //new UIMenuBuilder();

    m.add("Default", ControlEnumState.DEFAULT.go());
    m.add("Yes",
      new UIMenuAction() {
        public void run() { _state = states.EXITED.get(); }
      });
    m.add("No",
      new UIMenuAction() {
        public void run() {
          _state = states.START.get();
        }
      });
    
    _menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
  }
}
