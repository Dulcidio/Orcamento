/**
 * @author Dulcidio Sobrinho
 * 27/01/2019
 */
package br.com.loftsistemas.controlegeral.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.loftsistemas.controlegeral.bean.AutenticacaoBean;
import br.com.loftsistemas.controlegeral.dominio.Usuario;



/**
 * @author Dulcidio Sobrinho
 *
 */
@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener{

	 
	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();
		boolean ehIndex = paginaAtual.contains("index.xhtml");
		if(!ehIndex){
			AutenticacaoBean autenticacao =	Faces.getSessionAttribute("autenticacaoBean");
			 if(autenticacao == null){
				 Faces.navigate("/pages/index.xhtml");
				 return;
			 }
			 Usuario usuario = autenticacao.getUserLogin();
			 if(usuario == null ){
				 Faces.navigate("/pages/index.xhtml");
				 return;
			 }
		}
	
		
	 
		
	}
 
	@Override
	public void beforePhase(PhaseEvent event) {
		 
		
	}

 
	@Override
	public PhaseId getPhaseId() {
		 
		return PhaseId.RESTORE_VIEW;
	}

}
