Back-end

Clonage du projet

    - Sur la page GitHub du projet, cliquez sur le bouton Code et copiez le lien vers le dépôt.
    - Ouvrez un terminal et utilisez la commande suivante pour cloner le dépôt : git clone https://github.com/geo-mendo/chatop-api
    - Ouvrez le projet cloné dans IntelliJ IDEA.
    
Build et exécution du projet

    - Assurez-vous que Maven est installé sur votre machine. Pour vérifier, exécutez mvn -v dans votre terminal.
    - Dans IntelliJ, ouvrez la fenêtre Maven (généralement sur le côté droit de l'écran).
    - Dans la fenêtre Maven, dépliez Lifecycle, puis double-cliquez sur install pour construire le projet. Ceci téléchargera les dépendances nécessaires et compilera votre projet.
    - Pour exécuter le projet, recherchez la classe principale qui contient la méthode main (généralement annotée avec @SpringBootApplication) et cliquez sur le bouton Run à côté de la classe.
    
Base de données

Le projet comprend un fichier docker-compose.yml qui permet de construire et de lancer un conteneur Docker pour la base de données.
    - Assurez-vous que Docker est installé sur votre machine.
    - Dans un terminal, naviguez jusqu'au dossier contenant le fichier docker-compose.yml.
    - Exécutez la commande suivante pour démarrer le conteneur de la base de données : docker-compose up -d
    
Variables d'environnement

Le projet nécessite des variables d'environnement pour la connexion à la base de données. Voici comment les définir dans IntelliJ :
    - Allez dans Run -> Edit Configurations....
    - Sélectionnez votre classe principale dans la liste des configurations.
    - Dans l'onglet Configuration, trouvez la section Environment variables.
    - Cliquez sur l'icône de la liste, puis ajoutez les variables d'environnement suivantes DB_URL, DB_USERNAME, DB_PASSWORD avec les données correspondantes
    
    
Front-end

Clonage du projet

    - Sur la page GitHub du projet front-end, cliquez sur le bouton Code et copiez le lien vers le dépôt.
    - Ouvrez un terminal et utilisez la commande suivante pour cloner le dépôt : git clone https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring
    
 Build et exécution du projet

    - Assurez-vous que Node.js et npm (Node Package Manager) sont installés sur votre machine.
    - Naviguez jusqu'au dossier du projet cloné dans votre terminal.
    - Exécutez la commande suivante pour installer les dépendances du projet : npm install
    - Ensuite, lancez l'application Angular avec la commande : ng serve / npm start
    - Ouvrez votre navigateur web et allez à l'adresse http://localhost:4200 pour accéder à l'application.
