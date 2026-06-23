package br.edu.utfpr.td.tsi.loja.service;

import br.edu.utfpr.td.tsi.loja.model.PedidoRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Service
public class DlqListener {

	private static final Logger logger = Logger.getLogger(DlqListener.class.getSimpleName());

	static {
		try {
			FileHandler fh = new FileHandler("erros-dlq.log", true);
			fh.setFormatter(new SimpleFormatter());
			logger.addHandler(fh);
		} catch (IOException e) {
			System.err.println("Falha ao inicializar o arquivo de log DLQ.");
		}
	}

	@RabbitListener(queues = "fila.pagamento.dlq")
	public void processarErroPagamento(PedidoRequest pedido) {
		logger.severe("ERRO CRITICO (DLQ): Falha ao processar pagamento do pedido para o email: "
				+ pedido.getEmailUsuario() + " | Valor: " + pedido.getValorBase());
	}
}