const express = require('express')
const MainZone = require('../models/mainZone')
const router = new express.Router()

var jwt = require('jsonwebtoken');
const jwtKey = 'KLMNjPrSt98'

router.post('/mainZone', async (req, res) => {
    const mainZone = new MainZone(req.body)

    try {
        await mainZone.save()
        res.status(201).send(mainZone)
    } catch (e) {
        res.status(400).send(e)
    }
})

router.get('/mainZone', async (req, res) => {
    try {
        const mainZone = await MainZone.find({})
        res.send(mainZone)
    } catch (e) {
        console.log(e);
        res.status(500).send()
    }
})

router.put('/mainZone/:id', async (req, res) => {
    const _id = req.params.id
    console.log(_id);
    console.log(req.body);
    try {
        const mainZone = await MainZone.findByIdAndUpdate(_id, req.body, { new: true })
        if (!mainZone) {
            return res.status(404).send()
        }
        res.send(mainZone)
    } catch (e) {
        console.log(e);
        res.status(500).send()
    }
})

module.exports = router