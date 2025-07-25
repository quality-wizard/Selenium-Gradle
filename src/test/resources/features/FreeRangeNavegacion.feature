Feature: Navigation bar

    To see the subpages
    Whitout logging in
    I Can click on the navigation bar links

    Scenario Outline: I can access the subpages through the navigation bar
        Given I am on the Free Range Testers homepage
        When I go to the <section> using the navigation bar
        Examples:
            | section   |
            | Cursos    |
            | Recursos  |
            | Talleres  |
            | Mentorías |
            | Blog      |


