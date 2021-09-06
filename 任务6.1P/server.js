const express = require("express")
const bodyParser = require("body-parser")
const mongoose = require("mongoose")
const Expert = require("./models/Expert.js")
const app = express();


app.use(bodyParser.urlencoded({extended:true}));
mongoose.connect("mongodb://localhost:27017/expertDB",{useNewUrlParser: true})

app.post('/experts',(req,res)=>{
    const expert=new Expert(
    {country,firstname,lastname,email,password,re_password,address,city,state,zip,phone}=req.body)
    expert.save((err)=>{
    if(err){res.send(err)}
    else res.send('successfully added a new expert!')}
    )
})
app.get('/experts',(req,res)=>{
    Expert.find((err,taskList)=>{
        if(!err){res.send(taskList);}
        else
        {res.send(err);}
    })
})
app.delete('/experts',(req,res)=>
{
    Expert.deleteMany((err)=>{
        if(err)
        {res.send(err)}
        else
        {res.send('Successfully all experts deleted!')}
    });
});

app.route('/experts')
.get( (req, res)=>{
    Expert.find((err, taskList)=>{
        if (err) {res.send(err)}
        else {res.send(taskList)}
    })
})
.post( (req,res)=>{
    const expert=new Expert(
        {country,firstname,lastname,email,password,re_password,address,city,state,zip,phone}=req.body)
    expert.save((err) =>{
        if (err) {res.send(err)}
        else res.send ('Successfully added a new expert!')
    }
    )
})
.delete((req,res) =>{
    Expert.deleteMany((err) =>{
        if (err) {res.send(err)}
        else {res.send('Successfully deleted all experts!')}
    })
})

app.route('/experts/:email')
.get((req, res)=>{
    Expert.findOne({email: req.params.email}, (err, foundExperts)=>{
        if (foundExperts) (res.send(foundExperts))
        else res.send("No Matched Expert Found!")
    })
})
.put((req,res)=>{
Expert.update(
    //{task_name: req.params.tname},
   // {task_name: req.body.name},
   {country,firstname,lastname,email,password,re_password,address,city,state,zip,phone}=req.body,
   {country,firstname,lastname,email,password,re_password,address,city,state,zip,phone}=req.params,
    {overwrite:true}, 
    (err)=>{
        if (err) {res.send(err)}
        else {res.send('Successfully updated!')}
    }
)
})
.delete((req,res) =>{
    Expert.deleteOne((err) =>{
        if (err) {res.send(err)}
        else {res.send('Successfully deleted the experts!')}
    })
})

.patch((req, res)=>{
    Expert.update(
        {country,firstname,lastname,email,password,re_password,address,city,state,zip,phone}=req.body,
        {country,firstname,lastname,email,password,re_password,address,city,state,zip,phone}=req.params,
        (err)=>{
            if (!err) {res.send('Successfully updated! ')}
            else res.send(err)
        }
    )
})

app.listen(process.env.PORT || 8000, ()=>{
    console.log('Server started on port 8000');
})