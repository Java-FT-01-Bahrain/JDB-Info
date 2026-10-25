Feature: REST API functionalities

  Scenario: User able to manage books
    Given A list of books are available
    When I add a book to my reading list
    Then The book is added
    When I edit book from my reading list
    Then The book content is edited
    When I remove book from my reading list
    Then The book is removed