
## topology



## Streams Builder
```java
KStreamBuilder builder = new KStreamBuilder();
KStream<String, String> source = builder.stream("streams-plaintext-input");
source.to("streams-pipe-output");
```

### Topology
```java
Topology topology = builder.build();
```

### Connect Kafka Streams to Topology APi


```java
Properties props = new Properties();
props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");
props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

KafkaStreams streams = new KafkaStreams(topology, props);
streams.start();
```

```java
// the builder is used to construct the topology
Topology topology = new Topology();

topology.addSource("UserSource", "users");
topology.addProcessor("SayHello", SayHelloProcessor::new, "UserSource");

// set the required properties for running Kafka Streams
Properties config = new Properties();
config.put(StreamsConfig.APPLICATION_ID_CONFIG, "dev2");
config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Void().getClass());
config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

// build the topology and start streaming!
KafkaStreams streams = new KafkaStreams(topology, config);
System.out.println("Starting streams");
streams.start();

// close Kafka Streams when the JVM shuts down (e.g. SIGTERM)
Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
```

### Connect Kafka Streams to Streams Builder API

```java
 // the builder is used to construct the topology
    StreamsBuilder builder = new StreamsBuilder();

    // read from the source topic, "users"
    KStream<Void, String> stream = builder.stream("users");

    // for each record that appears in the source topic,
    // print the value
    stream.foreach(
        (key, value) -> {
          System.out.println("(DSL) Hello, " + value);
        });

    // you can also print using the `print` operator
    // stream.print(Printed.<String, String>toSysOut().withLabel("source"));

    // set the required properties for running Kafka Streams
    Properties config = new Properties();
    config.put(StreamsConfig.APPLICATION_ID_CONFIG, "dev1");
    config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Void().getClass());
    config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

    // build the topology and start streaming
    KafkaStreams streams = new KafkaStreams(builder.build(), config);
    streams.start();

    // close Kafka Streams when the JVM shuts down (e.g. SIGTERM)
    Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
 ```   