# alfresco-event-samples
Simple POC for Alfresco Event API SDK

## Fast setup

1. Create a local Docker environment using [alfresco-docker-installer](https://github.com/Alfresco/alfresco-docker-installer)

2. If running the application outside Docker, check the availability of port 61616 for ActiveMQ connections. Hint: run the ActiveMQ container with at least these ports configured:
    ~~~
    activemq:
        ports:
            - 8161:8161
            - 61616:61616
    ~~~
