package com.github.fridujo.rabbitmq.mock;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.github.fridujo.rabbitmq.mock.MockConnectionFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QueueDeclarationTest {

    @Test
    void testQueueDeclaration() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        try (Connection conn = factory.newConnection();
             Channel channel = conn.createChannel()) {
            String queueName = channel.queueDeclare().getQueue();
            assertNotNull(queueName, "Queue name should be generated");
        }
    }
}
