package br.edu.utfpr.td.tsi.logistica.endpoint;

import br.edu.utfpr.td.tsi.logistica.model.Endereco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/cep")
@CrossOrigin(origins = "*")
public class CepEndpoint {

	private static final Logger logger = Logger.getLogger(CepEndpoint.class.getSimpleName());

	@GetMapping(value = "/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> consultarCep(@PathVariable String cep) {
		logger.info("Buscando endereço em enderecos.json para o CEP: " + cep);
		try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("/enderecos.json"))) {
			Type listType = new TypeToken<ArrayList<Endereco>>() {
			}.getType();
			List<Endereco> enderecos = new Gson().fromJson(reader, listType);

			Endereco end = enderecos.isEmpty() ? new Endereco() : enderecos.get(0);
			end.setCep(cep);

			return ResponseEntity.status(HttpStatus.OK).body(end);
		} catch (Exception e) {
			logger.severe("Erro na leitura de enderecos.json: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}