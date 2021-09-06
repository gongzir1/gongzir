const mongoose = require("mongoose")
const expertSchema = new mongoose.Schema(
    {
        country: {
            type: String,
            required:'Please enter your country'
        },
        firstname: {
            type: String,
            required:'Please enter your firstname'
        },
        lastname: {
            type: String,
            required:'Please enter your lastname'
        },
        email: {
            type: String,
            required:'Please enter your email'
        },
        password: {
            type: String,
            required:'Please enter your password'
        },
        address: {
            type: String,
            required:'Please enter your address'
        },
        city: {
            type: String,
            required:'Please enter your city'
        },
        state: {
            type: String,
            required:'Please enter your state'
        },
        zip: {
            type: Number,
            required:'Please enter your zip'
        },
        phone: {
            type: String,
            required:'Please enter your phone'
        },
        creation_date: {
            type: Date,
            default: Date.now
        }
    }
)

module.exports = mongoose.model("Expert", expertSchema);