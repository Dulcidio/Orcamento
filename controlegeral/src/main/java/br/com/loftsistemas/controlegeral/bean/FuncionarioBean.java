/**
 * @author Dulcidio Sobrinho
 * 16/01/2019
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
import org.omnifaces.util.Messages.Message;

import br.com.loftsistemas.controlegeral.dao.FuncionarioDao;
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
@ManagedBean (name="funcionarioBean")
@SessionScoped
public class FuncionarioBean implements Serializable{

	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios;
	private FuncionarioDao funcionarioDao = new FuncionarioDao();
	
	@PostConstruct
	public void listar(){
		try{
		funcionarios=funcionarioDao.listar();
		for(Funcionario f:funcionarios){
			System.out.println(f);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void salvar(){
		
		try{
			funcionarioDao.merge(funcionario);
			funcionario = new Funcionario();
			funcionarios= funcionarioDao.listar();
			
			mensagemOk();
			retornarClean();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*Metodo que evita o reenvio do form apos salvar caso se pressione f5 */
    public void  retornarClean() {
    	 
    	try {
    		 
			FacesContext.getCurrentInstance().getExternalContext().redirect("listaFuncionarios.xhtml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*Metodo para permitir que as mensagens de confirmação seja apresentadas*/
    public String mensagemOk() {
    	  FacesContext.getCurrentInstance().addMessage(
    	        null, new FacesMessage("Funcionário salvo!"));
    	 
    	  FacesContext.getCurrentInstance()
    	      .getExternalContext()
    	      .getFlash().setKeepMessages(true);
    	 
    	  return "listar/listaFuncionarios.xhtml?faces-redirect=true";
    	}
    
    public void editar(ActionEvent evento){
    	try{
    		funcionario=(Funcionario)evento.getComponent().getAttributes().get("funcionarioSelecionado");
    	}catch(RuntimeException erros){
    		Messages.addGlobalError("Erro ao selecionar funcionário");
    	}
    }
	
    public void imprimir(){
      
    		HashMap parametros = new HashMap();
    		Connection conexao = HibernateUtil.getConexao();
    		UtilRelatorios.imprimirRelatorio("funcionarios", parametros, conexao);
     
    }
    
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public FuncionarioDao getFuncionarioDao() {
		return funcionarioDao;
	}
	public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
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

	@Override
	public String toString() {
		return "FuncionarioBean [funcionario=" + funcionario
				+ ", funcionarios=" + funcionarios + ", funcionarioDao="
				+ funcionarioDao + "]";
	}
	
	
	
	
}
