const mongoose = require('mongoose')

const Rating = mongoose.model('Rating', {
    userId: {
        type: Number,
        required: true
    },
    userZoneId: {
        type: Number,
        required: true
    },
    rating: {
        type: Number,
        required: true
    }
})

module.exports = Rating