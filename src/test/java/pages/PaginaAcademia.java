package pages;

public class PaginaAcademia extends BasePage {

    private String elegirPlanButton = "//a[normalize-space()='Empezar hoy' and @href]";

    // Method to click on a specific course using the defined locator
    public void goToElegirPlan() {
        clickElement(elegirPlanButton);
    }

}