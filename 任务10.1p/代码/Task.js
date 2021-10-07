const mongoose = require("mongoose")

const taskSchema = new mongoose.Schema(
    {
        
        title:  String,
        description: String,
        suburb:String,
        date:String,
        budget:Number,
    }
)

module.exports  =  mongoose.model("Task", taskSchema)
