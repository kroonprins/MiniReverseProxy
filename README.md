Small reverse proxy using jetty.

Configuration for the proxy rules is read from yml configuration file.
Supports http->http, https->http, http->https, https->https.

* Clone repository
* mvn package
* java -jar target/MiniReverseProxy-jar-with-dependencies.jar [configuration] 
  (if configuration file not provided it runs the test configuration file test.yml)
