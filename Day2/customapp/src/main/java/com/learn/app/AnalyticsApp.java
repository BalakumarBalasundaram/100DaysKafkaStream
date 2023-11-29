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
    

    
}
