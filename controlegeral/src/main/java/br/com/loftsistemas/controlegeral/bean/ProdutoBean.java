/**
 * @author Dulcidio Sobrinho
 * 21/01/2019
 */
package br.com.loftsistemas.controlegeral.bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.loftsistemas.controlegeral.dao.CategoriaProdutoDao;
import br.com.loftsistemas.controlegeral.dao.ProdutoDao;
import br.com.loftsistemas.controlegeral.dominio.CategoriaProduto;
import br.com.loftsistemas.controlegeral.dominio.Produto;
 
import br.com.loftsistemas.controlegeral.dominio.Unidade;
import br.com.loftsistemas.controlegeral.util.HibernateUtil;
import br.com.loftsistemas.controlegeral.util.UtilRelatorios;

/**
 * @author Dulcidio Sobrinho
 *
 */

@SuppressWarnings("serial")
@ManagedBean (name="produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable{
	
	

	private Produto produto= new Produto();
	private ProdutoDao produtoDao = new ProdutoDao();
	private List<Produto>produtos ;
	private CategoriaProduto categoria = new CategoriaProduto();
	private CategoriaProdutoDao categoriaDao = new CategoriaProdutoDao();
	private List<CategoriaProduto>categorias;
	private String tipoReg ;
	private Long totalG=0L;
	private List<String> lista =  new ArrayList<String>();
	
	{
		lista.add(0,"Produto");
		lista.add(1,"Servico");
	}
	
	
	@PostConstruct
	public void listar(){
		try{
			produtos=produtoDao.listar();
			categorias=categoriaDao.listar();
			contar(totalG);
			
		}catch(Exception e){
			Messages.addGlobalError("Erro ao listar produtos!");
			e.printStackTrace();
		}
	}
	
	public void limpar(){
		produto = new Produto();
		categoria=new CategoriaProduto();
    	produtos=produtoDao.listar();
	}
	
	public void salvar(){
        try{
        	produto.setTipo(tipoReg);
			produtoDao.merge(produto);
        	produto = new Produto();
        	produtos=produtoDao.listar();
        	categorias=categoriaDao.listar();
        	 mensagemOk();
        	 retornarClean();
        	
		}catch(Exception e){
			Messages.addGlobalError("Erro ao salvar produto/Serviço!");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
    	try{
    		 
    		produto=(Produto)evento.getComponent().getAttributes().get("produtoSelecionado");
    	}catch(RuntimeException erros){
    		Messages.addGlobalError("Erro ao selecionar produto/Serviço");
    	}
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
    	        null, new FacesMessage("Produto/Serviço!"));
    	 
    	  FacesContext.getCurrentInstance()
    	      .getExternalContext()
    	      .getFlash().setKeepMessages(true);
    	 
    	  return "listar/listaProdutos.xhtml?faces-redirect=true";
    	}

    public void changeRadio(ValueChangeEvent evento){
    	tipoReg = (String)evento.getNewValue();
    }

    public void habilita(){
    	if(tipoReg.equals("Produto")){
    		tipoReg="Produto";
    		
    	}else{
    		tipoReg="Servico";
    	}
    }
    
    public void contar(Long total){
		try{
            total=produtoDao.ContarTotal(total);
            totalG=total;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
    public void imprimir(){
    	  
    		 
    		HashMap parametros = new HashMap();
    		Connection conexao = HibernateUtil.getConexao();
    		UtilRelatorios.imprimirRelatorio("produtos", parametros, conexao);
    	 
    }
    
	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}


	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}


	public List<Produto> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
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
	
	public Unidade[] getUnidade(){
		return Unidade.values();
	}

	public String getTipoReg() {
		return tipoReg;
	}

	public void setTipoReg(String tipoReg) {
		this.tipoReg = tipoReg;
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}

	public Long getTotalG() {
		return totalG;
	}

	public void setTotalG(Long totalG) {
		this.totalG = totalG;
	}
	
	 
	
	
	
}
