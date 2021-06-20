# 3semesterProgrammering


Der er en data.sql fil med test data som køres automatisk.
Der bruges H2 database.

JPA:
Copypaste i browser for at se side med sogne: http://localhost:8080/sogne
Copypaste i browser for at se side med smittetryk: http://localhost:8080/smittetryk

Database:
Copypaste i browser for at se database: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password:

REST-> JSON texter til test

GET: http://localhost:8080/restsogne
DELETE: http://localhost:8080/restsogne/1

POST: http://localhost:8080/restsogne
{
        "sogneKode": 8521,
        "sogneNavn": "sognNavn11",
        "smittetryk": 10.0,
        "nedlukDato": "2021-12-31T23:00:00.000+00:00",
        "checked": false,
        "kommune": {
            "id": 3,
            "navn": "kommuneNavn3"}
        }
        
PUT: http://localhost:8080/restsogne/2

{
        "sogneKode": 1199,
        "sogneNavn": "sognNavn99",
        "smittetryk": 10.0,
        "nedlukDato": "2021-12-31T23:00:00.000+00:00",
        "checked": false,
        "kommune": {
            "id": 3,
            "navn": "kommuneNavn3"}
        }






