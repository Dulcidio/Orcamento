/**
 * @author Dulcidio Sobrinho
 * 21/01/2019
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

import br.com.loftsistemas.controlegeral.dao.CategoriaProdutoDao;
import br.com.loftsistemas.controlegeral.dominio.CategoriaProduto;
 

/**
 * @author Dulcidio Sobrinho
 *
 */
@SuppressWarnings("serial")
@ManagedBean (name="categoriaProdutoBean")
@SessionScoped
public class CategoriaProdutoBean implements Serializable{

	private CategoriaProduto categoria = new CategoriaProduto();
	private CategoriaProdutoDao categoriaDao = new CategoriaProdutoDao();
	private List<CategoriaProduto>categorias;
	
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
			categoria= new CategoriaProduto();
			categorias=categoriaDao.listar();
			
			mensagemOk();
			retornarClean();
			
		}catch(Exception e){
			Messages.addGlobalError("Erro ao salvar categoria!");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
    	try{
    		categoria=(CategoriaProduto)evento.getComponent().getAttributes().get("categoriaProdutoSelecionado");
    	}catch(RuntimeException erros){
    		Messages.addGlobalError("Erro ao selecionar categoria");
    	}
    }
	
	public void limpar(){
		categoria =  new CategoriaProduto();
		categorias=categoriaDao.listar();
		
	}
	
	/*Metodo que evita o reenvio do form apos salvar caso se pressione f5 */
    public void  retornarClean() {
    	 
    	try {
    		 
			FacesContext.getCurrentInstance().getExternalContext().redirect("listaProdutos.xhtml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*Metodo para permitir que as mensagens de confirmação seja apresentadas*/
    public String mensagemOk() {
    	  FacesContext.getCurrentInstance().addMessage(
    	        null, new FacesMessage("Categoria salva!"));
    	 
    	  FacesContext.getCurrentInstance()
    	      .getExternalContext()
    	      .getFlash().setKeepMessages(true);
    	 
    	  return "listar/listaProdutos.xhtml?faces-redirect=true";
    	}
	
	public CategoriaProduto getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}
	public CategoriaProdutoDao getCategoriaDao() {
		return categoriaDao;
	}
	public void setCategoriaDao(CategoriaProdutoDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}
	public List<CategoriaProduto> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<CategoriaProduto> categorias) {
		this.categorias = categorias;
	}
	
	
	
}
