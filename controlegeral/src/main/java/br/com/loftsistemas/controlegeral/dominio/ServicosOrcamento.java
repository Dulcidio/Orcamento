/**
 * @author Dulcidio Sobrinho
 * 22/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Dulcidio Sobrinho
 *
 */
@SuppressWarnings("serial")
@Entity
public class ServicosOrcamento extends GenericDomain {
	 
	@Column()
	private String tipo;
 
	@Column()
	private String descricao;
	@Column(nullable=false , precision = 7 , scale=2)
    private BigDecimal valor;
	@ManyToOne
	@JoinColumn(nullable=true)
	private Orcamento orcamento;
	
	@ManyToOne
	@JoinColumn()
	private Produto produto;
	@Column()
	private Short quantidade;
	
	 
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	 
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Orcamento getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Short getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}
	 
	
	
	

}
