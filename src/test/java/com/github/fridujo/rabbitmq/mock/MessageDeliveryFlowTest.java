package com.github.fridujo.rabbitmq.mock;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.GetResponse;
import com.github.fridujo.rabbitmq.mock.MockConnectionFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageDeliveryFlowTest {

    @Test
    void testMessageDeliveryFlow() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        try (Connection conn = factory.newConnection();
             Channel channel = conn.createChannel()) {
            String queue = channel.queueDeclare().getQueue();
            String message = "flow";
            channel.basicPublish("", queue, null, message.getBytes());
            GetResponse response = channel.basicGet(queue, true);
            assertEquals(message, new String(response.getBody()), "Message should flow correctly");
        }
    }
}
