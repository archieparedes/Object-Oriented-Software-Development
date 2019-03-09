//package shop.ui;
//
///**
// * @see UIFormBuilder
// */
//public final class UIForm {
//  private final String _heading;
//  private final Pair[] _form;
//
//static final class Pair <T>{
//    String prompt;
//    UIMenuAction action = null;
//    UIFormTest test = null;
//
//    Pair(String thePrompt, T t) {
//        prompt = thePrompt;
//
//        if(t instanceof UIMenuAction){
//            action = (UIMenuAction) t; // void
//        } else if (t instanceof UIFormTest){
//            System.out.println("form found");
//            test = (UIFormTest) t; // boolean
//        }
//    }
//}
//
//  UIForm(String heading, Pair[] menu) {
//    _heading = heading;
//    _form = menu;
//  }
//  public int size() {
//    return _form.length;
//  }
//  public String getHeading() {
//    return _heading;
//  }
//  public String getPrompt(int i) {
//    return _form[i].prompt;
//  }
//  public boolean checkInput(int i, String input) {
//    if (null == _form[i])
//      return true;
//    return _form[i].test.run(input);
//  }
//}
