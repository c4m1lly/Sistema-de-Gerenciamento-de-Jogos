package com.generation.xpgames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.generation.xpgames.model.Categoria;
import com.generation.xpgames.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {
		return ResponseEntity.ok(categoriaRepository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id) {
		return categoriaRepository.findById(id)

				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	
	
	
	
	

	@GetMapping("/descricao/{descricao}") // VAI EM DESCRIÇÃO E PROCURA A PALAVRA QUE VOCE ESCREVEU E VERIFICA SE ESSA PALAVRA TEM NA DESCRIÇÃO
	// EXEMPLO DE URL: /CATEGORIA/DESCRICAO/TECNOLOGIA, ONDE TECNOLOGIA É A PALAVRA QUE VAI SER BUSCADA DENTRO DE DESCRIÇÃO

	public ResponseEntity<List<Categoria>> getByTitle(@PathVariable String descricao) { // @PathVariable O VALOR PASSADO NA
																					// URL VAI SER ARMAZENADO NA
																					// VARIAVEL DESCRIÇÃO
		
		// E VAI TRAZER UMA LISTA COM OS OBJETOS DE CATEGORIA QUE TENHAM A PALAVRA DIGITADA
		// NA DESCRIÇÃO
		return ResponseEntity.ok(categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
        // findAllByDescricaoContainingIgnoreCase = ESSE METODO BUSCA TODOS OS TEMAS ONDE A DESCRIÇÃO CONTEM A PALAVRA QUE VOCE DIGITOU.
	}
	
	
	
	
	
	

	@PostMapping
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	}
	
	
	
	
	
	
	

	@PutMapping
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {

		return categoriaRepository.findById(categoria.getId())

				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	
	
	
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT) // RETORNA 204 SE A OPERAÇÃO FOR BEM SUCEDIDA
	@DeleteMapping("/{id}") // DELETA PELO ID NA REQUISIÇÃO HTTP DELETE
	public void delete(@PathVariable Long id) { // DELCARA O METODO DELETE QUE RETORNA VOID E RECEBE UMM PARAMETRO ID,
												// QUE VAI SER PEGO PELO @PathVariable

		Optional<Categoria> categoria = categoriaRepository.findById(id);// PROCURAR A CATEGORIA PELO ID

		if (categoria.isEmpty()) // VERIFICA SE O OPTIONAL ESTÁ VAZIO (OU SEJA, NÃO ENCONTROU A POSTAGEM COM ID).
			throw new ResponseStatusException(HttpStatus.NOT_FOUND); // SE ESTIVER, LANÇA UMA EXCEÇÃO QUE RETORNA O
																		// STATUS HTTP 404 (NOT FOUND) PARA O CLIENTE.
		// throw new É USADO PARA LANÇAR UMA EXCEÇÃO

		categoriaRepository.deleteById(id);
	} // CASO A POSTAGEM SEJA ENCONTRADA, ELA É EXCLUÍDA DO BANCO DE DADOS PELO MÉTODO
		// DELETEBYID DO REPOSITÓRIO( QUE DELETA PELO ID).

}
