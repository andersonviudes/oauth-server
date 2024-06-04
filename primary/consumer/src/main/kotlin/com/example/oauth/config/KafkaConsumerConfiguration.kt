package com.example.oauth.config

import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer
import com.example.oauth.membership.consumer.dto.MembershipAccountCreatedDTO
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerAwareErrorHandler
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.MessageListenerContainer

@EnableKafka
@Configuration
internal class KafkaConsumerConfiguration(
    private val properties: KafkaProperties,
) {

    @Bean("consumerFactory")
    fun consumerFactory(): ConsumerFactory<in String, in MembershipAccountCreatedDTO> {
        val buildConsumerProperties = properties.buildConsumerProperties()

        buildConsumerProperties[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = KafkaJsonSchemaDeserializer::class
        buildConsumerProperties[KafkaJsonDeserializerConfig.JSON_VALUE_TYPE] =
            MembershipAccountCreatedDTO::class.java.name

        val jsonDeserializer = KafkaJsonSchemaDeserializer<MembershipAccountCreatedDTO>()
        val stringDeserializer = StringDeserializer()

        jsonDeserializer.configure(buildConsumerProperties, false)
        stringDeserializer.configure(buildConsumerProperties, false)

        return DefaultKafkaConsumerFactory(
            buildConsumerProperties,
            stringDeserializer,
            jsonDeserializer
        )
    }

    @Bean("kafkaListenerRegisterContainerFactory")
    fun kafkaListenerRegisterContainerFactory(consumerFactory: ConsumerFactory<in String, in MembershipAccountCreatedDTO>): ConcurrentKafkaListenerContainerFactory<String, MembershipAccountCreatedDTO> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, MembershipAccountCreatedDTO> =
            ConcurrentKafkaListenerContainerFactory()
        factory.setConcurrency(1)
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL
        factory.consumerFactory = consumerFactory
        factory.setErrorHandler(ConsumerErrorHandler())
        return factory
    }
}

class ConsumerErrorHandler : ContainerAwareErrorHandler {
    override fun handle(
        thrownException: Exception,
        records: MutableList<ConsumerRecord<*, *>>?,
        consumer: Consumer<*, *>,
        container: MessageListenerContainer
    ) {
        logger.error("Handle Consumer error - container: $container - records: $records", thrownException)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
