/**
 * @author Dulcidio Sobrinho
 * 14/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

/**
 * @author Dulcidio Sobrinho
 *
 */
public enum TipoConta {

	CORRENTE("Conta Corrente"),
	POUPANCA("Conta Poupanca");
	
	private String tipo;
	
	TipoConta(String tipo){
		this.tipo=tipo;
	}
	
	public String getTipo(){
		return tipo;
	}
}
