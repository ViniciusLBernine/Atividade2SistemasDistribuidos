package br.edu.utfpr.td.tsi.comercial.listener;

import br.edu.utfpr.td.tsi.comercial.model.PedidoRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;
import java.util.List;

@Service
public class FiscalListener {
	private static final Logger logger = Logger.getLogger(FiscalListener.class.getSimpleName());
	private final RabbitTemplate rabbitTemplate;

	public FiscalListener(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@RabbitListener(queues = "fila.fiscal")
	public void processarFiscal(PedidoRequest pedido) {
		logger.info("[Fiscal Service] Emitindo NFe para " + pedido.getEmailUsuario());
		rabbitTemplate.convertAndSend("fila.estoque", pedido.getProdutosIds());
		rabbitTemplate.convertAndSend("fila.email", "Nota fiscal emitida com sucesso para sua compra!");
	}
}

@Service
class EstoqueListener {
	private static final Logger logger = Logger.getLogger(EstoqueListener.class.getSimpleName());

	@RabbitListener(queues = "fila.estoque")
	public void baixarEstoque(List<String> produtosIds) {
		logger.info("[Produtos Service] Realizando baixa de estoque para os itens: " + produtosIds);
	}
}