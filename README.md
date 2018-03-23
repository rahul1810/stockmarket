# Stock Market

This application is a basic stock market simulation. It loads content at startup, which are randomised through configuration file *application.properties*.

To run the application, take checkout and execute the below command from project directory

`mvn spring-boot:run`

## Web UI
http://localhost:7777/view

## Get List of Stocks
GET http://localhost:7777/api/stocks

## Update Stock
PUT http://localhost:7777/api/stocks/1

Request Body (application/json):
```
{
        "id": 189,
        "currentPrice": 39.34106
}
```
## Create Stock
POST http://localhost:7777/api/stocks/1

Request Body (application/json):
```
{
        "id": 1,
        "currentPrice": 39.34106,
        "name": "TEST"
}
```
