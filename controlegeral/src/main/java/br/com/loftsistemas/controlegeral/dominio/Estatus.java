/**
 * @author Dulcidio Sobrinho
 * 16/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

/**
 * @author Dulcidio Sobrinho
 *
 */
public enum Estatus {
	
	ATIVA ("ativo"),
	INATIVA("inativo");
	

	private String descricao;

	 Estatus(String descricao){
		 this.descricao = descricao;
	 }

	public String getDescricao() {
		return descricao;
	}
	
	 
 	
}
