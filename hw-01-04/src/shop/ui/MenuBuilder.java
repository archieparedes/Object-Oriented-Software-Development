package shop.ui;

public interface MenuBuilder {
    UIFormMenu toUIMenu(String heading);
    void add(String prompt, UIMenuAction action);
}
