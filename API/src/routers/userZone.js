const express = require('express')
const UserZone = require('../models/userZone')
const router = new express.Router()

var jwt = require('jsonwebtoken');
const jwtKey = 'KLMNjPrSt98'

router.post('/userZone', async (req, res) => {
    const userZone = new UserZone(req.body)

    try {
        await userZone.save()
        res.status(201).send(userZone)
    } catch (e) {
        res.status(400).send(e)
    }
})

router.get('/userZone', async (req, res) => {
    try {
        const userZone = await UserZone.find({})
        res.send(userZone)
    } catch (e) {
        console.log(e);
        res.status(500).send()
    }
})

router.put('/userZone/:id', async (req, res) => {
    const _id = req.params.id
    console.log(_id);
    console.log(req.body);
    try {
        const userZone = await UserZone.findByIdAndUpdate(_id, req.body, { new: true })
        if (!userZone) {
            return res.status(404).send()
        }
        res.send(userZone)
    } catch (e) {
        console.log(e);
        res.status(500).send()
    }
})

module.exports = router