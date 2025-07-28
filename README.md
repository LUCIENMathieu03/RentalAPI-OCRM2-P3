# RentalAPI-OCRM2-P3

API de gestion de Rental développée en Java et Spring Boot.

Lien application frontend : https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring.git

## Pré-requis

- **Java 17** (version recommandée : 17.x)
- **Maven 3.8+**
- **MySQL** (version 8.x conseillée) installé et fonctionnel sur ta machine
- **Git**

## 1. Cloner le projet depuis GitHub

Ouvre un terminal, puis :

```
git clone https://github.com/LUCIENMathieu03/RentalAPI-OCRM2-P3.git
cd RentalAPI-OCRM2-P3
```
## 2. Installer la base de données

**Étape 1 : Récupérer le script SQL**

Récupère le fichier [`script.sql`](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring/blob/main/ressources/sql/script.sql)
du dossier [`ressources/sql/`](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring/tree/main/ressources/sql/) du dépôt GitHub du projet front.

**Étape 2 : Créer la base de données dans MySQL**

1. **Ouvre l'invite de commande MySQL** :

```
mysql -u root -p (si tu utilise un terminal)
```

_Remplace `root` par ton nom d'utilisateur MySQL si besoin._


2. **Crée la base de données si ce n’est pas déjà fait** :
```
CREATE DATABASE nom_de_la_base_de_donnees;
```

3. **Place-toi dans le dossier où tu as téléchargé script.sql** :
```
cd chemin/ou/est/script.sql
```

4. **Importe le script SQL** :

En ligne de commande système :
```
mysql -u root -p nom_de_la_base_de_donnees < /chemin/vers/script.sql
```

- Remplace `nom_de_la_base_de_donnees` par le nom réel de ta base.
- Modifie `/chemin/vers/script.sql` avec le chemin où tu as téléchargé le script.


## 3. Configuration de l’environnement (.env)

Crée un fichier nommé `.env` à la racine du projet avec le contenu suivant :

```
DB_USERNAME=ton_nom_utilisateur_bdd
DB_PASSWORD=ton_mot_de_passe_bdd
DB_URL=jdbc:mysql://localhost:xxxx/nom_de_la_base_de_donnees
JWT_SECRET=une_chaine_de_caracteres_secrete_longue
SERVER_PORT=3001
BASE_URL=http://localhost:3001
```

| Variable       | Description                                                                                               |
|----------------|----------------------------------------------------------------------------------------------------------|
| `DB_USERNAME`  | Identifiant de connexion à la base de données MySQL.                                                     |
| `DB_PASSWORD`  | Mot de passe de connexion à la base de données MySQL.                                                    |
| `DB_URL`       | URL de connexion JDBC vers la base de données MySQL.<br>Exemple : `jdbc:mysql://localhost:3306/rentalocrm2` |
| `JWT_SECRET`   | Clé secrète utilisée pour signer et vérifier les tokens JWT (garde-la confidentielle !).                 |
| `SERVER_PORT`  | Port sur lequel le serveur backend écoute (ex. : `3001` pour accès à `http://localhost:3001`).           |
| `BASE_URL`     | URL de base de l’API (doit correspondre au port configuré dans `SERVER_PORT`).                           |

**Important** : Ne commit jamais ce fichier `.env` dans le dépôt Git, car il contient des informations sensibles.

## 4. Installer et lancer l’API localement

1. Vérifie les versions installées :

```
java -version
mvn -version
```

Assure-toi que Java 17 et Maven 3.8 ou plus sont installés.

2. Compile et installe les dépendances du projet :

```
mvn clean install
```

3. Démarre l’application :

```
mvn spring-boot:run
```

4. L’API sera disponible à :  
   [http://localhost:3001](http://localhost:3001)

## 5. Documentation de l’API

La documentation interactive Swagger UI est accessible ici quand l'api est lancé:  
[http://localhost:3001/swagger-ui.html](http://localhost:3001/swagger-ui.html)

## 6. Notes supplémentaires

- Gère tes secrets (comme JWT_SECRET, DB_PASSWORD) avec précaution.
- Pour toute modification, commits et push, vérifie que `.env` n’est jamais ajouté.
- En cas de questions ou d’aide, n’hésite pas à ouvrir une issue sur GitHub.

---