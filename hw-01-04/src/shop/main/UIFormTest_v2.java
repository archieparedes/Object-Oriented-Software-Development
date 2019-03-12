package shop.main;

import shop.ui.UIFormTest;

enum UIFormTest_v2 {
    NUMBERTEST(new numberTest()), STRINGTEST(new stringTest()), YEARTEST(new yearTest());

    private UIFormTest u;

    UIFormTest_v2(UIFormTest uift){
        this.u = uift;
    }

    public UIFormTest go(){
        return u;
    }

    static class yearTest implements UIFormTest{
        public boolean run(String input) {
            try {
                int i = Integer.parseInt(input);
                return i > 1800 && i < 5000;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    static class numberTest implements UIFormTest{
        public boolean run(String input) {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    static class stringTest implements UIFormTest{
        public boolean run(String input) {
            return ! "".equals(input.trim());
        }
    }
}
