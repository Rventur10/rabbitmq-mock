package com.github.fridujo.rabbitmq.mock;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.github.fridujo.rabbitmq.mock.MockConnectionFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessagePublishingTest {

    @Test
    void testMessagePublishing() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        try (Connection conn = factory.newConnection();
             Channel channel = conn.createChannel()) {
            String queue = channel.queueDeclare().getQueue();
            String message = "test";
            channel.basicPublish("", queue, null, message.getBytes());
            assertEquals(1, channel.messageCount(queue), "Message should be in queue");
        }
    }
}
