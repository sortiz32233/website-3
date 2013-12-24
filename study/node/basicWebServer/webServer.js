/**
 * study of basic http & file api
 * @author ian.primary.li@gmail.com
 * @create Dec22, 2013
 */
var http = require('http');
var server = http.createServer(function(req, res) {
    /*
     * set header before write data
     */
    var body = 'Hello World02!';
    res.setHeader('Content-Length', 2 * body.length || 0);
    res.setHeader('Content-Type', 'text/html');
    res.statusCode = 200;
    /*
     * res will show before location show when redirect 
     */
    //res.setHeader('Location', 'http://github.com');
    //res.statusCode = 302;

    res.write('Hello World01!');
    res.write(body);
    res.end();
});

server.listen(9527, '127.0.0.1');
console.log('### start web server ###');