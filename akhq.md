  akhq:
    image: tchiotludo/akhq
    container_name: akhq
    environment:
      AKHQ_CONFIGURATION: |
        akhq:
          connections:
            docker-kafka-server:
              properties:
                bootstrap.servers: "kafka:9092"
    ports:
      - "8111:8080"
