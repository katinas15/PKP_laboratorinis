const express = require('express')
require('./db/mongoose')
const userRouter = require('./routers/user')
const mainZoneRouter = require('./routers/mainZone')

const app = express()
const port = process.env.PORT || 3000

app.use(express.json())
app.use(userRouter)
app.use(mainZoneRouter)

app.listen(port, () => {
    console.log('Server is up on port ' + port)
})