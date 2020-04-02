const express = require('express')
const Rating = require('../models/rating')
const router = new express.Router()

var jwt = require('jsonwebtoken');
const jwtKey = 'KLMNjPrSt98'

router.post('/rating/:id/rate', async (req, res) => {
    const _id = req.params.id
    const { userId, rating } = req.body
    const mainZone = new MainZone({ userId: userId, userZoneId: _id, rating: rating })

    try {
        await mainZone.save()
        res.status(201).send(mainZone)
    } catch (e) {
        res.status(400).send(e)
    }
})

module.exports = router