var http = require('http');
var fs = require('fs');
http.createServer(function(req, res) {
    if(req.url == '/') {
        console.log(req.url);
        fs.readFile('./entries.json', function(error, data) {
            if(error) {
                throw error;
            }
            var entries = JSON.parse(data.toString());
            var output = '<html><head></head><body>' +
                '<h1>Latest Posts</h1>' +
            '<ul>';
            for(var index in entries) {
                output += '<li>' + entries[index].title + '</li>';
            }
            output += '</ul></body></html>';
            res.writeHead(200, {
                'Content-Type' : 'text/html'
            });
            res.end(output);
        });
    } else {
        console.log(req.url + ' no in if');
    }
}).listen(9529, '127.0.0.1');