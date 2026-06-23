package br.edu.utfpr.td.tsi.loja.service;

import br.edu.utfpr.td.tsi.loja.model.PagamentoRequest;
import br.edu.utfpr.td.tsi.loja.model.PedidoRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.logging.Logger;

@Service
public class OrquestradorLojaService {

	private static final Logger logger = Logger.getLogger(OrquestradorLojaService.class.getSimpleName());
	private final RestTemplate restTemplate = new RestTemplate();
	private final RabbitTemplate rabbitTemplate;

	private static final String URL_LOGISTICA = "http://localhost:8082";

	public OrquestradorLojaService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public String processarCompra(PedidoRequest pedido) {
		logger.info("--- INICIANDO PROCESSAMENTO DE COMPRA ---");

		BigDecimal valorTotal = pedido.getValorBase() != null ? pedido.getValorBase() : BigDecimal.ZERO;

		if (pedido.getPagamento() == null) {
			pedido.setPagamento(new PagamentoRequest());
		}
		pedido.getPagamento().valorTotal(valorTotal);

		// Passo 4: Consultar CEP
		try {
			String enderecoJson = restTemplate.getForObject(URL_LOGISTICA + "/api/cep/" + pedido.getCep(),
					String.class);
			logger.info("Endereço consultado via REST: " + enderecoJson);
		} catch (Exception e) {
			logger.warning("Aviso ao consultar o CEP: " + e.getMessage());
		}

		// Passo 6: Dispara evento para o e-mail de confirmação
		rabbitTemplate.convertAndSend("fila.email", "Confirmação de pedido recebida para: " + pedido.getEmailUsuario());

		// Passo 7: Envia para a fila de pagamento
		rabbitTemplate.convertAndSend("fila.pagamento", pedido);

		logger.info("--- EVENTOS DISPARADOS PARA AS FILAS COM SUCESSO ---");

		return "Pedido em processamento! NFe e dados de entrega serão enviados para o seu e-mail.";
	}
}