/**
 * @author Dulcidio Sobrinho
 * 22/01/2019
 */
package br.com.loftsistemas.controlegeral.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author Dulcidio Sobrinho
 *
 */

@SuppressWarnings("serial")
@ManagedBean (name="inicioBean")
@SessionScoped

public class InicioBean implements Serializable {
	
	public String cliente(){
		return "/pages/listar/listaClientes.xhtml?faces-redirect=true";
	}
	
	public String registro(){
		return "/pages/cadastro/registroCadastro.xhtml?faces-redirect=true";
	}
	
	public String funcionario(){
		return "/pages/listar/listaFuncionarios.xhtml?faces-redirect=true";
	}
	
	public String produtos(){
		return "/pages/listar/listaProdutos.xhtml?faces-redirect=true";
	}
	
	public String servicos(){
		return "/pages/listar/listaServicos.xhtml?faces-redirect=true";
	}
	
	public String novoOrcamento(){
		return "/pages/listar/abriOrcamento.xhtml?faces-redirect=true";
	}
	public String usuario(){
		return "/pages/listaUsuarios.xhtml?faces-redirect=true";
	}
	public String listaUsuario(){
    	return "/pages/listaUsuarios.xhtml?faces-redirect=true";
    }
	public String listaOrcamento(){
    	return "/pages/listar/listaOrcamentos.xhtml?faces-redirect=true";
    }
    
	

}
