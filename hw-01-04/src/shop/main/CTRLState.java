//package shop.main;
//import shop.command.Command;
//import shop.data.Data;
//import shop.data.Inventory;
//import shop.data.Record;
//import shop.data.Video;
//import shop.ui.UI;
//import shop.ui.UIFormMenu;
//import shop.ui.UIFormBuilder;
//import shop.ui.UIFormTest;
//
//import java.util.Comparator;
//import java.util.Iterator;
//
//
//public enum CTRLState{
//    ADD_REMOVE("addremove"),
//    CHECKIN("checkin"), CHECKOUT("checkout"),
//    PRINT("print"), UNDO("undo"),
//    REDO("redo"), TOP10("top"),
//    INIT("init"), CLEAR("clear");
//
//    //how do I get these bruh?
//    private UI _ui = null;
//    private Inventory _inventory = null;
//    private UIFormMenu _getVideoForm = null;
//    private UIFormTest _numberTest = null;
//
//    String choice;
//
//    CTRLState(String c){
//        this.choice = c;
//    }
//
//    public void run(){
//        switch(choice){
//            case "addremove":
//                ARrun();
//                break;
//            case "checkin":
//                CIrun();
//                break;
//            case "checkout":
//                COrun();
//                break;
//            case "print":
//                PIrun();
//                break;
//            case "clear":
//                Crun();
//                break;
//            case "undo":
//                Urun();
//                break;
//            case "redo":
//                Rrun();
//                break;
//            case "top":
//                Trun();
//                break;
//            case "init":
//                Irun();
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void ARrun(){
//        String[] result1 = _ui.processForm(_getVideoForm);
//        if (result1[0] == null || result1[1] == null || result1[2] == null){
//            return;
//        }
//        Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]); // title, year, director
//        UIFormBuilder f = new UIFormBuilder();
//
//        f.add("Number of copies to add/remove", _numberTest);
//        String[] result2 = _ui.processForm(f.toUIForm(""));
//        if(result2[0] == null) return;
//
//        Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
//        if (! c.run())  _ui.displayError("Command failed");
//    }
//    ////    ,
//    private void CIrun() {
//        // GOAL: get video info from user. match video in inventory, check in command
//        if (_inventory.size() <= 0) {
//            _ui.displayError("Inventory is empty at the moment");
//            return;
//        }
//        String[] result1 = _ui.processForm(_getVideoForm);
//        if (result1[0] == null || result1[1] == null || result1[2] == null){
//            return;
//        }
//        Video v = null;
//        try{
//            int year = Integer.parseInt(result1[1]);
//        } catch (Exception e){
//            _ui.displayError("Year is not an integer");
//            return;
//        }
//        v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
//
//        // find video in inventory, pull video info, then check in
//        //_ui.displayMessage(video);
//        Command c = Data.newInCmd(_inventory,v);
//        if (! c.run())  _ui.displayError("Command failed"); // run command, if it fails, output an error
//        else  _ui.displayMessage("Video checked in. Thank you!");
//    }
//    ////    ,
//    private void COrun() {
//        // check if there's videos in inventory, then check it out
//        //System.out.println("check out initiated"); // for debugging
//        if (_inventory.size() <= 0) {
//            _ui.displayError("Inventory is empty at the moment");
//            return;
//        }
//        String[] result1 = _ui.processForm(_getVideoForm);
//        if (result1[0] == null || result1[1] == null || result1[2] == null){
//            return;
//        }
//
//        try{
//            int year = Integer.parseInt(result1[1]);
//        } catch (Exception e){
//            _ui.displayError("Year is not an integer");
//            return;
//        }
//
//        Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
//
//        // find video in inventory, pull video info, then check in
//        Command c = Data.newOutCmd(_inventory,v);
//        if (! c.run())  _ui.displayError("Command failed"); // run command, if it fails, output an error
//        else  _ui.displayMessage("Video checked out. Thank you!");
//    }
//    ////    ,
//    private void PIrun() {
//        _ui.displayMessage(_inventory.toString());
//    }
//    ////    ,
//    private void Crun() {
//        if (!Data.newClearCmd(_inventory).run()) {
//            _ui.displayError("Command failed");
//        }
//    }
//    ////    ,
//    private void Urun() {
//        if (!Data.newUndoCmd(_inventory).run()) {
//            _ui.displayError("Command failed");
//        }
//    }
//    ////    ,
//    private void Rrun() {
//        if (!Data.newRedoCmd(_inventory).run()) {
//            _ui.displayError("Command failed");
//        }
//    }
//    ////    ,
//    private void Trun() {
//        Comparator<Record> top10 = new Comparator<Record>() { // sorts from largest to smallest
//            @Override
//            public int compare(Record o1, Record o2) {
//                return ((o2.numRentals()) - (o1.numRentals()));
//            }
//        };
//        if (_inventory.size() <= 0){ // checks if inventory is empty
//            _ui.displayError("No videos in Inventory");
//        } else {
//            Iterator<Record> sortedInv = _inventory.iterator(top10); // get inventory sorted
//            int i = 1; // count up
//            String vidName, message = "";
//            while(i < 11){
//                if(!sortedInv.hasNext()){ // if there are no more videos in lineup, output blanks
//                    vidName = "";
//                } else {
//                    Record r = sortedInv.next(); // pull the record
//
//                    vidName = r.toString(); // pull video, then get String of video
//                }
//                message += i + ".)  " + vidName + " \n";
//                i++;
//            }
//            _ui.displayMessage(message); // display message of top 10
//        }
//    }
//    ////    ,
//    private void Irun() {
//        Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
//        Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
//        Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
//        Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
//        Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
//        Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
//        Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
//        Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
//        Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
//        Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
//        Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
//        Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
//        Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
//        Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
//        Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
//        Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
//        Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
//        Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
//        Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
//        Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
//        Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
//        Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
//        Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
//        Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
//        Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
//        Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
//    }
//}
