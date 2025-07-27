package pages;

import java.util.List;

public class PaginaRegistro extends BasePage {

    private String radioButtonSelected = "//form[@id='checkout-summary-form']";

    // Method to get the text of all radio buttons within the registration form
    public List<String> getRadioButtonTexts() {
        return getRadioButtonTexts(radioButtonSelected);
    }
}
