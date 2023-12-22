# Petstore 
## Assignment
Onze klanten hebben al jarenlang een fysiek stenen dierenwinkel, gespecialiseerd in aquariums en tropische vissen. Om mee te gaan met hun tijd willen ze uitbreiden en gebruik maken van een webshop om klanten ook de mogelijkheid te geven van thuis uit te bestellen. Hun klanten hebben niet altijd de mogelijkheid om de winkel te bezoeken en er vallen anders heel wat aankopen weg.

We kregen de opdracht om als proof of concept een webapplicatie op te bouwen waar klanten gebruik van kunnen maken om uit een beperkte catalogus producten te kopen. Belangrijk hierbij is een mogelijkheid om een winkelmandje te vullen en een catalogus te raadplegen waar producten uit verschillende categorieën terug te vinden zijn. Andere nuttige extras mogen ook maar worden enkel bekeken indien alle core functionaliteit werkt.

Voor front-end waren er geen specifieke eisen, de back-end moet echter binnen een Java (Spring) omgeving gemaakt zijn aangezien hun ICT medewerker hier ervaring mee heeft. Voor grote problemen is David Van Steertegem ingehuurd als consultant. Het budget laat echter maar toe om hem één keer in te schakelen, meer kost 10% van de omzet.

## Functionele vereisten
- [x] Mogelijkheid om alle producten weer te geven in een catalogus
- [x] Mogelijkheid om enkel producten van een specifieke categorie weer te geven (voeding, decor, habitat), de voor jou relevante filters
- [x] Toevoegen product aan winkelmandje
- [x] Aankoop bevestigen (checkout systeem, mag beperkt blijven tot bevestigingspagina)
- [x] Een registratiesysteem voor users
- [x] Een veilig login systeem (oauth2, salting, bcrypt, je mag zelf kiezen wat je gebruikt zolang het veilig is)

Registratie en login wordt gebruik gemaakt van de Google Oauth2 API.

## Technologieen & Dependencies

- Maven
- spring-boot-starter-oauth2-client
- spring-boot-starter-security
- spring-boot-starter-thymeleaf
- spring-boot-starter-web
- spring-boot-starter-web-services
- thymeleaf-extras-springsecurity6
- spring-boot-starter-validation
- spring-boot-starter-data-jp

## Create .env file in project root

1. To create your own Google API credentials follow [this guide](https://developers.google.com/identity/openid-connect/openid-connect)
2. Paste following in file `.env` with correct credentials
```
MYSQLDB_USER=root
MYSQLDB_ROOT_PASSWORD=petStore1
MYSQLDB_DATABASE=petstore

MYSQLDB_LOCAL_PORT=3307
MYSQLDB_DOCKER_PORT=3306

SPRING_LOCAL_PORT=6868
SPRING_DOCKER_PORT=8080

PHPMYADMIN_LOCAL_PORT=3400
PHPMYADMIN_DOCKER_PORT=80

GOOGLE_OAUTH2_ID=example.apps.googleusercontent.com
GOOGLE_OAUTH2_SECRET=EXAMPLE-EXAMPLE-EXAMPLE
```

## How to run

1. go to project root where `docker-compose.yml` file is located
2. run `docker-compose up`
3. browse to `http://localhost:6868/`
4. for database access (phpmyadmin) you can go to `http://localhost:3400/`

A normal version of this project is created as well
`https://github.com/ehbstudentadam/petstore`

An [old repository](https://github.com/ehbstudentadam/petstore_old/commits/main/) of this project exists, but I have created a new one for security purposes. A bigger history of the project can be found there.

## References
 - Google (various sources)
 - https://www.baeldung.com/spring-security-5-oauth2-login
 - https://www.javadevjournal.com/spring-boot/loading-initial-data-with-spring-boot/
 - https://spring.io/guides/gs/validating-form-input/
 - https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.pdf
 - https://docs.spring.io/spring-security/reference/reactive/oauth2/login/advanced.html