/**
 * @author Dulcidio Sobrinho
 * 20/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Dulcidio Sobrinho
 *
 */

@SuppressWarnings("serial")
@Entity
public class CategoriaServico extends GenericDomain {

	@Column(nullable = false)
	private String descricao;
	@Column( )
	private String observacao;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
}
