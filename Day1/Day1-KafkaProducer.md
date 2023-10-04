## Kafka Producer

The KafkaProducer class is a part of the Apache Kafka library, which is a distributed streaming platform that is used to build real-time data pipelines and streaming applications.

The KafkaProducer class is used to send messages to a Kafka topic. It is initialized with a set of configuration properties that define the behavior of the producer, such as the Kafka broker addresses, the serializer to use for the key and value, and the compression type. Once the producer is initialized, messages can be sent to a Kafka topic by calling the send() method, which takes a ProducerRecord object as an argument. The ProducerRecord object contains the topic name, the key, and the value of the message to be sent.

The KafkaProducer is initialized with a set of configuration properties that define the behavior of the producer, such as the Kafka broker addresses and the Avro serializer. Once the producer is initialized, data is sent to the Kafka topic by creating a new ProducerRecord object for each order and calling the send() method. Overall, the KafkaProducer class is a key component of the Apache Kafka library and is used to send messages to a Kafka topic.

### Producer Config

```
  public static final Map<String, Object> producerConfig = Map.of(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST,
        ProducerConfig.ACKS_CONFIG, "all",
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
```
        
### Kafka Producer & Producer Record

```
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

var producer = new KafkaProducer<String, String>(Utils.producerConfig);
 for (int i = 0; i < 2; i++) {
            producer.send(new ProducerRecord<>("topic1", Integer.toString(i), Integer.toString(i)));
}

producer.close();
```

### Kafka Avro Producer & Producer Record
```
Properties props = new Properties();
props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:19092");
props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        
final KafkaProducer<String, Order> producer = new KafkaProducer<>(props);
```
