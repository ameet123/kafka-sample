# kafka-sample

Interact with kafka by publishing and consuming messages.
This uses Spring kafka connector and consequently is a `Spring-boot` application.

### Pre-reuisite
The app requiers a local/remote installation of kafka and zookeeper.
This is straightforward. 
  + download latest kafka
  + zookeeper -> start zookeeper
  + kafka -> start kafka
  
### WebApp
When you run this, either within an IDE or `gradlew bootRun`, it should start listening on port 8080.
It has 2 controllers - 
  + Welcome -> to return basic hello world messages
  + Kafka   -> to interact with kafka by allowing publishing of messages.
  
### Kafka publishing
Kafka controller has a few REST end points-
  + http://localhost:8080/message/submitEz
  + http://localhost:8080/message/submitJsonReserv
  + http://localhost:8080/message/submitXml
  
These endpoints will send sample data into the connected kafka topic.

### Kafka Consumption
Consumption is done via `AppConfig`
Method `listen` will print the message adn some metadata about it. Feel free to modify it.
