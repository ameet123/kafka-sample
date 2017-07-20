# commonrequestprocessor-service 

This service aims to provide a common framework to perform the commonly requested asynchronous task.
It performs these requests on behalf of any and all consumer services.

![archflow](../../../../commonasync-embedded/commonasync-embedded/images/common-req-I.jpg "main req")

## Changes:

#####2/14/2017

+ TAS integration completed. performance testing underway in g4-stub and development environment.
+ new version: `0.2.14`
+ embedded tx-db-connector: **0.2.8**

#####1/23/2017

+ New version of embedded jar - **0.1.6**

#####1/20/2017

+ Added TAS-db connector library to interact with TAS Database for persisting front and back end request/response pair.
+ Added a new Queue 'TASQueue' bound to the same exchange which will process the messages intended for TAS.
+ 3 methods in the `Receiver` class based on the 3 types of objects persisted to the queue
+ All queues made durable now.


## TAS - TransactionAuditing Service

The invocation of TAS is via the following interfaces,
+ Instantiate bean `TasLogger`
+ Invoke one of the following three methods,
    - ```void logTransaction(TransactionDetail msg); ```
    - ```void logFrontEnd(EnhancedTasFrontEndDetail msg);```
    - ```void logBackEnd(TasBackendDetail msg);```
+ Add interceptors to the application to extract appropriate fields from request/response and construct the above
  objects. An example of this can be found here,
   + [AMCSSoapLoggingInterceptor](https://github.comcast.com/SalesPortal/amc-service/blob/tas-integration/src/main/java/com/comcast/billing/accountmanagementcontroller/service/interceptor/AMCSSoapLoggingInterceptor.java "Soap Logging Interceptor")
   + [AMCSBackendLoggingInterceptor](https://github.comcast.com/SalesPortal/amc-service/blob/tas-integration/src/main/java/com/comcast/billing/accountmanagementcontroller/service/interceptor/AMCSBackendLoggingInterceptor.java "Backend Logging Interceptor")
   
+ Interceptors are categorized in two types,
  + **Front-end**: These are added to the application when extending from `EspApplication` as follows,
  
    ```java
    import org.springframework.ws.server.EndpointInterceptor;
      
    public class AmcsApplication extends EspApplication {  
      
    @Autowired AMCSSoapLoggingInterceptor customSoapHeaderInterceptor;  
      
    @Override
        public void addInterceptors(List<EndpointInterceptor> interceptors) {
            // TODO Auto-generated method stub
            interceptors.add(customSoapHeaderInterceptor);
            super.addInterceptors(interceptors);
        }
    }
    ```
    
  + **Backend**: This is where the service makes backend SOAP calls to other services. Each of these calls are made
  by a dedicated `SOAPClient` object created from the esp package in a bean. This object has the ability to accept 
  multiple interceptors which are added to the array of interceptors. All such clients will need to be passed the
  appropriate and desired interceptors.
    ```java
      public SoapClient customerServiceClient(
          
          Jaxb2Marshaller marshaller,
          HeaderPropagationInterceptor headerPropagationInterceptor, 
          Wss4jSecurityInterceptor wss4jSecurityInterceptor,
          @Qualifier("com.comcast.amcs.backendlogginginterceptor") 
          BackendLoggingInterceptor backendLoggingInterceptor
      
              ) {
        
          SoapClient client = new SoapClient(customerMarshaller(), endpoint, 
                                headerPropagationInterceptor, wss4jSecurityInterceptor);
      
          ClientInterceptor[] interceptors = new ClientInterceptor[3];
                interceptors[0] = interceptor;
                interceptors[1] = headerPropagationInterceptor;
                interceptors[2] = backendLoggingInterceptor;
                client.setInterceptors(interceptors);
      }
    ```

### Configuration:

Following properties are leveraged in configuring the TAS integration,

There are 2 `RabbitMQ` queues - `TAS` and `AsyncJob`. The corresponding names are,
+ `ctp.commonasync.tas.<application_name>`
+ `ctp.<application_name>`

The properties related to consumer configuration for the above queues are,

```properties
# Async Job
ctp.commonasync.amqp.consumer.asyncjob.core=8
ctp.commonasync.amqp.consumer.asyncjob.max=12
```
```properties
# TAS 
ctp.commonasync.amqp.consumer.tas.core=40
ctp.commonasync.amqp.consumer.tas.max=50
ctp.commonasync.amqp.tas.prefetch=20
```

We allocate more consumers for TAS because each incoming request will spawn a tas request, may be even more in case 
of backend requests. We would also like to ensure that the application consumers only as much as it can process,
lest it shoulder the liability of losing messages. Hence the ceiling on TAS requests shall be the number of consumers to
the AMQP queue in RabbitMq.
 
Another factor which puts a ceiling on TAS throughput is the number of database connections to the TAS datasource.
Since each tas request will require a database call, the throughput is restricted by number of concurrent db 
connections to the TAS datasource. Consequently, we can tune the `ctp.commonasync.amqp.consumer.tas.core` property
to be within the ballpark of the `tas.datasource.max-active` property.

## Why?

In the legacy applications, the following template was used and repeated by copy/paste.

![archflow](../../../../commonasync-embedded/commonasync-embedded/images/jms-async.jpg)

In the newer world of PCF, JMS shall be replaced with RabbitMQ

## RabbitMQ resiliency

Especially in case of TAS, the functionality is itself ancillary and secondary in importance to the main application.
Consequently, in case of failure of any of the underlying components, it is desirable to have the application 
continue along. There are two modes of usage to RabbitMQ.

+ `Consumer`: This is asynchronous in nature and is effected using AMQP consumer threads. These threads are part of a
construct called `SimpleMessageListenerContainer`. This container utilizes a `connectionFactory` to make automatically
make connections to the bound rabbitMQ broker. In case of failure of the broker, the AMQP consumers via the 
`ListenerContainer` incessantly attempt to reconnect to the broker. We use an event-based mechanism to prevent such 
reconnects. this is depicted as below,
![archflow](../../../../commonasync-embedded/commonasync-embedded/images/broker-failure.png)
The listener is as follows,

```java
public class BrokerFailureEventListener implements ApplicationListener<ListenerContainerConsumerFailedEvent> {
    
    public void onApplicationEvent(ListenerContainerConsumerFailedEvent event) {
        asyncContainer.stop();
        
    }
}
```
+ `Producer`: As described in the above image, the producer which is the TAS interceptor, checks whether this global 
variable `isBrokerFailure` has been set to true. If it is, then no attempt is made to submit message and a simple 
error message is logged to the console and the process continues forward. 

### RabbitMQ Blocked connection

Although rare, during heavy stress testing, it was observed that connections to amqp consumers were broken. This 
will manifest as `blocked` in rabbitmq web interface and will cause the message listener container in the application
 to be shut down. Additionally, it may also cause the application performance to be degraded, eventually requiring a 
 restart. Here are the reasons,
 + Memory - By default rabbitMQ has a high watermark of 0.4 for memory. This means that when rabbitMQ uses more than 
 40% of installed memory, it raises memory alarm and blocks all connections.
 + Disk - similarly for disk, if the free space drops below 50MB, an alarm is raised.
 
 This may be handled by a blocked listener as follows,
 ```java
connectionFactory.addConnectionListener(new ConnectionListener() {
            @Override
            public void onCreate(Connection connection) {
                Channel channel = connection.createChannel(false);
                channel.getConnection().addBlockedListener(new BlockedListener() {
                    @Override
                    public void handleBlocked(String reason) throws IOException {
                        LOGGER.error("#### Connection blocked by the broker !!!!");
                    }

                    @Override
                    public void handleUnblocked() throws IOException {
                        LOGGER.error("!!! Connection UN-blocked by the broker !!!!");
                    }
                });
            }
```

At the moment, this code is not present in the repository but can be added at a later point.



## Metrics collection

Metrics collection is separated from the request processing. During the bean execution, only the elapsed time and 
returned result are captured.
This information needs to be processed to generate bean-level statistics.
this task is achieved using `RxJava` which constructs a subscriber to be used as an observable as well as an observer.
This takes care of putting the newly generated request object on the rx stream and processing it.
The publishing of new data is performed in an async fashion using a different thread pool.

### Rx Java

In Rx Java, the general pattern is , 
1. create an observable -> `Observable.create()` or `Observable.from()`. 
Generally, this is where event or message generation takes place
2. subscribe an action or a subscriber which will do the work on invocation of onNext()

However in this case, the data is primarily static. If we have a need to generate a stream of data dynamically, 
the `Subscriber` comes in handy.
We can use the subscriber as an observable and in this fashion create a dynamically growing stream of data.

So, we create a Subject using following construct,

```java
SerializedSubject<AsyncRemoteRequest, AsyncRemoteRequest> safeSource = 
                        PublishSubject.<AsyncRemoteRequest>create().toSerialized();
// now we can subscribe to this the usual way,
safeSource.subscribe(new Subscriber<AsyncRemoteRequest>() { ... };

// and to publish new data, we can call the onNext() method as follows,
safeSource.onNext(asyncRemoteRequest);
```


## Modes of Usage:

there are two ways in which this service can be utilized.

+ *Stand-alone*: In the first case, the service shall be deployed as a micro-service in PCF and 
any client application may connect to it over REST
+ *Embedded*: Additionally, an application may as well embed this application as a jar and use its offered features.

## Onboarding
 
1. add following properties to application.yaml or application.properties
    ```
    ctp.commonasync.amqp.queue.prefix=ctp
    ctp.commonasync.amqp.exchange=ctp-exchange
    # caching
    ctp.commonasync.caching.request.entries=1000
    # Metrics
    ctp.commonasync.metrics.top.entries=10
    ```
2. add following dependency to local build.gradle
    ```compile group: 'org.apache.commons', name: 'commons-math3', version: '3.0'```
3. use the following component scan
   ``` {"com.comcast.ctp.commonasync"}```
4. In order to connect to rabbitMQ in PCF, add the following property
    ```
    ctp.commonasync.amqp.broker.vhost=
    ```
5. end of onboarding

## How to Use

#### Sample Request

```
{
  "primaryKey": "WhiskeyTangoFoxtrot101",
  "objectParams": [
    "1st param"
  ],
  "beanId": "com.comcast.ctp.commonasync.bean.SampleAsyncBean",
  "methodName": "transact",
  "cacheResponse": true
}
```

Multiple requests can also be collected and passed in an array as follows,
 
 ```
 [
   {
     "primaryKey": "WhiskeyTangoFoxtrot101",
     "objectParams": [
       "1st param"
     ],
     "beanId": "com.comcast.ctp.commonasync.bean.SampleAsyncBean",
     "methodName": "transact",
     "responseQueue": "client9.response",
     "cacheResponse": false
   },
   {
     "primaryKey": "AlphaBraviCharlie39",
     "objectParams": [
       "39th argument"
     ],
     "beanId": "com.comcast.ctp.commonasync.bean.SampleAsync500Bean",
     "methodName": "transact",
     "cacheResponse": true
   },
   {
     "primaryKey": "SierraTangoUmbrella88",
     "objectParams": [
       "35239th argument"
     ],
     "beanId": "com.comcast.ctp.commonasync.bean.SampleAsync1000Bean",
     "methodName": "transact",
     "cacheResponse": true
   },
   {
     "primaryKey": "EchoFoxtrotGold8734",
     "objectParams": [
       "89414 argument"
     ],
     "beanId": "com.comcast.ctp.commonasync.bean.SampleAsync2000Bean",
     "methodName": "transact",
     "cacheResponse": true
   }
 ]
 ```
 
##### Submite a Request
    
    @Autowired
    private AsyncRequestSubmitter asyncRequestSubmitter;
    
    public void myMethod(){
        AsyncRemoteRequest asyncRemoteRequest= new AsyncRemoteRequest();
        // assign appropriate values...
        asyncRequestSubmitter.drop(asyncRemoteRequest);
    }
    
##### Get Status

    @Autowired
    private MetricsProcessor metricsProcessor;
    
    public void myMethod(){
        String pk = asyncRemoteRequest.getPk();
        // get status
        AsyncRemoteRequest response = metricsProcessor.get(pk);
 
 
   or, via REST,
   http://<server:port>/commonasync/metric/<pk>
   
##### Sample Message

```
{
  "primaryKey": "WhiskeyTangoFoxtrot101",
  "objectParams": [
    "1st param"
  ],
  "beanId": "com.comcast.ctp.commonasync.bean.SampleAsyncBean",
  "methodName": "transact"
}
```

##### API end-points

```
Swagger UI: https://commonasync-service.u1.app.cloud.comcast.net/swagger-ui.html 
URL endpoints:
•	Main:
    o	Hello Page: /commonasync/hello 
•	Metric:
    o	Generate random message: /commonasync/random
    o	Summary by work bean: /commonasync/metric/summary
    o	Status of individual message: /commonasync/metric/<msg-ID> 
    o	Drop a message in queue: /commonasync/submit POST
```


## Local Testing

### Requirements
Erlang - OTP 19.1 or latest version  
RabbitMQ Server - 3.6.5 or latest version  
Gradle - 2.12 or latest version [OPTIONAL - can build using Gradle Wrapper]  

1. Download and install Erlang - https://www.erlang.org/downloads
2. Download and install RabbitMQ Server - https://www.rabbitmq.com/download.html
3. [OPTIONAL] Download and install Gradle - https://gradle.org/gradle-download/ 
4. Clone the master directory into local repository
5. Build the project ".\gradlew build" 