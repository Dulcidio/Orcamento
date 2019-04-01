/**
 * @author Dulcidio Sobrinho
 * 22/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Dulcidio Sobrinho
 *
 */
@SuppressWarnings("serial")
@Entity
public class Orcamento extends GenericDomain{
	
	@ManyToOne
	@JoinColumn()
	private Cliente cliente;
	@Column( )
	@Temporal(TemporalType.DATE)
	private Date data;
	@ManyToOne
	@JoinColumn(nullable=true)
	private Funcionario funcionario;
	@Column(nullable=false , precision = 7 , scale=2)
    private BigDecimal valorTotal;
    @Column( precision = 7 , scale=2)
    private BigDecimal valorProdutos;
    @Column( precision = 7 , scale=2)
    private BigDecimal valorServicos;
    @Column()
    private String observacoes;
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "orcamento")
    @Fetch(FetchMode.SUBSELECT)
    private List<ServicosOrcamento> servicosOrcamento;
    @Column( )
	@Enumerated(EnumType.STRING)
    private SituacaoOrcamento situacaoOrcamento;
    @Column( )
    private Integer desconto;
    
	public List<ServicosOrcamento> getServicosOrcamento() {
		return servicosOrcamento;
	}
	public void setServicosOrcamento(List<ServicosOrcamento> servicosOrcamento) {
		this.servicosOrcamento = servicosOrcamento;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	 
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getValorProdutos() {
		return valorProdutos;
	}
	public void setValorProdutos(BigDecimal valorProdutos) {
		this.valorProdutos = valorProdutos;
	}
	public BigDecimal getValorServicos() {
		return valorServicos;
	}
	public void setValorServicos(BigDecimal valorServicos) {
		this.valorServicos = valorServicos;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	 
	public SituacaoOrcamento getSituacaoOrcamento() {
		return situacaoOrcamento;
	}
	public void setSituacaoOrcamento(SituacaoOrcamento situacaoOrcamento) {
		this.situacaoOrcamento = situacaoOrcamento;
	}
	public Integer getDesconto() {
		return desconto;
	}
	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	 
	

}
