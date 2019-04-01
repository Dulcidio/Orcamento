/**
 * @author Dulcidio Sobrinho
 * 29/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Dulcidio Sobrinho
 *
 */
@SuppressWarnings("serial")
@Entity
public class Registro extends GenericDomain{
	
	@Column()
	private String fantasia;
	@Column( )
	private String razaoSocial;
	@Column( )
	private String cpfCnpj;
	@Column( )
	private String email;
	@Column( )
	private String celular;
	@Column( )
	private String fone;
	@Column( )
	private String rua;
	@Column( )
	private String cep;
	@Column( )
	private String nro;
	@Column( )
	private String bairro;
	@Column( )
	private String cidade;
	@Column( )
	@Enumerated(EnumType.STRING)
	private Estado estado;
	@Column( )
	private String inscricaoEstadual;
	@Column( )
	private String inscricaoMunicipal;
	@Column()
	private Long nroRegistro;
	@Column()
	private String tipo;
	
	
	public String getFantasia() {
		return fantasia;
	}
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}
	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}
	public Long getNroRegistro() {
		return nroRegistro;
	}
	public void setNroRegistro(Long nroRegistro) {
		this.nroRegistro = nroRegistro;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	

	
}
