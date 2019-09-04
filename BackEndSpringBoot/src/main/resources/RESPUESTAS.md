INFORMACION PARA LOS SERVICIOS REST
-----------------------------------

Para mas información desde localhost ingresar: 
http://localhost:8080/tecso/swagger-ui.html

Server context: /tecso
Port: 8080

<-----ACCOUNT----->
Create an account service
HTML VERB: POST
OBJECT:
{
	"accountNumber": "3312341234",
	"currencyType": "PESO",   // Enums: DOLAR, EURO, PESO
	"balance": 0              
}
URL: /account/create
-------------------------------

Get all acounts service
HTML VERB: GET
URL: /account/getAllAccounts
-------------------------------

Delete an account service
HTML VERB: DELETE

URL: /account//delete/{id}
<-----ACCOUNT----->



<-----ACCOUNT MOVEMENT----->
Create an account movement service
HTML VERB: POST
OBJECT:
{
	"accountNumber": "3312341234",
	"currencyType": "PESO",   // Enums: DOLAR, EURO, PESO
	"balance": 0              
}
URL: /accountMovement/create
-------------------------------

Get all acounts movements by account service
HTML VERB: GET
URL: /accountMovement/getAllAccountMovements

<-----ACCOUNT MOVEMENT----->
