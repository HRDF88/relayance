Feature: Ajout d'un nouveau client

  En tant que Commercial
  Je veux pouvoir ajouter un(e) nouveau/nouvelle client(e) avec son nom et son email
  Afin de suivre mes propositions commerciales

  Scenario: Ajouter un client avec succes
    Given je suis sur l'écran d'ajout d'un client
    When je saisis "John Doe" comme nom
    And je saisis "johndoe@gmail.com" comme email
    And je valide l'ajout
    Then le client "John Doe" apparaît dans la liste des clients

  Scenario: Ajouter un client avec un email invalide
    Given je suis sur l'écran d'ajout d'un client
    When je saisis "Jane Doe" comme nom
    And je saisis "janedoe@invalid" comme email
    And je valide l'ajout
    Then un message d'erreur "Format d'email invalide" est affiché

  Scenario: Ajouter un client avec un email vide
    Given je suis sur l'écran d'ajout d'un client
    When je saisis "Empty Email" comme nom
    And je saisis "" comme email
    And je valide l'ajout
    Then un message d'erreur "Format d'email invalide" est affiché