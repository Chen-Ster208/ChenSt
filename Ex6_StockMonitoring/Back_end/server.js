const express = require('express');
const request = require('request');
// const fs
url_1 = 'https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=';
url_2 = '&interval=2min&outputsize=full&apikey=QI8N8P4ZRKGOOGY4'

let app = express();

app.get('/stocks/:symbol', (req, res) => {
    console.log('getting');
    let stockSymbol = req.params['symbol'];
    url = url_1 + stockSymbol + url_2;
    request(url, function(err, res, body){
        console.log(body);
    })
});

app.listen(3000, () => {
    console.log('Example app listening on port 3000');
});

function saveToJSON(json) {
    let data = JSON.stringify(json, null, 4);
    fs.writeFile('stocks.json', data, (err) =>{
        if (err){
            throw err;
        }
        console.log("JSON saved");
    });
} 
