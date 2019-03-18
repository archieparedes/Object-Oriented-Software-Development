package shop.main;
import shop.command.Command;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Record;
import shop.data.Video;
import shop.ui.*;
import java.util.Comparator;
import java.util.Iterator;

enum ControlEnumState {
    ADD_REMOVE(new addremove()), // each enumerator calls a new class
    CHECKIN(new checkin()), CHECKOUT(new checkout()),
    PRINT(new printInventory()), UNDO(new undo()),
    REDO(new redo()), TOP10(new top()),
    INIT(new bogus()), CLEAR(new clear()),
    DEFAULT(new def());

    private UIMenuAction run;

    private static UI _ui = null;
    private static Inventory _inventory = null;
    private static UI_FM_Interface _getVideoForm;
    private static UIFormTest _numberTest;

    private static SuperUIFactory SuperUI = new SuperUIFactory();

    ControlEnumState(UIMenuAction r){ // init UIMenuAction interface
        this.run = r;
    }

    UIMenuAction run(){
        return run;
    }

    static class INIT{
        public INIT(UI ui, Inventory inventory, UI_FM_Interface getVideoForm, UIFormTest nt) { // initialize constructors
            _ui = ui;
            _inventory = inventory;
            _getVideoForm = getVideoForm;
            _numberTest = nt;

        }
    }

    static class def implements UIMenuAction{
        public void run() {
            _ui.displayError("doh!");
        }
    }
////    ,
    static class addremove implements UIMenuAction{
        public void run(){
            String[] result1 = _ui.processForm(_getVideoForm);
            if (result1[0] == null || result1[1] == null || result1[2] == null){
                return;
            }
            Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]); // title, year, director
            FormBuilder f = (FormBuilder)SuperUI.launch("UIFB",null,null);

            f.add("Number of copies to add/remove", _numberTest);
            String[] result2 = _ui.processForm(f.toUIForm(""));
            if(result2[0] == null) return;

            Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
            if (! c.run())  _ui.displayError("Command failed");
        }
    }
////    ,
    static class checkin implements UIMenuAction{
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
    }
////    ,
    static class checkout implements UIMenuAction{
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
    }
////    ,
    static class printInventory implements UIMenuAction{
        public void run() {
            _ui.displayMessage(_inventory.toString());
        }
    }
////    ,
    static class clear implements UIMenuAction{
        public void run() {
            if (!Data.newClearCmd(_inventory).run()) {
                _ui.displayError("Command failed");
            }
        }
    }
////    ,
    static class undo implements UIMenuAction{
        public void run() {
            if (!Data.newUndoCmd(_inventory).run()) {
                _ui.displayError("Command failed");
            }
        }
    }
////    ,
    static class redo implements UIMenuAction{
        public void run() {
            if (!Data.newRedoCmd(_inventory).run()) {
                _ui.displayError("Command failed");
            }
        }
    }
////    ,
    static class top implements UIMenuAction{
        public void run() {
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
    }
////    ,
    static class bogus implements UIMenuAction{
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
    }
}
