# myshop-assignment

In this project, I have created 3 microservices.
1. products : This service is essentially storing and providing products details
2. orders : This service is used to place an order and get orders for provided time range.
3. naming-sever : This service is a netflix-eureka-server which is a web-service naming service used by other services to registry and discover.

Technolgies used:
1. Java 8
2. Spring (boot, web, jpa, open-feign, eureka-client, eureka server)
3. Swagger 2
4. h2 (in memory)

The choice of these technologies 1,2,3 is pretty much product-ready, but 4. h2 is used for dev purpose only
To run this project, It needs to be imported (maven import) into an ide and then the spring boot applications need to be run.

Once all the three services are up, then we can see the end points by:
https://localhost:8010/swagger-ui.html/
https://localhost:8070/swagger-ui.html/


Q1 : propose a protocol / method for security
Since I am using a micro-services arhcitechture for this project.
I will use a API Gateway (netflix-zuul) to securing the web service from external callers.
So the external caller will call the API Gateway to access the services and it will a single entry point.
Then I will add a Authorization filter in the gateway which will validate if the user request conatins a valid security token.
If no token is present or token is expired then I will redirect the request to Oauth Server to get the user-security token.
Then the token once received will stored in database to check for the validity of the token later.
This token will appended to the user-request-reponse to avoid calling the Oauth server at each server call.

Q2 How can you make the service redundant? What considerations should you do?*
To make service redundant, We can run these microservices on multiple servers(hostname-port).
So every instance of service will register to Eureka server and then caller will able to make use of the multiple servers.
We can use ribbon client for client side load balancing , otherwise using netflix-zuul api gateway is more preferable.
Netflix-zuul api gateway itself uses ribbon client and requests can be routed to mutliple servers in round-robbin way.
Use of naming server helps the redundancy a lot as it becomes very easy to manage the load from outside by just deploying the services at more servers. They will automatically register themselves to eureka server and then will be called.
