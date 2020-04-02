const mongoose = require('mongoose')
const validator = require('validator')

const Rating = mongoose.model('Rating', {
    userId: {
        type: String,
        required: true
    },
    userZoneId: {
        type: String,
        required: true
    },
    rating: {
        type: Number,
        required: true,
        validate(value) {
            if (!(value >= 1 && value <= 5)) {
                throw new Error('Rating must be between 1.0-5.0')
            }
        }
    }
})

module.exports = Rating