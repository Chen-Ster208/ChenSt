const express = require('express');
const request = require('request');
const fcm_push = require('fcm-push');
const node_fetch = require('node-fetch');
const bodyParser = require('body-parser');


const url_1 = 'https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=';
const url_2 = '&interval=2min&outputsize=full&apikey=QI8N8P4ZRKGOOGY4'
const KEY = "AAAA3lEGFXs:APA91bF50ymdIEUMBqr-H0YhLIjXTvfZ-2-_rjb6E4zTPpeVl2KS-P3g8l5lGcTBNNg09sFLSI0awQmbzH2xoriSEV3KC3ImmYBAO3VVvMwCc3_MdfX5s2JtHPaWlRLACEN0jM2hKOiG";

let app = express();
app.use(bodyParser.json());

let fcm = fcm_push(KEY);
let token = "";

app.post('/stocks/:stock', (req, res) => {
    let stockName = req.params['stock'];
    token = req.body.token;
    console.log ("Received stock and token required\n");
    console.log ("Updating ${stock} price every ");
    setInterval(() => {
        getDataFromAlpha(stockSymbol, (price, error) => {
            if(error){
                console.log("Error sending message:", error);
            } else {
                sendDataFCM(token, stockName, price);
            }
        })
    }, 3600);
    res.json({result: "Success!"});
});

app.post('/newToken/:token', (req, res) => {
    token = req.params['token'];
});

// Sends the price of the stock using FCM 
function sendDataFCM(token, stock, price) {
    fcm.send({
        to: token,
        data: {
            symbol: stock,
            price: price
        },
        notification: {
            title: `${stock} price update!!!`,
            body: `${stock}'s price is now: ${price}.\nSo, what are you gonna do?`
        }
    }, 
    (error, response) => {
        console.log("Got an error sending with FCM; ", error);
    });
}

// Handles the 'GET' request to AlphaVantage, using symbol provided
function getDataFromAlpha (symbol, answers) {
    // concatinate url using given symbol
    let url = new URL(url1 + symbol + url2);

    fetch(url , {method: 'GET'})
        .then(response => response.json())

        .then((data) => {
        //extract price from received json
        answers(data["Global Quote"]["05. price"]);
    })

    .catch((error) => {
        console.error('Error while getting data from AlphaVantage: ', error);
        // in case of error, data is empty
        answers("", error);
    });
}

app.listen(3000, () => {
    console.log('Example app listening on port 3000');
});

