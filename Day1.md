##Producer

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
