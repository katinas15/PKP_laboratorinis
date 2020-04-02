const express = require('express')
const Rating = require('../models/rating')
const router = new express.Router()

var jwt = require('jsonwebtoken');
const jwtKey = 'KLMNjPrSt98'

router.post('/mainZone/:id/rate', async (req, res) => {
    const _id = req.params.id
    const { userId, rating } = req.body
    const zoneRating = new Rating({ userId: userId, userZoneId: _id, rating: rating })

    try {
        await zoneRating.save()
        res.status(201).send(zoneRating)
    } catch (e) {
        res.status(400).send(e)
    }
})

module.exports = router