package com.learn.utils;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class Utils {


    public static final String HOST = "localhost:9091";


    public static final Map<String, Object> producerConfig = Map.of(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST,
            ProducerConfig.ACKS_CONFIG, "all",
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    public static Map<String, Object> createProducerConfig(Consumer<Map<String, Object>> builder) {
        HashMap map = new HashMap<>(producerConfig);
        builder.accept(map);
        return map;
    }

    public static final Map<String, Object> consumerConfig = Map.of(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST,
            ConsumerConfig.GROUP_ID_CONFIG, "some-java-consumer",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    public static Map<String, Object> createConsumerConfig(Consumer<Map<String, Object>> builder) {
        HashMap map = new HashMap<>(consumerConfig);
        builder.accept(map);
        return map;
    }

    public static final Map<String, Object> adminConfig = Map.of(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);

    public static Map<String, Object> createAdminConfig(Consumer<Map<String, Object>> builder) {
        HashMap map = new HashMap<>(producerConfig);
        builder.accept(map);
        return map;
    }

    private static final Map<String, Object> streamsConfig = Map.of(
            StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);

    public static StreamsConfig createStreamsConfig(Consumer<Map<String, Object>> builder) {
        HashMap map = new HashMap<>(streamsConfig);
        builder.accept(map);
        return new StreamsConfig(map);
    }

    public static StreamsConfig createStreamsConfig(String appId) {
        return createStreamsConfig(b -> b.put(StreamsConfig.APPLICATION_ID_CONFIG, appId));
    }


    public static void doAdminAction(AdminClientConsumer action) {
        try (Admin client = Admin.create(Utils.adminConfig)) {
            action.accept(client);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doAdminAction(Consumer<Map<String, Object>> configBuilder, AdminClientConsumer action) {
        try (Admin client = Admin.create(createAdminConfig(configBuilder))) {
            action.accept(client);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public interface AdminClientConsumer {
        void accept(Admin client) throws Exception;
    }

    public static final String someLongMessage;

    static {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < 1024; ++i)
            b.append(i);
        someLongMessage = b.toString();
    }

    public static void sendMessages(int keyFrom, int keyTo, String topic, Integer partition) {
        try (KafkaProducer<String, String> producer = new KafkaProducer<String, String>(Utils.producerConfig)) {
            for (int i = keyFrom; i < keyTo; i++) {
                producer.send(new ProducerRecord<String, String>(topic, partition, Integer.toString(i),
                        Utils.someLongMessage));
            }
            producer.flush();
        }
    }


    public static <K, V> Materialized<K, V, KeyValueStore<Bytes, byte[]>> materialized(String name, Serde<K> key, Serde<V> value) {
        return Materialized.<K, V, KeyValueStore<Bytes, byte[]>>as(name)
                .withKeySerde(key).withValueSerde(value);
    }

}

