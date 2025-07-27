package steps;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PaginaAcademia;
import pages.PaginaCursos;
import pages.PaginaPrincipal;
import pages.PaginaRegistro;

/**
 * FreeRangeSteps class contains step definitions for navigating the Free Range
 * Testers website.
 * It uses the PaginaPrincipal page object to interact with the homepage and
 * perform actions.
 */
public class FreeRangeSteps {
    // SoftAssertions instance to collect assertion failures without stopping
    // execution
    SoftAssertions soft = new SoftAssertions();

    // Page objects for different sections of the Free Range Testers website
    PaginaPrincipal landingPage = new PaginaPrincipal();
    PaginaCursos cursosPage = new PaginaCursos();
    PaginaAcademia academiaPage = new PaginaAcademia();
    PaginaRegistro registroPage = new PaginaRegistro();

    @Given("I navigate to {string}")
    public void navigateToFreeRangeTesters(String url) {
        landingPage.navigateToFreeRangeTesters(url);
    }

    @When("I go to the {word} using the navigation bar")
    public void navigationBarUse(String section) {
        landingPage.goToSectionUsingNavigationBar(section);
    }

    @And("I select Introducción al Testing")
    public void selectIntroduccionAlTesting() {
        cursosPage.goToFundamentosTestingLink();
    }

    @And("I select Empezar hoy")
    public void selectEmpezarHoy() {
        academiaPage.goToElegirPlan();
    }

    @Then("I can validate the options available in the checkout page")
    public void validateCheckoutOptions() {
        List<String> lista = registroPage.getRadioButtonTexts();
        List<String> listaEsperada = List.of("$16.99/m", "$176/a");

        // Assert that the expected options are present in the checkout page
        Assertions.assertEquals(lista, listaEsperada);

    }

    public void EjemplosAssertions() {
        // Example assertions
        String palabraEsperada = "Pepe";
        String palabraEncontrada = "Pepa";

        Integer numeroEsperado = 10;
        Integer numeroEncontrado = 10;

        // Assertions to validate conditions
        Assertions.assertEquals(numeroEsperado, numeroEncontrado);

        // Assertions to validate conditions are not equal
        Assertions.assertNotEquals(palabraEsperada, palabraEncontrada);

        // Assertions to validate conditions are equal
        Assertions.assertEquals(palabraEsperada, palabraEncontrada);

        // Assertions to validate that a condition is true
        Assertions.assertTrue(palabraEncontrada.contains(palabraEsperada));

        // Assertions to validate that a condition is false
        Assertions.assertFalse(palabraEncontrada.contains(palabraEsperada));

        // Soft assertions to validate conditions
        // This not stops execution on failure
        // Ideally to verify multiple tiny assertions at the same time
        soft.assertThat(palabraEsperada).isEqualTo(palabraEncontrada);
        soft.assertThat(palabraEncontrada).contains(palabraEsperada);
        soft.assertThat(palabraEncontrada).isNotEqualTo(palabraEsperada);

        soft.assertAll(); // This will throw an exception if any soft assertion failed
    }

}
