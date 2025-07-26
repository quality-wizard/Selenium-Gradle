package pages;

/**
 * PaginaPrincipal class represents the main page of the Free Range Testers
 * website.
 * It extends BasePage to inherit common methods for interacting with web
 * elements.
 */
public class PaginaPrincipal extends BasePage {

    private String sectionLink = "//a[normalize-space()='%s' and @href]";

    // Method to navigate to the Free Range Testers homepage
    public void navigateToFreeRangeTesters() {
        navigateTo("https://www.freerangetesters.com");
    }

    // Method to click on a section in the navigation bar using the provided section
    public void goToSectionUsingNavigationBar(String section) {
        // Format the section link with the provided section name
        String xpathSection = String.format(sectionLink, section);
        clickElement(xpathSection);
    }

}
