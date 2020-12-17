const express = require('express');
const bodyParser = require('body-parser');
let app = express();
app.use(bodyParser.json());

const fs = require('fs');
let tasksJS = { "asdf": 1234, 
                "asdf2": "lalala"};
                
saveToJSON(tasksJS);

app.get('/tasks', (req, res) => {
    fs.readFile('task_manager.json', (err, data) => {
        if (err) throw err;
        let tasks = JSON.parse(data);
        res.send(tasks);
    });

});

app.get('/tasks/new', (req, res) => {
    let id = req.query.id || "Null";
    let task = req.query.task || "None";
    console.log("the id is: " + id + ", and the task is: " + task);
    res.send("Task saved in id:" + id);
});

// app.get('/tasks/remove', (req, res) => {
//     let id = req.query.id;
//     id = parseInt(id, 10);
//     if (isNaN(id)) {
//         res.send("Invalid id number entered");
//     } else {
//         if (id doesn't exist...)
//     }
// });

app.listen(3000, () => {
    console.log('Example app listening on port 3000');
});


function saveToJSON(json) {
    let data = JSON.stringify(json);
    fs.writeFile('task_manager.json', data, (err) =>{
        if (err){
            throw err;
        }
        console.log("JSON saved");
    });
} 
