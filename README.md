# JExpresso middleware for static resources

A middleware compenent for JExpresso application to serve static resources like files and images.

## Maven

```xml
<repository>
  <id>yourinspiration.realeases</id>
  <url>http://nexus.yourinspiration.de/nexus/content/repositories/releases/</url>
</repository>
<repository>
  <id>yourinspiration.snapshots</id>
  <url>http://nexus.yourinspiration.de/nexus/content/repositories/snapshots/</url>
</repository>
```

```xml
<dependency>
  <groupId>de.yourinspiration</groupId>
  <artifactId>jexpresso-static-resources</artifactId>
  <version>0.0.1-RELEASE</version>
</dependency>
```

## Usage

```java
final JExpresso app = new JExpresso();
// Serves all files inside the folder 'assets'.
// Set the second argument to false, if file caching should be disabled.
app.use(new StaticResources("assets", true));
```



