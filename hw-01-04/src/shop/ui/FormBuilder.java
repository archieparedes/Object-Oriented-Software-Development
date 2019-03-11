package shop.ui;

public interface FormBuilder {
    UIFormMenu toUIForm(String heading);
    void add(String prompt, UIFormTest test);
}
