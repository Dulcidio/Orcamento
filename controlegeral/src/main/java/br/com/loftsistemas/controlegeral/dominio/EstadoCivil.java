/**
 * @author Dulcidio Sobrinho
 * 14/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

/**
 * @author Dulcidio Sobrinho
 *
 */
public enum EstadoCivil {
	

	SOLTEIRO("Solteiro"),
    CASADO("Casado"),
    SEPARADO("Separado"),
    DIVORCIADO("Divorciado"),
    VIUVO("Viúvo");
 
    private String descricao;
 
    EstadoCivil(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return this.descricao;
    }

}
