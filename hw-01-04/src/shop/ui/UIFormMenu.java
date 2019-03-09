package shop.ui;

public final class UIFormMenu implements UI_FM_Interface {
    private final String _heading;
    private final Pair[] _t;

    static final class Pair <T> {
        String prompt;
        UIMenuAction action = null;
        UIFormTest test = null;

        Pair(String thePrompt, T t) {
            prompt = thePrompt;

            if (t instanceof UIMenuAction) {
                action = (UIMenuAction) t; // void
            } else if (t instanceof UIFormTest) {
                test = (UIFormTest) t; // boolean
            }
        }
    }

    UIFormMenu(String heading, Pair[] t){
        _heading = heading;
        _t = t;
    }

    public int size() {
        return _t.length;
    }
    public String getHeading() {
        return _heading;
    }
    public String getPrompt(int i) {
        return _t[i].prompt;
    }

    public boolean checkInput(int i, String input) {
        if (null == _t[i])
            return true;
        return _t[i].test.run(input);
    }

    public void runAction(int i) {
        _t[i].action.run();
    }

}
