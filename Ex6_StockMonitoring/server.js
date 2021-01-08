const express = require('express');
const request = require('request');
// const fs
url_1 = 'https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=';
url_2 = '&interval=5min&apikey=QI8N8P4ZRKGOOGY4'

let app = express();

app.get('/stocks', (req, res) => {
    console.log('getting');
    url = url_1 + 'IBM' + url_2;
    request(url, function(err, res, body){
        // console.log('error:', err);
        // console.log('statusCode:', res && res.statusCode);
        // console.log(body);
        // console.log(typeof(body));
        // saveToJSON(body);
    })
});
//////////
app.get('/', (req, res) => {
    console.log('getting');
    url = 'http://newsapi.org/v2/top-headlines?' +
    'country=il&category=politics&' +
    'apiKey=7bc41e6caf5c49638c0977221c6e059a';
    request(url, function(err, res, body){
        // console.log('error:', err);
        // console.log('statusCode:', res && res.statusCode);
        // console.log(body);
        console.log(typeof(body));
        jsoned = JSON.parse(body);
        console.log(typeof(jsoned));
        articles = jsoned['articles'];
        articles.forEach(element => {
            console.log(element['title']);
        });
        // console.log(jsoned[0]);
        // saveToJSON(body);
    })
});
///////////
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
