const express = require("express")
const bodyParser = require("body-parser")
const https = require("https")
const mongoose = require("mongoose")
const cors = require("cors");
const Task = require("./models/Task");
const fs = require("fs");


const app = express()
app.use(express.static('www'));
app.use(bodyParser.urlencoded({extended:true, limit: '50mb'}))
app.use(cors())
app.use(bodyParser.json({limit: '50mb'}))


// mongoose
mongoose.connect("mongodb://localhost:27017/iServerDB", {useNewUrlParser: true, useUnifiedTopology: true})


//home route
app.get ('/', (req, res)=>{
   console.log('keyword='+req.query.keyword)
  Task.find({ $or:[{
    date: new RegExp(req.query.keyword)
  },{
    title: new RegExp(req.query.keyword)
  },{
    suburb: new RegExp(req.query.keyword)
  }] },function(err, data){
    // console.log(err,data)
    if(!err){
       res.json(data)
    }else{
      res.json(err)
    }
  })
  
})

//task route
app.post('/task', (req,res)=>{
  const task = new Task({
    taskType: req.body.taskType,
    title: req.body.title,
    description: req.body.description,
    suburb:req.body.suburb,
    date:req.body.date,
    budget:req.body.budget,
    budgetType: req.body.budgetType,
    avatar: req.body.avatar,
    });
    task.save()
    .catch((err) => console.log(err));
    res.json(('saved to db: ' + task)); 
    res.json('you have post your task successfully!')
    
})
// delete task
app.get('/delete', (req, res)=>{
  Task.remove({
    _id: req.query.id
  }, (err, data)=>{
    res.json(err?{
      msg: 'delete fail'
    }:{
      msg: 'delete success'
    })
  })
  console.log(req.query.id)
})
app.post('/upload', (req,res)=>{
  var filename = new Date().getTime() + Math.floor(Math.random()*1000) + '.jpg';
  fs.writeFile('www/imgs/'+filename,new Buffer(req.body.file,'base64'),(err)=>{
    res.json(err?{
      msg: 'upload fail'
    }:{
      src: 'imgs/'+filename,
      msg: 'upload success'
    })
  } );

})
//login route
// app.post('/login', (req,res)=>{
//     User.findOne({ username: req.body.username},(error,user)=>{
//       if(user!= null){
//           if(!user.password.localeCompare(req.body.password)){
//               res.json('success');
//           }
//           else{
//               res.json('Password is wrong!')
//           }
//       }else{
//         res.json('Username is not registered!')
//       }
//   });
// });



let port = process.env.PORT;
if (port == null || port == "") {
  port = 5000;
}

app.listen(port, (req,res)=>{
    console.log("Server is running successfullly!")
})
