package com.learn.app;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderRunnableApp implements Runnable {

    public static final String topic = "orders";

    public static void main(String[] args) {
        OrderRunnableApp app = new OrderRunnableApp();
        app.run();
    }

    public void run() {
       
        //Setup Kafka Producer
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers","localhost:29092");

        kafkaProps.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        
        Producer<String,String> productSalesOrderProducer  =  new KafkaProducer<String, String>(kafkaProps);

        //Define list of Product names
        List<String> items = new ArrayList<String>();
        items.add("textile");
        items.add("shoes");
        items.add("cosmetics");

        //Define list of Product prices
        List<Double> cost = new ArrayList<Double>();
        cost.add(121.00);
        cost.add(13.5);
        cost.add(349.00);

        //Define a random number generator
        Random random = new Random();
        
        ObjectMapper mapper = new ObjectMapper();


        try {

            //Generate a random order id
            long orderId = (long)Math.floor(System.currentTimeMillis()/1000);

            //Generate 100 random orders
            for(int i=0; i < 100; i++) {

                /**
                 * Represents a product sales order.
                 */
                ProductSalesOrder pSalesOrder = new ProductSalesOrder();
                pSalesOrder.setOrderId(orderId);
                orderId++;

                // Generate a random product number
                pSalesOrder.setProductNo("P" + String.valueOf(random.nextInt(1000) + 1));

                //Generate a random product
                int randval=random.nextInt(items.size());
                pSalesOrder.setProductName(items.get(randval));

                //Get product price
                pSalesOrder.setPrice(cost.get(randval));

                //Generate a random value for number of quantity
                pSalesOrder.setQuantity(random.nextInt(4) + 1);

                String recKey = String.valueOf(pSalesOrder.getOrderId());
                String value = mapper.writeValueAsString(pSalesOrder);

                //Create a Kafka producer record
                ProducerRecord<String, String> record =
                        new ProducerRecord<String, String>(
                                topic,
                                recKey,
                                value );

                productSalesOrderProducer.send(record).get();

                System.out.println( 
                            "Kafka Orders Stream Generator : Sending Event : "
                            + String.join(",", value)   + " to topic : " + topic);

                //Sleep for a random time ( 1 - 3 secs) before the next record.
                Thread.sleep(random.nextInt(2000) + 1000);
            }

        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            productSalesOrderProducer.close();
        }
    }
}        