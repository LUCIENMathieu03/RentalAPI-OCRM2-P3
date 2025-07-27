# RentalAPI-OCRM2-P3

API de gestion de Rental développée en Java et Spring Boot.

## Pré-requis

- **Java 17** (version recommandée : 17.x)
- **Maven 3.8+**
- **Git**

## 1. Cloner le projet depuis GitHub

Ouvre un terminal, puis :

```
git clone https://github.com/LUCIENMathieu03/RentalAPI-OCRM2-P3.git
cd RentalAPI-OCRM2-P3
```

## 2. Configuration de l’environnement (.env)

Crée un fichier nommé `.env` à la racine du projet avec le contenu suivant :

```
DB_USERNAME=ton_nom_utilisateur_bdd
DB_PASSWORD=ton_mot_de_passe_bdd
JWT_SECRET=une_chaine_de_caracteres_secrete_longue
BASE_URL=http://localhost:3001
```

- **DB_USERNAME** et **DB_PASSWORD** : tes identifiants pour la connexion à ta base de données.
- **JWT_SECRET** : une clé secrète utilisée pour signer les tokens JWT.
- **BASE_URL** : URL de base de l’API (ici `http://localhost:3001` pour un environnement local).

**Important** : Ne commit jamais ce fichier `.env` dans le dépôt Git, car il contient des informations sensibles.

## 3. Installer et lancer l’API localement

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

## 4. Documentation de l’API

La documentation interactive Swagger UI est accessible ici :  
[http://localhost:3001/swagger-ui.html](http://localhost:3001/swagger-ui.html)

## 5. Notes supplémentaires

- Gère tes secrets (comme JWT_SECRET, DB_PASSWORD) avec précaution.
- Pour toute modification, commits et push, vérifie que `.env` n’est jamais ajouté.
- En cas de questions ou d’aide, n’hésite pas à ouvrir une issue sur GitHub.

---