package com.example.oauth.membership.consumer

import com.example.oauth.membership.consumer.adapter.MembershipAccountAdapter
import com.example.oauth.membership.consumer.dto.MembershipAccountCreatedDTO
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
internal class MembershipAccountConsumer(
    private val registerAdapter: MembershipAccountAdapter
) {
    @KafkaListener(
        id = "oauth-by-membership",
        topics = ["membership-account-event"],
        groupId = "oauth-group",
        containerFactory = "kafkaListenerRegisterContainerFactory"
    )
    fun consumer(
        @Payload membershipAccountDTO: MembershipAccountCreatedDTO,
        acknowledgment: Acknowledgment
    ) {
        runCatching { registerAdapter.process(membershipAccountDTO) }
            .onFailure {
                logger.error("Error to consume $membershipAccountDTO", it)
                acknowledgment.acknowledge()
            }
            .onSuccess {
                logger.info("Success to consume $membershipAccountDTO")
                acknowledgment.acknowledge()
            }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
