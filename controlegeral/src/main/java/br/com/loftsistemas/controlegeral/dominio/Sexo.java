/**
 * @author Dulcidio Sobrinho
 * 14/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

/**
 * @author Dulcidio Sobrinho
 *
 */
public enum Sexo {

	MASCULINO("Masculino"),
    FEMININO("Feminino");
 
    private String descricao;
 
    Sexo(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
}
