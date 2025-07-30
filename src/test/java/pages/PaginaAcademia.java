package pages;

import static utils.WebElements.*;

public class PaginaAcademia extends BasePage {

    // Method to click on a specific course using the defined locator
    public void goToElegirPlan() {
        clickElement(ELEGIR_PLAN_BUTTON);
    }

}