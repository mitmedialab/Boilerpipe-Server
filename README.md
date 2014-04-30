Boilerplate Server
------------------

This is a tiny wrapper around the [Boilerplate Text Extraction library](https://code.google.com/p/boilerpipe/).

Developing
----------

You need maven and java (1.7).  We develop in Eclipse Kepler: Java EE.

Deployment
----------

# Setup

This is setup to be run inside a Java servlet container (ie. Tomcat7).  For development 
we use the [Maven Tomcat plugin](http://tomcat.apache.org/maven-plugin.html).  To deploy, 
add this to your `%TOMCAT_PATH%/conf/tomcat-users.xml` file:
```xml
  <role rolename="manager"/>
  <role rolename="manager-gui"/>
  <role rolename="manager-script"/>
  <user username="cliff" password="beer" roles="manager,manager-gui,manager-script"/>
```
Also add this to your `~/.m2/settings.xml`:
```xml
  <servers>
    <server>
	  <id>ExtractorTomcatServer</id>
      <username>cliff</username>
      <password>beer</password>
    </server>
  </servers>
```
That lets the Maven Tomcat plugin upload the WAR it builds over the website control panel.

# Building and Deploying

First make sure tomcat is running (ie. `catalina run`). Now run `mvn tomcat7:deploy` 
to deploy the app, or `mvn tomcat7:redeploy` to redeploy once you've already got 
the app deployed.

Using
-----

To test it out, hit this url in a browser and you should get some JSON back:

```
http://localhost:8080/extractor/text?url=http://civic.mit.edu
```

Releasing
---------

To create the WAR file, run `mvn package`.
