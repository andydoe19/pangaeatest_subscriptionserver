# Pangaea Test Subscription Server
> Test subscription server for Pangaea
---
This is a test subscription server for Pangaea written in java using Micronaut framework.
This server is meant to receive subscription requests from subscribers. 
Subscribers would have to specify their url and subscribe with any topic they prefer in order to subscribe.
Afterwards, the server can publish a message to any topic on the server, and that message will be fired to 
all subscribers subscribed to that topic.<br /><br />

However this server works hand in hand with a Test subscribers server, which receives notifications anytime 
a publish request is fired on the Test Subscription server. The Test subscribers server can be found here ...

## Development Language
**Java programming language**

## Framework
**Micronaut** - A recent framework of java that employs AoT and other features 
for developing responsive and fast microservices. More about Micronaut are below;<br /><br />

## Requirments
1. Java SDK (version 8)
2. Micronaut Client
3. IntelliJ IDEA
4. Git
5. Postman (To test API Endpoints) <br /><br />


## Project Setup
After cloning the project with Git, open IntelliJ and Click on File > Open.
Navigate to "pangaeatest_subscriptionserver" project and open it. 

When asked by IntelliJ to enable auto import for gradle, enable it. 
Else if project hasn't started importing gradle dependencies, click on Gradle tab on the far right,
and click on the "reimport all projects" button (looks like a refresh icon). <br /><br />

## No Database setup
NO database setup needed for now, the application will use an in-memory database
and create objects on the fly. However when required we can switch to a server database anytime.
Test Server or Production however will rely on an installed mySql on the server. <br /><br />

## Running the Project
After all dependencies are imported, you can use any of the actions below to run or test the project.
You can run them terminal tab at the lower section of the IntelliJ IDE;

> CLEAN AND BUILD PROJECT
```
$ gradlew clean build
```

> RUN PROJECT
```
$ gradlew run
```

After running the project has run successfully using the `run project` command above,
you can test the project with the url - http://localhost:8000 <br /><br />

## Testing the API Endpoints
Manual testing can be done with **Postman** desktop application. <br /><br />
Here are the following Endpoints to test: <br />
> Subscription
---
This API registers subscribers onto the server. The following test subscriber urls, found on 
the test subscribers server can be used for this API: http://localhost:9000/test1, 
http://localhost:9000/test2, http://localhost:9000/test3 . <br/>
Hence here's a sample request : <br/>
```
POST /subscribe/topic1 HTTP/1.1
Host: localhost:8000
Content-Type: application/json
{ "url": "http://localhost:9000/test1"}
```

> Publish
---
This API sends the supplied message to all subscribers registered to a particular topic on the server. 
<br/>
Hence here's a sample request : <br/>
```
POST /publish/topic1 HTTP/1.1
Host: localhost:8000
Content-Type: application/json
{ "message": "test notification to all subscribers"}
```

> List all Subscribers
---
This API lists all subscriber details on the server. 
<br/>
Hence here's a sample request : <br/>
```
GET /subscribe/list HTTP/1.1
Host: localhost:8000
```
<br/><br/>
***Thats all and Have fun trying out this project!!!***