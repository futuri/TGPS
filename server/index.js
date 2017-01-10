#!/usr/bin/env node
var io = require('socket.io')(8080);

io.on('connection', function (socket) {
	console.log("conectado");

	socket.on('disconnect', function(){
		//io.emit("","");
		console.log("desconectado");
	});

	socket.on('command', function (data) {
		console.log(data.action);
		io.emit('command',{'action':data.action});
	});

	socket.on('position', function (data) {
		console.log(data);
		io.emit('position',data);
	});

	//console.log(socket.handshake.headers.origin)
	//console.log(socket.handshake.headers.referer)
});