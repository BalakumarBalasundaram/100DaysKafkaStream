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
```
