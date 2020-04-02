const mongoose = require('mongoose')

const MainZone = mongoose.model('MainZone', {
    name: {
        type: String,
        required: true,
        trim: true
    },
    color: {
        type: String,
        required: true,
        trim: true
    },
    bounds: {
        type: [{ x: Number, y: Number }],
        required: true
    }
})

module.exports = MainZone