const functions = require('firebase-functions');
const { spawn } = require('child_process');
const path = require('path');

exports.startApp = functions.https.onRequest((request, response) => {
    const appPath = path.join(__dirname, '/IPRWC-Backend-0.0.1-SNAPSHOT.jar');
    const command = `java -jar ${appPath}`;
    const appProcess = spawn(command);

    appProcess.stdout.on('data', (data) => {
        console.log(`stdout: ${data}`);
    });

    appProcess.stderr.on('data', (data) => {
        console.error(`stderr: ${data}`);
    });

    appProcess.on('close', (code) => {
        console.log(`child process exited with code ${code}`);
    });

    response.send('App started successfully');
});
