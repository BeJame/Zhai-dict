var request = require("request");
var fs = require("fs");
var vocabulary = require("./list");
var result_json = {};
var result = fs.createWriteStream("result.json");
var time = 0;
var url_pre = "https://api.shanbay.com/bdc/search/?word=";
var i = 0;


var run = setInterval(function(){
      var word = vocabulary[i++];
      console.log('fetching ' + word);
      request(url_pre + word, function(_,_,body) {
      result_json[word] = JSON.parse(body);
      
      if(i >= vocabulary.length) {
            clearInterval(run);
      		const ret = []
            for (const word of vocabulary) {
                  if (result_json[word].status_code === 0) {
                        ret.push({
                              content: word,
                              definition: result_json[word].data.cn_definition.defn,
                              pron: result_json[word].data.pron
                        })
                  } else {
                        console.log('NOT EXIST ' + word)
                  }
            }
      	result.write(JSON.stringify(ret));
      }
 })
},150);