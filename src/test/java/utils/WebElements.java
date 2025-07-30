package utils;

/**
 * WebElements class contains common web element locators or methods
 * that can be reused across different page objects.
 */
public class WebElements {

    // # Home Page Locators
    public static final String HOME_URL = "%s"; // Será interpolada en tiempo de ejecución
    public static final String HOME_SECTION_LINK = "//a[normalize-space()='%s' and @href]";

    // # Cursos Page Locators
    public static final String FUNDAMENTOS_TESTING_LINK = "//h3[normalize-space()='Introducción al Testing de Software']";

    // # Academia Page Locators
    public static final String ELEGIR_PLAN_BUTTON = "//a[normalize-space()='Empezar hoy']";

    // # Registro Page Locators
    public static final String CHECKOUT_SUMMARY_FORM = "//form[@id='checkout-summary-form']";
}
