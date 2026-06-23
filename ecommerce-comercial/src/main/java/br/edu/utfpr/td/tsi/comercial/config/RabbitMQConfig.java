package br.edu.utfpr.td.tsi.comercial.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public Queue fiscalQueue() {
		return QueueBuilder.durable("fila.fiscal").build();
	}

	@Bean
	public Queue estoqueQueue() {
		return QueueBuilder.durable("fila.estoque").build();
	}

	@Bean
	public Queue entregaQueue() {
		return QueueBuilder.durable("fila.entrega").build();
	}
}