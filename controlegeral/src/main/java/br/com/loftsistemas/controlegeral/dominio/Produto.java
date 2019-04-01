/**
 * @author Dulcidio Sobrinho
 * 21/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Dulcidio Sobrinho
 *
 */
@SuppressWarnings("serial")
@Entity
public class Produto  extends GenericDomain{

	@Column(nullable = false)
	private String descricao;
	@Column()
	@Enumerated(EnumType.STRING)
	private Unidade unidade;
	@Column( )
	private String referencia;
	@Column( )
	private String barras;
	@Column(nullable=false,precision=7,scale=2)
	private BigDecimal precoVista;
	 
	@Column()
	private Integer quantidade;
	@Column( )
	private String observacoes;
	@ManyToOne
	@JoinColumn()
	private CategoriaProduto categoria;
	
	@Column(nullable = false)
	private int minimo;
	@Column( )
	@Temporal(TemporalType.DATE)
	private Date validade;
	@Column()
	private String tipo;
	
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getBarras() {
		return barras;
	}
	public void setBarras(String barras) {
		this.barras = barras;
	}
	public BigDecimal getPrecoVista() {
		return precoVista;
	}
	public void setPrecoVista(BigDecimal precoVista) {
		this.precoVista = precoVista;
	}
	 
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public CategoriaProduto getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}
	public int getMinimo() {
		return minimo;
	}
	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
