package pages;

public class PaginaCursos extends BasePage {

    private String fundamentosTestingLink = "//h3[normalize-space()='Introducción al Testing de Software']";

    // Method to click on a specific course using the defined locator
    public void goToFundamentosTestingLink() {
        clickElement(fundamentosTestingLink);
    }

}
