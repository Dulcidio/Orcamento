/**
 * @author Dulcidio Sobrinho
 * 20/01/2019
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
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
 

import br.com.loftsistemas.controlegeral.dao.CategoriaServicosDao;
import br.com.loftsistemas.controlegeral.dominio.CategoriaServico;
 

/**
 * @author Dulcidio Sobrinho
 *
 */
@SuppressWarnings("serial")
@ManagedBean (name="categoriaServicoBean")
@SessionScoped
public class CategoriaServicoBean implements Serializable {

	private CategoriaServico categoria = new CategoriaServico();
	private List<CategoriaServico> categorias;
	private CategoriaServicosDao  categoriaDao = new CategoriaServicosDao();
	
	
	@PostConstruct
	public void listar(){
		try{
			categorias=categoriaDao.listar();
			
		}catch(Exception e){
			Messages.addGlobalError("Erro ao listar categorias");
			e.printStackTrace();
		}
	}
	
	public void salvar(){
		try{
			categoriaDao.merge(categoria);
			categoria = new CategoriaServico();
			categorias=categoriaDao.listar();
			
			mensagemOk();
			retornarClean();
			
		}catch(Exception e){
			Messages.addGlobalError("Erro ao salvar categoria de serviço");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
    	try{
    		categoria=(CategoriaServico)evento.getComponent().getAttributes().get("categoriaSelecionado");
    	}catch(RuntimeException erros){
    		Messages.addGlobalError("Erro ao selecionar categoria serviço");
    	}
    }
	
	/*Metodo que evita o reenvio do form apos salvar caso se pressione f5 */
    public void  retornarClean() {
    	 
    	try {
    		 
			FacesContext.getCurrentInstance().getExternalContext().redirect("listaServicos.xhtml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*Metodo para permitir que as mensagens de confirmação seja apresentadas*/
    public String mensagemOk() {
    	  FacesContext.getCurrentInstance().addMessage(
    	        null, new FacesMessage("Categoria de serviço salvo!"));
    	 
    	  FacesContext.getCurrentInstance()
    	      .getExternalContext()
    	      .getFlash().setKeepMessages(true);
    	 
    	  return "listar/listaServicos.xhtml?faces-redirect=true";
    	}
	
	public CategoriaServico getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaServico categoria) {
		this.categoria = categoria;
	}
	public List<CategoriaServico> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<CategoriaServico> categorias) {
		this.categorias = categorias;
	}
	public CategoriaServicosDao getCategoriaDao() {
		return categoriaDao;
	}
	public void setCategoriaDao(CategoriaServicosDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}
	
	
	
}
