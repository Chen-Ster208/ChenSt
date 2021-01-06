const express = require('express');
const fStream = require('fs');

// const __path = 'Resources/';

let app = express();
app.use(express.static('./Resources'))
let path = require('path');

//If user didn't input file name
app.get('/server/files/', (req, res) => {
    res.send('input file name');
});

app.get('/server/files/:file', (req, res) => {
    let file = req.params.file || null;

    if(!isValid(file)){
        return;
    }

    let fPath = path.join('./Resources/', file);

    let myReadStream = fStream.createReadStream(fPath);

    console.log('piping from file: ' + file);

    myReadStream.pipe(res);
});

app.listen(3000, () => {
    console.log('Example app listening on port 3000');
});



//helper function to check validity and existence of file
function isValid(file){

    if(!file){ 
        res.send("Invalid file name");
        return false; 
    }

    let fPath = path.join('./Resources/', file);

    if(!(fStream.existsSync(fPath))){ 
        res.send("No such file exists :(");
        return false;
    }

    return path;
}
