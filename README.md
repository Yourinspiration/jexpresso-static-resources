# JExpresso middleware for static resources

A middleware compenent for JExpresso application to serve static resources like files and images.

## Maven

Latest stable release:

```xml
<dependency>
  <groupId>de.yourinspiration</groupId>
  <artifactId>jexpresso-static-resources</artifactId>
  <version>1.1.1</version>
</dependency>
```

## Usage

```java
final JExpresso app = new JExpresso();
// Serves all files inside the folder 'assets'.
// Set the second argument to false, if file caching should be disabled.
app.use(new StaticResources("assets", true));
```
