/**
 * @author Dulcidio Sobrinho
 * 15/01/2019
 */
package br.com.loftsistemas.controlegeral.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.omnifaces.util.Messages.Message;

import br.com.loftsistemas.controlegeral.dao.UsuarioDao;
import br.com.loftsistemas.controlegeral.dominio.Funcionario;
import br.com.loftsistemas.controlegeral.dominio.Usuario;

/**
 * @author Dulcidio Sobrinho
 *
 */

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {
	
	private Usuario usuario ;
	private Usuario userLogin ;
	private Funcionario funcionario;
	 

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@PostConstruct
	public void iniciar(){
		
		
		usuario = new Usuario();
		usuario.setFuncionario(new Funcionario());
	 
	}
		
	public Usuario getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(Usuario userLogin) {
		this.userLogin = userLogin;
	}

	public void autenticar(){
		try{
	    UsuarioDao userDao = new UsuarioDao();
	    userLogin = userDao.autenticar(usuario.getUsername(), usuario.getSenha());
	    if(userLogin == null){
	    	Messages.addGlobalError("Usuario e/ou senha incorretos!");
	    	return;
	    }
	     
	    
		Faces.redirect("./pages/inicio.xhtml");
		 
		 
		}catch(IOException e){
			e.printStackTrace();
			Messages.addGlobalError("erro ao autenticar");
		}
	}
	
	public boolean temPermissoes(List<String>permissoes ){
		
		for(String permissao: permissoes){
			if(userLogin.getPerfil().equals(permissao)){
				return true;
			}
		}
		return false;		
	}
	 
	
	public String sair(){
		userLogin = null;
		Messages.addGlobalInfo("Obrigado por usar o sistema!");
		return "/pages/index.xhtml?faces-redirect=true";
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	 

	
}
