package com.github.fridujo.rabbitmq.mock;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.GetResponse;
import com.github.fridujo.rabbitmq.mock.MockConnectionFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultipleChannelsTest {

    @Test
    void testMultipleChannels() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        try (Connection conn = factory.newConnection();
             Channel ch1 = conn.createChannel();
             Channel ch2 = conn.createChannel()) {
            String queue = ch1.queueDeclare().getQueue();
            String message = "multi";
            ch1.basicPublish("", queue, null, message.getBytes());
            GetResponse response = ch2.basicGet(queue, true);
            assertEquals(message, new String(response.getBody()), "Channels should integrate");
        }
    }
}
