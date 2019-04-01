/**
 * @author Dulcidio Sobrinho
 * 14/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

/**
 * @author Dulcidio Sobrinho
 *
 */
public enum Unidade {
	
	UNID	("UNIDADE"),
	KG	    ("QUILOGRAMA"),
	GRAMAS	("GRAMAS"),
	PACOTE	("PACOTE"),
	DUZIA	("DUZIA"),
	CX	    ("CAIXA"),
	CX2	    ("CAIXA COM 2 UNIDADES"),
	CX3	    ("CAIXA COM 3 UNIDADES"),
	CX5	    ("CAIXA COM 5 UNIDADES"),
	CX10	("CAIXA COM 10 UNIDADES"),
	CX15	("CAIXA COM 15 UNIDADES"),
	CX20	("CAIXA COM 20 UNIDADES"),
	CX25	("CAIXA COM 25 UNIDADES"),
	CX50	("CAIXA COM 50 UNIDADES"),
	CX100	("CAIXA COM 100 UNIDADES"),
	CM	    ("CENTIMETRO"),
	CM2	    ("CENTIMETRO QUADRADO"),
	CART	("CARTELA"),
	CENTO	("CENTO"),
	AMPOLA("AMPOLA"),
	BALDE("BALDE"),
	BANDEJ("BANDEJA"),
	BARRA("BARRA"),
	BISNAG("BISNAGA"),
	BLOCO("BLOCO"),
	BOBINA("BOBINA"),
	BOMB	("BOMBONA"),
	CAPS	("CAPSULA"),
	CJ	    ("CONJUNTO"),
	DISP	("DISPLAY"),
	EMBAL	("EMBALAGEM"),
	FARDO	("FARDO"),
	FOLHA	("FOLHA"),
	FRASCO	("FRASCO"),
	GALAO	("GALÃO"),
	GF	    ("GARRAFA"),
	JOGO	("JOGO"),
	KIT	    ("KIT"),
	LATA	("LATA"),
	LITRO	("LITRO"),
	M	    ("METRO"),
	M2	    ("METRO QUADRADO"),
	M3	    ("METRO CÚBICO"),
	MILHEI	("MILHEIRO"),
	ML	    ("MILILITRO"),
	MWH	    ("MEGAWATT HORA"),
	PALETE	("PALETE"),
	PARES	("PARES"),
	PC	    ("PEÇA"),
	POTE	("POTE"),
	K	    ("QUILATE"),
	RESMA	("RESMA"),
	ROLO	("ROLO"),
	SACO	("SACO"),
	SACOLA	("SACOLA"),
	TAMBOR	("TAMBOR"),
	TANQUE	("TANQUE"),
	TON	    ("TONELADA"),
	TUBO	("TUBO"),
	VASIL	("VASILHAME"),
	VIDRO	("VIDRO");
	
	private String tipoUnidade;

	 Unidade(String tipoUnidade){
		 this.tipoUnidade=tipoUnidade;
	 }
	
	  public String getTipoUnidade(){
		  return tipoUnidade;
	  }

}
