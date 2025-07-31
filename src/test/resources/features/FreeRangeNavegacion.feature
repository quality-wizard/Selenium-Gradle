@free-range-navigation
@allure.label.owner:abasualtop
@allure.label.parentSuite:Cucumber
@allure.label.suite:FreeRangeNavigation
@allure.link.tms:TC-FR-NAV-001
@allure.link.issue:BUG-FRONT-117
Feature: FreeRangeNavigation

    To see the subpages
    Without logging in
    I Can click on the navigation bar links

    Background: I am on the Free Range Testers website
        Given I navigate to 'www.freerangetesters.com'

    @navigation @smoke
    @allure.label.severity:normal
    @allure.label.subSuite:subpages
    Scenario Outline: I can access the subpages through the navigation bar
        When I go to the <section> using the navigation bar
        Examples:
            | section   |
            | Cursos    |
            | Recursos  |
            | Talleres  |
            | Mentorías |
            | Blog      |

    @courses @ui
    @allure.label.severity:blocker
    @allure.label.subSuite:courses
    Scenario: Courses are presented correctly to potential customers
        When The user goes to the 'Cursos' using the navigation bar
        And I select Introducción al Testing

    @plans @regression
    @allure.link.tms:TC-FR-PLAN-01
    @allure.link.issue:FRONT-102
    @allure.label.severity:critical
    @allure.label.subSuite:suscription
    Scenario: User can select a plan when signing up
        When The client goes to the 'Academia' using the navigation bar
        And The user selects Empezar hoy
        Then The client can validate the options available in the checkout page