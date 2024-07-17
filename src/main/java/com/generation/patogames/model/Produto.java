package com.generation.patogames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O Atributo nome é obrigatório")
	@Size(min = 3, max = 100, message = "O atributo nome deve conter no mínimo 3 e no maximo 100 caracteres")
	private String nome;
	
	@NotBlank(message = "O Atributo Preço é obrigatório" )
	@Digits(integer = 4, fraction = 2)
	private BigDecimal preco;
	
	@NotBlank(message = "O Atributo descrição é obrigatório")
	@Size(min = 10, max = 500, message = "O atributo descrição deve conter no minimo 10 e no maximo 500 caracteres")
	private String descricao;
	
	@NotBlank(message = "O Atributo imagem é obrigatório")
	private String imagem;
	
	@NotBlank(message = "O Atributo estoque é obrigatório" )
	private int estoque;
	
	private int avaliacao;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria  categoria;
	

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public int getEstoque() {
		return estoque;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
}
