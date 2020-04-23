// using nodejs's build in asserts that throw on failure
var assert = require('assert')
const fetch = require('node-fetch')
const axios = require('axios')
const BASEURL = "http://openag.pro:3111"

exports['tests'] = function() {

  assert.equal(2 + 2, 5, 'will never pass this since test failed above')
}



exports['test login'] = async function() {
    console.log('login')
    let login = "t@t.lt";
    let pass = "testtest";
    let send = "{\"email\":\"" + login + "\", \"password\":\"" + pass + "\"}";
    let expectedResult = "{\"_id\":\"5e8cc76a6b01af2af17fe997\"," +
            "\"password\":\"$2b$08$UDDiGd9gEKQfazZnIFdVlOC9Sn2aChzVJXFZhOxHCeRmoyZ4ka4PW\"," +
            "\"email\":\"t@t.lt\",\"__v\":0}";
    let url = "/login";
    

    let answer = await fetch(BASEURL + url,
            { method: 'POST', body: send, headers: { "Content-type": "application/json" } }).then((response) => {

                if (response.ok != false) {
                    return response.json();
                } else {
                    console.log('err')
                    return [];
                }
            })

    console.log(JSON.stringify(answer))
    console.log(expectedResult)
    assert.equal(JSON.stringify(answer), expectedResult)

}

exports['test register'] = async function() {

    let random = Math.floor(Math.random() * 1000); 
    let login = "unittest" + random + "@t.lt";
    let pass = "unittest" + random;
    let send = "{\"email\":\"" + login + "\", \"password\":\"" + pass + "\"}";
    let expectedResult = "\"unittest" + random + "@t.lt\"";
    let url = "/register";


    let answer = await fetch(BASEURL + url,
        { method: 'POST', body: send, headers: { "Content-type": "application/json" } }).then((response) => {

            if (response.ok != false) {
                return response.json();
            } else {
                console.log('err')
                return [];
            }
        })

    console.log(JSON.stringify(answer))
    console.log(expectedResult)
    assert.equal(JSON.stringify(answer.email), expectedResult)
}
  

exports['test getMainZones'] = async function() {
    let url = "/mainZone";

    let answer = await fetch(BASEURL + url,
        { method: 'GET', headers: { "Content-type": "application/json" } }).then((response) => {

            if (response.ok != false) {
                return response.json();
            } else {
                console.log('err')
                return [];
            }
        })

    assert.equal((JSON.stringify(answer).length > 1) ? true : false , true)
}

exports['test getUserZones'] = async function() {
    let url = "/userZone";

    let answer = await fetch(BASEURL + url,
        { method: 'GET', headers: { "Content-type": "application/json" } }).then((response) => {

            if (response.ok != false) {
                return response.json();
            } else {
                console.log('err')
                return [];
            }
        })

    assert.equal((JSON.stringify(answer).length > 1) ? true : false , true)
}


exports['test getZoneRating'] = async function() {
    let markerId = "5e8ee1fe54a93509f1dd6ce5";

    let url = "/userZone/" + markerId + "/rate";

    let answer = await fetch(BASEURL + url,
        { method: 'GET', headers: { "Content-type": "application/json" } }).then((response) => {

            if (response.ok != false) {
                return response.json();
            } else {
                console.log('err')
                return [];
            }
        })

    assert.equal((JSON.stringify(answer).length > 1) ? true : false , true)
}


exports['test changeZoneRating'] = async function() {
    let ratingId = "5e8ef31c54a93509f1dd6ce9";
    let url = "/rating/" + ratingId ;

    let rateValue = Math.floor(Math.random() * 6); 
    let send =
        "{" + "\"rating\":" + rateValue + " }";

    let answer = await fetch(BASEURL + url,
        { method: 'PUT', body: send, headers: { "Content-type": "application/json" } }).then((response) => {

            if (response.ok != false) {
                return response.json();
            } else {
                console.log('err')
                return [];
            }
        })
    console.log(JSON.stringify(answer))
    console.log(rateValue)
    assert.equal(JSON.stringify(answer.rating), rateValue)
}



// @Test
// public void ChangeZoneRating(){
//     let ratingId = "5e8ef31c54a93509f1dd6ce9";
//     let url = "/rating/" + ratingId ;

//     int rateValue = new Random().nextInt(5) + 1;
//     let send =
//         "{" + "\"rating\":" + rateValue + " }";

//     let answer;
//     try {
//         answer = NetController.sendPut(url, send);
//     } catch (Exception e) {
//         e.printStackTrace();
//         answer = "Error";
//     }

//     Rating rating = new Gson().fromJson(answer, new TypeToken<Rating>() {}.getType());

//     System.out.println("result - " + answer);
//     assertTrue(rating.getRating() == rateValue);
// }
if (module == require.main) require('test').run(exports)