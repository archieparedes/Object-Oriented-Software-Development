package shop.main;

import shop.ui.UI;
import shop.ui.UIMenu;
import shop.ui.UIMenuAction;
import shop.ui.UIMenuBuilder;
import shop.ui.UIError;
import shop.ui.UIForm;
import shop.ui.UIFormTest;
import shop.ui.UIFormBuilder;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;
import shop.command.Command;
import java.util.Iterator;
import java.util.Comparator;

class Control {
  private static final int EXITED = 0;
  private static final int EXIT = 1;
  private static final int START = 2;
  private static final int NUMSTATES = 10;
  private UIMenu[] _menus;
  private int _state;

  private UIForm _getVideoForm;
  private UIFormTest _numberTest;
  private UIFormTest _stringTest;
    
  private Inventory _inventory;
  private UI _ui;
  
  Control(Inventory inventory, UI ui) {
    _inventory = inventory;
    _ui = ui;

    _menus = new UIMenu[NUMSTATES];
    _state = START;
    addSTART(START);
    addEXIT(EXIT);
    
    UIFormTest yearTest = new UIFormTest() {
        public boolean run(String input) {
          try {
            int i = Integer.parseInt(input);
            return i > 1800 && i < 5000;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      };
    _numberTest = new UIFormTest() {
        public boolean run(String input) {
          try {
            Integer.parseInt(input);
            return true;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      };
    _stringTest = new UIFormTest() {
        public boolean run(String input) {
          return ! "".equals(input.trim());
        }
      };

    UIFormBuilder f = new UIFormBuilder();
    f.add("Title", _stringTest);
    f.add("Year", yearTest);
    f.add("Director", _stringTest);
    _getVideoForm = f.toUIForm("Enter Video");
  }
  
  void run() {
    try {
      while (_state != EXITED) {
        _ui.processMenu(_menus[_state]);
      }
    } catch (UIError e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(int stateNum) {
    UIMenuBuilder m = new UIMenuBuilder();
    
    m.add("Default",
      new UIMenuAction() {
        public void run() {
          _ui.displayError("doh!");
        }
      });
    m.add("Add/Remove copies of a video",
      new UIMenuAction() {
        public void run() {
          String[] result1 = _ui.processForm(_getVideoForm);
          if (result1[0] == null || result1[1] == null || result1[2] == null){
            return;
          }
          Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]); // title, year, director
          UIFormBuilder f = new UIFormBuilder();

          f.add("Number of copies to add/remove", _numberTest);
          String[] result2 = _ui.processForm(f.toUIForm(""));
                                             
          Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
          if (! c.run())  _ui.displayError("Command failed");
        }
      });
    m.add("Check in a video",
      new UIMenuAction() {
        public void run() {
          // GOAL: get video info from user. match video in inventory, check in command
          if (_inventory.size() <= 0) {
            _ui.displayError("Inventory is empty at the moment");
            return;
          }
          String[] result1 = _ui.processForm(_getVideoForm);
          if (result1[0] == null || result1[1] == null || result1[2] == null){
            return;
          }
          Video v = null;
          try{
              int year = Integer.parseInt(result1[1]);
          } catch (Exception e){
              _ui.displayError("Year is not an integer");
              return;
          }
          v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);


          // find video in inventory, pull video info, then check in
          //_ui.displayMessage(video);
          Command c = Data.newInCmd(_inventory,v);
          if (! c.run())  _ui.displayError("Command failed"); // run command, if it fails, output an error
          else  _ui.displayMessage("Video checked in. Thank you!");


        }
      });
    m.add("Check out a video",
      new UIMenuAction() {
        public void run() {
          // check if there's videos in inventory, then check it out
          //System.out.println("check out initiated"); // for debugging
          if (_inventory.size() <= 0) {
            _ui.displayError("Inventory is empty at the moment");
            return;
          }
          String[] result1 = _ui.processForm(_getVideoForm);
          if (result1[0] == null || result1[1] == null || result1[2] == null){
            return;
          }

          try{
            int year = Integer.parseInt(result1[1]);
          } catch (Exception e){
            _ui.displayError("Year is not an integer");
            return;
          }

          Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

            // find video in inventory, pull video info, then check in
          Command c = Data.newOutCmd(_inventory,v);
          if (! c.run())  _ui.displayError("Command failed"); // run command, if it fails, output an error
          else  _ui.displayMessage("Video checked out. Thank you!");

        }
      });
    m.add("Print the inventory",
      new UIMenuAction() {
        public void run() {
          _ui.displayMessage(_inventory.toString());
        }
      });
    m.add("Clear the inventory",
      new UIMenuAction() {
        public void run() {
          if (!Data.newClearCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Undo",
      new UIMenuAction() {
        public void run() {
          if (!Data.newUndoCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Redo",
      new UIMenuAction() {
        public void run() {
          if (!Data.newRedoCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Print top ten all time rentals in order",
      new UIMenuAction() {
        public void run() {
          // DONE **
          // Goal: sort out according to numRentals, output highest to lowest numRental amount, if not enough videos, output blanks. output as _ui.displayMessage
          // use comparator
          Comparator<Record> top10 = new Comparator<Record>() { // sorts from largest to smallest
            @Override
            public int compare(Record o1, Record o2) {
              return ((o2.numRentals()) - (o1.numRentals()));
            }
          };
          if (_inventory.size() <= 0){ // checks if inventory is empty
            _ui.displayError("No videos in Inventory");
          } else {
            Iterator<Record> sortedInv = _inventory.iterator(top10); // get inventory sorted
            int i = 1; // count up
            String vidName, message = "";
            while(i < 11){
              if(!sortedInv.hasNext()){ // if there are no more videos in lineup, output blanks
                vidName = "";
              } else {
                Record r = sortedInv.next(); // pull the record

                vidName = r.toString(); // pull video, then get String of video
              }
              message += i + ".)  " + vidName + " \n";
              i++;
            }
            _ui.displayMessage(message); // display message of top 10
          }
        }
      });
          
    m.add("Exit",
      new UIMenuAction() {
        public void run() {
          _state = EXIT;
        }
      });
    
    m.add("Initialize with bogus contents",
      new UIMenuAction() {
        public void run() {
          Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
          Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
          Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
          Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
          Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
          Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
          Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
          Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
          Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
          Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
          Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
          Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
          Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
          Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
          Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
          Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
          Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
          Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
          Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
          Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
          Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
          Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
          Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
          Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
          Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
          Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
        }
      });
    
    _menus[stateNum] = m.toUIMenu("Bob's Video");
  }
  private void addEXIT(int stateNum) {
    UIMenuBuilder m = new UIMenuBuilder();
    
    m.add("Default", new UIMenuAction() { public void run() {} });
    m.add("Yes",
      new UIMenuAction() {
        public void run() {
          _state = EXITED;
        }
      });
    m.add("No",
      new UIMenuAction() {
        public void run() {
          _state = START;
        }
      });
    
    _menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
  }
}
