package br.edu.utfpr.td.tsi.comercial.endpoint;

import br.edu.utfpr.td.tsi.comercial.model.Produto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/ecommerce.produtos")
@CrossOrigin(origins = "*")
public class ProdutoEndpoint {

	private static final Logger logger = Logger.getLogger(ProdutoEndpoint.class.getSimpleName());

	@GetMapping("/catalogo")
	public ResponseEntity<?> carregarCatalogo() {
		logger.info("Carregando catálogo de produtos via JSON...");
		try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("/produtos.json"))) {
			Type listType = new TypeToken<ArrayList<Produto>>() {
			}.getType();
			List<Produto> produtos = new Gson().fromJson(reader, listType);
			return ResponseEntity.status(HttpStatus.OK).body(produtos);
		} catch (Exception e) {
			logger.severe("Erro ao ler produtos.json: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}