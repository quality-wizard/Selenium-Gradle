@free-range-navigation
Feature: Navigation bar

    To see the subpages
    Whitout logging in
    I Can click on the navigation bar links

    Background: I am on the Free Range Testers website
        Given I navigate to 'www.freerangetesters.com'

    Scenario Outline: I can access the subpages through the navigation bar
        When I go to the '<section>' using the navigation bar
        Examples:
            | section   |
            | Cursos    |
            | Recursos  |
            | Talleres  |
            | Mentorías |
            | Blog      |

    @courses
    Scenario: Courses are presented correctly to potencial customers
        When I go to the 'Cursos' using the navigation bar
        And I select Introducción al Testing

    @plans @courses
    Scenario: User can select a plan when singing up
        When I go to the 'Academia' using the navigation bar
        And The user selects Empezar hoy
        Then The client can validate the options available in the checkout page

