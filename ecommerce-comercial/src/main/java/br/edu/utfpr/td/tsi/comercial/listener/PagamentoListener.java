package br.edu.utfpr.td.tsi.comercial.listener;

import br.edu.utfpr.td.tsi.comercial.model.PedidoRequest;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.logging.Logger;

@Service
public class PagamentoListener {

	private static final Logger logger = Logger.getLogger(PagamentoListener.class.getSimpleName());
	private final RabbitTemplate rabbitTemplate;

	public PagamentoListener(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@RabbitListener(queues = "fila.pagamento")
	public void processarPagamento(PedidoRequest pedido) {
		logger.info("[Pagamento Service] Recebido processamento para: " + pedido.getEmailUsuario());

		if (pedido.getValorBase() != null && pedido.getValorBase().compareTo(BigDecimal.ZERO) < 0) {
			logger.warning("[Pagamento Service] Valor negativo detectado. Rejeitando mensagem para DLQ.");
			throw new AmqpRejectAndDontRequeueException("Valor de pagamento negativo não permitido.");
		}

		logger.info("[Pagamento Service] Pagamento aprovado com sucesso!");

		rabbitTemplate.convertAndSend("fila.email", "Pagamento aprovado para o pedido " + pedido.getEmailUsuario());
		rabbitTemplate.convertAndSend("fila.fiscal", pedido);
		rabbitTemplate.convertAndSend("fila.entrega", pedido);
	}
}