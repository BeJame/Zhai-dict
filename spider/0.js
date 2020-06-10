const fs = require('fs')
const out = fs.createWriteStream('./TEM8-unsorted.json')
const word = require('../TEM8-with-audio-unsorted.json')

for (let i = 0; i < word.length; i++) {
    delete word[i].audio
}

out.write(JSON.stringify(word))