const mongoose = require("mongoose")

const taskSchema = new mongoose.Schema(
    {
        
        taskType: String,
        title:  String,
        description: String,
        suburb:String,
        date:String,
        budget:Number,
        budgetType: String,
        avatar: String

    }
)

module.exports  =  mongoose.model("Task", taskSchema)
