const express = require('express');
const bodyParser = require('body-parser');
let app = express();
app.use(bodyParser.json());

app.get('/hello', (req, res) => {
    let name = req.query.name ||"<unknown>";
    res.send('Hello there '+ name);
});

app.post('/hello', (req, res) => {
    let name = req.body.name ||"<unknown>";
    req.send('Hello there post user ${name}');
});

app.listen(3000, () => {
    console.log('Example app listening on port 3000');
});