var express = require('express');
var app = express();

app.get('/hello.html', function(req, res) {
    var body = 'Hello';
    //res.setHeader('Content-Type', 'text/html');
    //res.setHeader('Content-Length', Buffer.byteLength(body));
    res.end(body);
});

app.listen(3000);

console.log('express listen to port 3000');