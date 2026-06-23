package br.edu.utfpr.td.tsi.loja.endpoint;

import br.edu.utfpr.td.tsi.loja.model.PedidoRequest;
import br.edu.utfpr.td.tsi.loja.service.OrquestradorLojaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loja")
@CrossOrigin(origins = "*")
public class LojaEndpoint {
	private final OrquestradorLojaService orquestrador;

	public LojaEndpoint(OrquestradorLojaService orquestrador) {
		this.orquestrador = orquestrador;
	}

	@PostMapping("/confirmar")
	public ResponseEntity<?> confirmarCompra(@RequestBody PedidoRequest pedido) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(orquestrador.processarCompra(pedido));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}
	}
}