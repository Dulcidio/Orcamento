/**
 * @author Dulcidio Sobrinho
 * 14/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;





/**
 * @author Dulcidio Sobrinho
 *
 */
@SuppressWarnings("serial")
@Entity
public class Funcionario extends GenericDomain {
	
	@Column(length = 200 , nullable = false)
	private String nome;
	@Column( )
	private String cpf;
	@Column( )
	private String matricula;
	@Column( )
	private String rg;
	@Column( )
	@Temporal(TemporalType.DATE)
	private Date admissao;
	@Column( )
	private String emissor;
	@Column( )
	private String localnasimento;
	@Column( )
	private String status;
	@Column( )
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@Column( )
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadocivil;
	@Column( )
	@Temporal(TemporalType.DATE)
	private Date aniversario;
	@Column( )
	private String fone;
	@Column( )
	private String celular;
	@Column( )
	private String whatsapp;
	@Column( )
	private String email;
	@Column( )
	private String rua;
	@Column( )
	private String nro;
	@Column( )
	private String bairro;
	@Column( )
	private String cep;
	@Column( )
	private String cidade;
	@Column( )
	private String observacao;
	@Column( )
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public Date getAdmissao() {
		return admissao;
	}
	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}
	public String getEmissor() {
		return emissor;
	}
	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}
	public String getLocalnasimento() {
		return localnasimento;
	}
	public void setLocalnasimento(String localnasimento) {
		this.localnasimento = localnasimento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public EstadoCivil getEstadocivil() {
		return estadocivil;
	}
	public void setEstadocivil(EstadoCivil estadocivil) {
		this.estadocivil = estadocivil;
	}
	public Date getAniversario() {
		return aniversario;
	}
	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
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
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
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
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", cpf=" + cpf + ", matricula="
				+ matricula + ", rg=" + rg + ", admissao=" + admissao
				+ ", emissor=" + emissor + ", localnasimento=" + localnasimento
				+ ", status=" + status + ", sexo=" + sexo + ", estadocivil="
				+ estadocivil + ", aniversario=" + aniversario + ", fone="
				+ fone + ", celular=" + celular + ", whatsapp=" + whatsapp
				+ ", email=" + email + ", rua=" + rua + ", nro=" + nro
				+ ", bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade
				+ ", observacao=" + observacao + ", estado=" + estado + "]";
	}
	
	
	
	
	

}
