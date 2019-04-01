/**
 * @author Dulcidio Sobrinho
 * 20/01/2019
 */
package br.com.loftsistemas.controlegeral.bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.loftsistemas.controlegeral.dao.ClienteDao;
import br.com.loftsistemas.controlegeral.dominio.Cliente;
import br.com.loftsistemas.controlegeral.dominio.Estado;
import br.com.loftsistemas.controlegeral.dominio.EstadoCivil;
import br.com.loftsistemas.controlegeral.dominio.Funcionario;
import br.com.loftsistemas.controlegeral.dominio.Sexo;
import br.com.loftsistemas.controlegeral.util.HibernateUtil;
import br.com.loftsistemas.controlegeral.util.UtilRelatorios;

/**
 * @author Dulcidio Sobrinho
 *
 */
@SuppressWarnings("serial")

@ManagedBean (name="clienteBean")
@SessionScoped
public class ClienteBean implements Serializable{
	
	private Cliente cliente = new Cliente();
	private ClienteDao clienteDao = new ClienteDao();
	private List<Cliente> clientes;
	private Long totalG=0L;
	private List<Cliente> aniversariantes;
	private Long codigoCliente;
	private Cliente clienteFicha = new Cliente();
	
	@PostConstruct
	public void listar(){
		
		try{
			clientes=clienteDao.listar();
			aniversariantes=clienteDao.listaNiver();
			contar(totalG);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void contar(Long total){
		try{
            total=clienteDao.ContarTotal(total);
            totalG=total;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void salvar(){
		try{
		clienteDao.merge(cliente);
		cliente = new Cliente();
		clientes=clienteDao.listar();
		
		
		mensagemOk();
		retornarClean();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
    	try{
    		cliente=(Cliente)evento.getComponent().getAttributes().get("clienteSelecionado");
    		
    	}catch(RuntimeException erros){
    		Messages.addGlobalError("Erro ao selecionar cliente");
    	}
    }
	
	public void editarPrint(ActionEvent evento){
    	try{
    		cliente=(Cliente)evento.getComponent().getAttributes().get("clienteSelecionado");
    		codigoCliente=cliente.getCodigo();
    		 
    		 
    	}catch(RuntimeException erros){
    		Messages.addGlobalError("Erro ao selecionar cliente");
    	}
    }
 
	
	/*Metodo que evita o reenvio do form apos salvar caso se pressione f5 */
    public void  retornarClean() {
    	 
    	try {
    		 
			FacesContext.getCurrentInstance().getExternalContext().redirect("listaClientes.xhtml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*Metodo para permitir que as mensagens de confirmação seja apresentadas*/
    public String mensagemOk() {
    	  FacesContext.getCurrentInstance().addMessage(
    	        null, new FacesMessage("Cliente salvo!"));
    	 
    	  FacesContext.getCurrentInstance()
    	      .getExternalContext()
    	      .getFlash().setKeepMessages(true);
    	 
    	  return "listar/listaClientes.xhtml?faces-redirect=true";
    	}
     
    
    public void imprimir(){
    	 
    		HashMap parametros = new HashMap();
    		 
    		Connection conexao = HibernateUtil.getConexao();
    		UtilRelatorios.imprimirRelatorio("clientes", parametros, conexao);
    		
    	 
    }
    
    public void imprimirFicha(){
    	  
    		HashMap parametros = new HashMap();
    		parametros.put("CLIENTE_COD",codigoCliente);
    		Connection conexao = HibernateUtil.getConexao();
    		UtilRelatorios.imprimirRelatorio("fichaCliente", parametros, conexao);
    		
    	 
    }
	
	public Estado[] getEstado(){
		return Estado.values();
	}
	public EstadoCivil[] getEstatoCivil(){
		return EstadoCivil.values();
	}
	public Sexo[] getSexo(){
		return Sexo.values();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ClienteDao getClienteDao() {
		return clienteDao;
	}
	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public Long getTotalG() {
		return totalG;
	}
	public void setTotalG(Long totalG) {
		this.totalG = totalG;
	}
	public List<Cliente> getAniversariantes() {
		return aniversariantes;
	}
	public void setAniversariantes(List<Cliente> aniversariantes) {
		this.aniversariantes = aniversariantes;
	}
	public Long getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public Cliente getClienteFicha() {
		return clienteFicha;
	}
	public void setClienteFicha(Cliente clienteFicha) {
		this.clienteFicha = clienteFicha;
	}
  
    
}
