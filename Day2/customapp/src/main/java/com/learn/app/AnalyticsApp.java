package com.learn.app;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.ValueMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.learn.common.ClassDeSerializer;
import com.learn.common.ClassSerializer;

/**
 * Hello world!
 *
 */
public class AnalyticsApp {
 
    public static void main (String[] args) {
    OrderRunnableApp app = new OrderRunnableApp();
    Thread genOrder  =  new Thread(app);
    genOrder.start();
    }
    
//    try {
//         /**************************************************
//             * Build a Kafka Streams Topology
//             **************************************************/
//
//            //Setup Serializer / DeSerializer for  Data types
//            final Serde<String> stringSerde = Serdes.String();
//            final Serde<Long> longSerde = Serdes.Long();
//
//            final Serde<ProductSalesOrder> orderSerde
//                    = Serdes.serdeFrom(new ClassSerializer<>(),
//                            new ClassDeSerializer<>(ProductSalesOrder.class));
//
//            //Setup Properties for the Kafka Input Stream
//            Properties props = new Properties();
//            props.put(StreamsConfig.APPLICATION_ID_CONFIG,
//                    "streaming-analytics-pipe");
//            props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
//                    "localhost:9092");
//            props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
//                    Serdes.String().getClass());
//            props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
//                    Serdes.String().getClass());
//            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
//
//            //For immediate results during testing
//            props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
//            props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 100);
//
//            //Initiate the Kafka Streams Builder
//            final StreamsBuilder builder = new StreamsBuilder();
//
//            //Create the source node for Orders
//            KStream<String,String> ordersInput
//                    = builder.stream("orders",
//                                        Consumed.with(
//                                                stringSerde, stringSerde));
//
//            ObjectMapper mapper = new ObjectMapper();
//
//            //Convert input json to SalesOrder object using Object Mapper
//            KStream<String,ProductSalesOrder> orderObjects
//                    = ordersInput.mapValues(
//                            new ValueMapper<String, ProductSalesOrder>() {
//                                @Override
//                                public ProductSalesOrder apply(String inputJson) {
//
//                                    try {
//                                        ProductSalesOrder order =
//                                                mapper.readValue(
//                                                        inputJson,
//                                                        ProductSalesOrder.class);
//                                        return order;
//                                    }
//                                    catch(Exception e) {
//                                        System.out.println("ERROR : Cannot convert JSON "
//                                                + inputJson);
//                                        return null;
//                                    }
//                                }
//                            }
//                    );
//
//            //Print objects received
//            orderObjects.peek(
//                    (key, value) ->
//                            System.out.println("Received Order : " + value)
//            );
//
//    } catch (InterruptedException e) {
//        e.printStackTrace();
//    }
    
}
