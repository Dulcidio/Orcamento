/**
 * @author Dulcidio Sobrinho
 * 17/01/2019
 */
package br.com.loftsistemas.controlegeral.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;

/**
 * @author Dulcidio Sobrinho
 *
 */

@ManagedBean (name="navegacaoBean")
@SessionScoped

public class NavegacaoManageBean {

	public void cadastrarUsuario() {
		
		  ExternalContext externalContext = FacesContext.getCurrentInstance()
	                .getExternalContext();
	    try {
	          externalContext.redirect(externalContext.getRequestContextPath()
	                + "/pages/cadastro/usuarioCadastro.xhtml");
	    } catch (IOException e) {
	          e.printStackTrace();
	    }
		
		
	}
}
