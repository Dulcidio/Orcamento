/**
 * @author Dulcidio Sobrinho
 * 27/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

/**
 * @author Dulcidio Sobrinho
 *
 */
public enum SituacaoOrcamento {
	
	OK("Or�amento Ok"),
	CANCELADO("Or�amento Cancelado");
	
	private String situacao;
	
	SituacaoOrcamento(String situacao){
		this.situacao=situacao;
	}

	 public String getSituacao(){
		 return situacao;
	 }
}
