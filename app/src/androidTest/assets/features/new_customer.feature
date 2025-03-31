Feature: Ajout d'un nouveau client

  En tant que Commercial
  Je veux pouvoir ajouter un(e) nouveau/nouvelle client(e) avec son nom et son email
  Afin de suivre mes propositions commerciales

  Scenario: Ajouter un client avec succès
    Given je suis sur l'écran d'ajout d'un client
    When je saisis "John Doe" comme nom
    And je saisis "johndoe@gmail.com" comme email
    And je valide l'ajout
    Then le client "John Doe" apparaît dans la liste des clients