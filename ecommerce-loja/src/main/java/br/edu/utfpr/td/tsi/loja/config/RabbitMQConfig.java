package br.edu.utfpr.td.tsi.loja.config;

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
	public Queue pagamentoQueue() {
		return QueueBuilder.durable("fila.pagamento").withArgument("x-dead-letter-exchange", "")
				.withArgument("x-dead-letter-routing-key", "fila.pagamento.dlq").build();
	}

	@Bean
	public Queue dlqPagamentoQueue() {
		return QueueBuilder.durable("fila.pagamento.dlq").build();
	}

	@Bean
	public Queue emailQueue() {
		return QueueBuilder.durable("fila.email").build();
	}
}