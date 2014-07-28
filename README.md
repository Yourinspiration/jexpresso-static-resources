# JExpresso middleware for static resources

A middleware compenent for JExpresso application to serve static resources like files and images.

## Usage

```java
final JExpresso app = new JExpresso();
// Serves all files inside the folder 'assets'.
// Set the second argument to false, if file caching should be disabled.
app.use(new StaticResources("assets", true));
```
