package pages;

/**
 * PaginaPrincipal class represents the main page of the Free Range Testers
 * website.
 * It extends BasePage to inherit common methods for interacting with web
 * elements.
 */
public class PaginaPrincipal extends BasePage {

    private String urlBase = "%s";
    private String sectionLink = "//a[normalize-space()=%s and @href]";

    // Method to navigate to the Free Range Testers homepage
    public void navigateToFreeRangeTesters(String url) {
        // Check if the URL starts with "http" and prepend "https://" if not
        if (!url.startsWith("http")) {
            url = "https://" + url;
        }
        // Format the URL with the base URL
        String urlPage = String.format(urlBase, url);
        navigateTo(urlPage);
    }

    // Method to click on a section in the navigation bar using the provided section
    public void goToSectionUsingNavigationBar(String section) {
        // Format the section link with the provided section name
        String xpathSection = String.format(sectionLink, section);
        clickElement(xpathSection);
    }

}
