
## Day 2 - Set Maven POM

### Create Maven POM

https://maven.apache.org/archetypes/maven-archetype-quickstart/

```
mvn archetype:generate -DgroupId=com.learn.app -DartifactId=customapp -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

### Add Maven Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.kafka</groupId>
        <artifactId>kafka-streams</artifactId>
        <version>3.5.1</version>
    </dependency>
    <dependency>
        <groupId>org.apache.kafka</groupId>
        <artifactId>kafka-clients</artifactId>
        <version>3.5.1</version>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
```  