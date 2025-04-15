package com.github.fridujo.rabbitmq.mock;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.GetResponse;
import com.github.fridujo.rabbitmq.mock.MockConnectionFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExchangeBindingTest {

    @Test
    void testExchangeBinding() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        try (Connection conn = factory.newConnection();
             Channel channel = conn.createChannel()) {
            String exchange = "testEx";
            String queue = channel.queueDeclare().getQueue();
            channel.exchangeDeclare(exchange, "direct");
            channel.queueBind(queue, exchange, "key");
            channel.basicPublish(exchange, "key", null, "bound".getBytes());
            GetResponse response = channel.basicGet(queue, true);
            assertNotNull(response, "Message should be delivered via binding");
        }
    }
}
