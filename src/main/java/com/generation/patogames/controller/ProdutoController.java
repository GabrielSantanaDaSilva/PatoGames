package com.generation.patogames.controller;

import java.util.List;

import com.generation.patogames.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.generation.patogames.model.Produto;
import com.generation.patogames.repository.ProdutoRepository;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Produto> create(@RequestBody Produto produto){
		if(categoriaRepository.existsById(produto.getCategoria().getId())){
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inválida", null);
	}

	@PutMapping
	public ResponseEntity<Produto> update(@Valid @RequestBody Produto produto){
		if (produtoRepository.existsById(produto.getId())){
			if(categoriaRepository.existsById(produto.getCategoria().getId())){
				return ResponseEntity.ok().body(produtoRepository.save(produto));
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inválida", null);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
		if (!produtoRepository.existsById(id)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		produtoRepository.deleteById(id);
	}
}
