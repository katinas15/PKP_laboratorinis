const mongoose = require('mongoose')

const UserZone = mongoose.model('UserZone', {
    name: {
        type: String,
        required: true,
        trim: true
    },
    point: {
        type: { x: Number, y: Number },
        required: true
    },
    description: {
        type: String,
        required: false,
        trim: true
    },
    timeStart: {
        type: String,
        required: false,
        trim: true
    },
    timeEnd: {
        type: String,
        required: false,
        trim: true
    },
    image: {
        type: String,
        required: false,
        trim: true
    },
    userId: {
        type: String,
        required: false,
        trim: true
    },
    kaina: {
        type: Number,
        required: false,
        trim: true
    }
})

module.exports = UserZone
