package pages;

import java.util.List;
import static utils.WebElements.*;

public class PaginaRegistro extends BasePage {

    // Method to get the text of all radio buttons within the registration form
    public List<String> getRadioButtonTexts() {
        return getRadioButtonTexts(CHECKOUT_SUMMARY_FORM);
    }
}
