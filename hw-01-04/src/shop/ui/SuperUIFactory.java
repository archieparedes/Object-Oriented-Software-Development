package shop.ui;
// this could be inside UIFactory
final public class SuperUIFactory {
    public SuperUIInterface launch(String i, String head, UIFormMenu.Pair[] t){
        switch(i){
            case SuperUIInterface.popup:
                return new PopupUI();

            case SuperUIInterface.textui:
                return new TextUI();

            case SuperUIInterface.UIFormBuilder:
                return new UIFormBuilder();

            case SuperUIInterface.UIFormMenu:
                return new UIFormMenu(head, t);

            case SuperUIInterface.UIMenuBuilder:
                return new UIMenuBuilder();

            default:
                return null;
        }
    }

    static private UI _UI = new PopupUI();
    //static private UI _UI = new TextUI();
    static public UI ui () {
        return _UI;
    }
}
