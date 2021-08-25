//var mongoose = require('./db')
let mongoose=require('mongoose')
let Schema=mongoose.Schema
let UserSchema=new Schema({
    country:{
        type:String,
        required:true
    },
    firstname:{
        type:String,
        required:true,
    },
    lastname:{
        type:String,
        required:true,
    },
    phone:{
        type:Number,
        required:true,
    },
    email:{
        type:String,
        required:true,
        unique:true
},
    password:{
        type:String,
        required:true
    },
    address:{
        type:String,
        required:true
    },
    city:{
        type:String,
        required:true
    },
    state:{
        type:String,
        required:true
    },
    zip:{
        type:Number,
        required:true
    },
    date:{
        type:Date,
        default:Date.now()
    },
    enable_flag:{
        type:String,
        default:'Y'
    }
    
})
//var UserSchema = mongoose.Schema
module.exports=mongoose.model('User',UserSchema,'users');