/**
 * @author Dulcidio Sobrinho
 * 14/01/2019
 */
package br.com.loftsistemas.controlegeral.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * @author Dulcidio Sobrinho
 *
 */
@SuppressWarnings("serial")
@Entity
 
 
public class Usuario extends GenericDomain {
	
	@Transient
	private String senhaSemCriptografia;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String senha;
	 
	@Column( )
	@Enumerated(EnumType.STRING)
	private Perfil perfil;
	@OneToOne
	@JoinColumn()
	private Funcionario funcionario;
	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}
	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	 
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
 
	
	
	
	 
}
