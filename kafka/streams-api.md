json schema 2 pojo

<plugin>
                <groupId>org.jsonschema2pojo</groupId>
                <artifactId>jsonschema2pojo-maven-plugin</artifactId>
                <version>0.5.1</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <sourceDirectory>${project.basedir}/src/main/resources/schema/</sourceDirectory>
                            <outputDirectory>${project.basedir}/src/main/java/</outputDirectory>
                            <includeAdditionalProperties>false</includeAdditionalProperties>
                            <includeHashcodeAndEquals>false</includeHashcodeAndEquals>
                            <generateBuilders>true</generateBuilders>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
      
        
class AppSerdes extends Serdes {

 static final class OrderSerde extends WrapperSerde<Order> {
    OrderSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    static Serde<Order> Order() {
        OrderSerde serde = new OrderSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("specific.class.name", Order.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }
}



        KS0.groupBy(
                (k,v) -> v.getDepartment(),
                Serialized.with(AppSerdes.String(), AppSerdes.Employee())
        ).aggregate(
                () -> new DepartmentAggregate()
                        .withEmployeeCount(0)
                        .withTotalSalary(0)
                        .withAvgSalary(0D),
                (k,v,aggV) -> new DepartmentAggregate()
                        .withEmployeeCount(aggV.getEmployeeCount() + 1)
                        .withTotalSalary(aggV.getTotalSalary() + v.getSalary())
                        .withAvgSalary((aggV.getTotalSalary() + v.getSalary()) / (aggV.getEmployeeCount() + 1D)),
                Materialized.<String, DepartmentAggregate, KeyValueStore<Bytes, byte[]>>as("agg-store")
                        .withKeySerde(AppSerdes.String())
                        .withValueSerde(AppSerdes.DepartmentAggregate())
        ).toStream().foreach(
                (k,v) -> System.out.println("Key = " + k + " Value = " + v.toString())
        );
