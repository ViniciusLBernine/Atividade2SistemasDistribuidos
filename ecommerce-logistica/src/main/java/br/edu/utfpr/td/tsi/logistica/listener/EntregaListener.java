package br.edu.utfpr.td.tsi.logistica.listener;

import br.edu.utfpr.td.tsi.logistica.model.PedidoRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EntregaListener {
	private static final Logger logger = Logger.getLogger(EntregaListener.class.getSimpleName());
	private final RabbitTemplate rabbitTemplate;

	public EntregaListener(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@RabbitListener(queues = "fila.entrega")
	public void agendarEntrega(PedidoRequest pedido) {
		logger.info("[Entrega Service] Agendando pacote para o CEP: " + pedido.getCep());
		rabbitTemplate.convertAndSend("fila.email", "Sua entrega está agendada e em breve chegará no seu endereço!");
	}
}

@Service
class EmailListener {
	private static final Logger logger = Logger.getLogger(EmailListener.class.getSimpleName());

	@RabbitListener(queues = "fila.email")
	public void enviarEmail(String mensagem) {
		logger.info("[Email Service] Disparando notificação: " + mensagem);
	}
}