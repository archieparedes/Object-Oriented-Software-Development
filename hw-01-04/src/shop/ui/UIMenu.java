//package shop.ui;
////
/////**
//// * @see UIMenuBuilder
//// */
//public final class UIMenu {
//  private final String _heading;
//  private final Pair[] _menu;
//
//  static final class Pair <T>{
//    String prompt;
//    UIMenuAction action = null;
//    UIFormTest test = null;
//
//    Pair(String thePrompt, T t) {
//        prompt = thePrompt;
//
//        if(t instanceof UIMenuAction){
//            System.out.println("menu found");
//            action = (UIMenuAction) t; // void
//        } else if (t instanceof UIFormTest){
//            test = (UIFormTest) t; // boolean
//        }
//    }
//  }
//
//  UIMenu(String heading, Pair[] menu) {
//    _heading = heading;
//    _menu = menu;
//  }
//  public int size() {
//    return _menu.length;
//  }
//  public String getHeading() {
//    return _heading;
//  }
//  public String getPrompt(int i) {
//    return _menu[i].prompt;
//  }
//  public void runAction(int i) {
//    _menu[i].action.run();
//  }
//}
