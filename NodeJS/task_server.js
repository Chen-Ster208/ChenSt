const fs = require('fs');
const express = require('express');
const bodyParser = require('body-parser');
let app = express()

let tasksJS = { };

saveToJSON(tasksJS);


app.get('/tasks', (req, res) => {
        let tasks = readJSON();

        console.log(tasks);
        res.send(tasks);
});

app.get('/tasks/new', (req, res) => {
    let tasks = JSON.parse(readJSON());
    let newTask = req.query.task || "None";
    let id = req.query.id || "NaN";

    id = parseInt(id, 10);

    if (isNaN(id)) {
        res.send("Invalid id number entered");

    } else {
        if (!tasks.hasOwnProperty(id)) {
            tasks[id] = newTask;            
            saveToJSON(tasks);

            console.log("added new task\nid: " + id + ", task: " + newTask);
            res.send("new task succefully added, id " + id);

        } else {
            res.send("id '" + id + "' already exists, choose a new one");
        }
    }
});

app.get('/tasks/remove', (req, res) => {
    let tasks = JSON.parse(readJSON());
    let id = req.query.id;
    
    id = parseInt(id, 10);

    if (isNaN(id)) {
        res.send("Invalid id number entered");

    } else {
        delete tasks[id]
        res.send("removed task with id :" + id);
    }

    saveToJSON(tasks);
});

app.listen(3000, () => {
    console.log('Example app listening on port 3000');
});



//helper function - saves file in .json format
function saveToJSON(json) {
    let data = JSON.stringify(json, null, 4);
    fs.writeFile('task_manager.json', data, (err) =>{
        if (err){
            throw err;
        }
        console.log("JSON saved");
    });
} 

//helper function - reads 'task_manager' file
function readJSON() {
    return fs.readFileSync('task_manager.json', 'utf-8');
}
