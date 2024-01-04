# OrderCraft - [Tests Unitaires](https://github.com/mohatala/CraftWood)

## Contexte du projet
Pour garantir le succès de la migration de notre application, nous devons impérativement intégrer des tests unitaires dans le processus de développement.<br/>
Ces tests assurent la fiabilité des nouvelles fonctionnalités, détectent les erreurs précocement, facilitent la maintenance future et optimisent les performances.<br/>
Leur intégration est essentielle pour assurer la qualité et la pérennité de notre application.<br/><br/>

<b>Stack Technique de l'Application "OrderCraft" (Spring Core MVC avec Thymeleaf)</b><br/><br/>
Langage de Programmation : Java<br/>
Frontend : Thymeleaf pour les vues dynamiques<br/>
Backend : Spring Core ( IOC , DI ) - Spring MVC - Hibernate - Spring Data JPA<br/>
Gestion de Dépendances : Apache Maven<br/>
Base de Données : MySQL<br/>
Test : Junit 5<br/>
Serveur d'Application : Apache Tomcat<br/>
Gestion des tâches : Trello<br/>
Système de Gestion de Version : Git et Github<br/>
Logging : SLF4J (Simple Logging Facade for Java) pour la gestion des journaux**<br/>
## Documentation de Test avec Junit
## Conception <b>UML</b>
<b> Diagramme de Cas d'Utilisation:</b><br/>
![alt text](https://github.com/mohatala/CraftWood/blob/main/usecase%20Craft.drawio%20(1).png)<br/><br/>

<b> Diagramme de Class:</b><br/>
![alt text](https://github.com/mohatala/CraftWood/blob/main/Diagramme%20sans%20nom.drawio.png)

## Fonctionnalités de l'Application "<b>OrderCraft</b>" (Java Spring) pour la Gestion des Commandes d'ArtWood<br/>
<b>Ajout de Commandes :</b><br/>
Les utilisateurs peuvent ajouter de nouvelles commandes en spécifiant les détails tels que le client, les articles commandés, et la date de la commande.<br/><br/>
![alt text](https://github.com/mohatala/OrderCraft-Task/blob/master/interface%20nouveau%20commande.PNG)<br/><br/>
<b>Visualisation des Commandes en Cours :</b><br/>
Une interface conviviale basée sur Spring Core MVC et Thymeleaf permet aux utilisateurs de voir l'état d'avancement des commandes en cours, y compris les détails spécifiques de chaque commande.<br/><br/>
![alt text](https://github.com/mohatala/OrderCraft-Task/blob/master/interface%20list%20commande.PNG)<br/><br/>
<b> Pour afficher les informations d'une commande et Marquer les Commandes comme Complètes :<b><br/><br/>
Les utilisateurs ont la possibilité de marquer les commandes comme complètes une fois qu'elles ont été traitées. Cela permet de suivre facilement les commandes encore en attente.<br/><br/>
![alt text](https://github.com/mohatala/OrderCraft-Task/blob/master/interface%20infos%20commande.PNG)<br/><br/><br/>
<b>Gestion des Clients :</b><br/>
Un module de gestion des clients permet aux utilisateurs de créer, afficher et mettre à jour les informations clients pour une gestion centralisée.<br/><br/>
<b> Pour ajouter un client :</b>
![alt text](https://github.com/mohatala/OrderCraft-Task/blob/master/interface%20nouveau%20client.PNG)<br/><br/>
<b> Pour afficher list des Clients :</b>
![alt text](https://github.com/mohatala/OrderCraft-Task/blob/master/interface%20List%20client.PNG)<br/><br/>
<b>Suivi des Stocks :</b><br/>
Intégration d'une fonctionnalité de suivi des stocks pour s'assurer que les articles nécessaires sont disponibles pour satisfaire les commandes.<br/><br/>
<b> Pour ajouter un article :</b>
![alt text](https://github.com/mohatala/OrderCraft-Task/blob/master/interface%20nouveau%20articles.PNG)<br/><br/>
<b> Pour afficher list des Articles :</b><br/>
![alt text](https://github.com/mohatala/OrderCraft-Task/blob/master/interface%20list%20articles.PNG)<br/><br/>
​

<b>Rapports d'Analyse des Ventes :</b>
Un module de génération de rapports offre aux utilisateurs la possibilité d'analyser les tendances de vente, les préférences des clients, et les performances des articles. Les rapports visuels facilitent la prise de décision stratégique pour optimiser les ventes et la gestion des stocks. Intégration d'une bibliothèque de reporting, telle que JasperReports ou Apache POI, pour la génération des rapports d'analyse des ventes. <br/><br/>






