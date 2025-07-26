Feature: Navigation bar

    To see the subpages
    Whitout logging in
    I Can click on the navigation bar links

    # Scenario Outline: I can access the subpages through the navigation bar
    #     Given I navitgate to www.freerangetesters.com
    #     When I go to the <section> using the navigation bar
    #     Examples:
    #         | section   |
    #         | Cursos    |
    #         | Recursos  |
    #         | Talleres  |
    #         | Mentorías |
    #         | Blog      |

    Scenario: Courses are presented correctly to potencial customers
        Given I navigate to www.freerangetesters.com
        When I go to the "Cursos" using the navigation bar
        And I select Introducción al Testing



