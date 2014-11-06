var ws = require('ws');
var fs = require('fs');
var path = require('path');
var chalk = require('chalk');

var model = process.argv[2] || 'base.kevs';

var modelPath = path.resolve(__dirname, '..', 'models', model);
var file = fs.readFileSync(modelPath, 'utf8');
var client = new ws('ws://localhost:9000');

client.on('open', function () {
	client.send(file);
	console.log('Model '+chalk.green(modelPath)+' pushed.');
	client.close();
});

client.on('error', function (err) {
	console.log('Error: '+chalk.red(err.message));
});