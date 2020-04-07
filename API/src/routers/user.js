const express = require('express')
const User = require('../models/user')
const router = new express.Router()

var bcrypt = require('bcrypt');
const saltRounds = 8;
var jwt = require('jsonwebtoken');
const jwtKey = 'KLMNjPrSt98'
const jwtExpireSeconds = 10000;

router.post('/register', async (req, res) => {
    let { email, password } = req.body
    try {
        let hashedPassword = await bcrypt.hash(password, saltRounds)
        const user = new User({ password: hashedPassword, email: email })
        const token = jwt.sign({ email }, jwtKey, {
            algorithm: 'HS256',
            expiresIn: jwtExpireSeconds,
        })
        await user.save()
        res.send(user);
    } catch (e) {
        res.status(400).send(e)
    }
})

router.post('/login', async (req, res) => {
    const { email, password } = req.body;

    try {
        const user = await User.findOne({ email: email })

        if (!user) {
            return res.status(404).send()
        }

        const passwordMatch = await bcrypt.compare(password, user.password)
        if (passwordMatch) {
            const token = jwt.sign({ email }, jwtKey, {
                algorithm: 'HS256',
                expiresIn: jwtExpireSeconds,
            })
            res.send(user);
            res.end()
        } else {
            return res.status(401).end();
        }

    } catch (e) {
        res.status(500).send()
    }
})

module.exports = router