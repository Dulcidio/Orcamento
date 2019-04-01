/**
 * @author Dulcidio Sobrinho
 * 29/01/2019
 */
package br.com.loftsistemas.controlegeral.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.omnifaces.util.Messages;
 





import br.com.loftsistemas.controlegeral.dao.RegistroDao;
import br.com.loftsistemas.controlegeral.dominio.Estado;
import br.com.loftsistemas.controlegeral.dominio.Registro;

/**
 * @author Dulcidio Sobrinho
 *
 */
@SuppressWarnings("serial")
@ManagedBean (name="registroBean")
@SessionScoped
public class RegistroBean implements Serializable {

	private Registro registro = new Registro();
	private RegistroDao registroDao = new RegistroDao();
	private Long nroReg;
	private List<Registro> registros;
	private  String razaoSocial=null;;
	private  String cpfCnpj=null;
	private Long numero;
	private String tipoReg ;
private List<String> lista =  new ArrayList<String>();
	
	{
		lista.add(0,"Fisica");
		lista.add(1,"Juridica");
	}
	
	@PostConstruct
	public void listar(){
		registros=registroDao.listar();
		identificaRegistro();
		for(Registro r:registros){
			registro=r;
			 
			
		}
		razaoSocial=registro.getRazaoSocial();
		System.out.println(razaoSocial);
		cpfCnpj=registro.getCpfCnpj();
		System.out.println(cpfCnpj);
		 
	}
	
	public void identificaRegistro(){
		numero = registroDao.abreReg(nroReg);
		System.out.println("Qual o numero dessa merda"+ numero);
	}
	
	public void salvarRegistro(){
		
		if(numero != 0L){
			
			registro.setRazaoSocial(razaoSocial); 
			registro.setCpfCnpj(cpfCnpj);
			registro.setCelular(registro.getCelular());
			registroDao.merge(registro);
			registro = new Registro();
			mensagemOk();
			retornarClean();
			listar(); 
			
		}else{
			
			 registroDao.salvar(registro);
			 mensagemOkSalvar();
			 retornarClean();
			 numero=null;
			 listar();
		}
	}
	
	 
	/*Metodo que evita o reenvio do form apos salvar caso se pressione f5 */
    public void  retornarClean() {
    	 
    	try {
    		 
			FacesContext.getCurrentInstance().getExternalContext().redirect("registroCadastro.xhtml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*Metodo para permitir que as mensagens de confirmação seja apresentadas*/
    public String mensagemOk() {
    	  FacesContext.getCurrentInstance().addMessage(
    	        null, new FacesMessage("Alteração realizada com sucesso!"));
    	 
    	  FacesContext.getCurrentInstance()
    	      .getExternalContext()
    	      .getFlash().setKeepMessages(true);
    	 
    	  return "cadastro/registroCadastro.xhtml?faces-redirect=true";
    	}
	
    /*Metodo para permitir que as mensagens de confirmação seja apresentadas*/
    public String mensagemOkSalvar() {
    	  FacesContext.getCurrentInstance().addMessage(
    	        null, new FacesMessage("Registro salvo!"));
    	 
    	  FacesContext.getCurrentInstance()
    	      .getExternalContext()
    	      .getFlash().setKeepMessages(true);
    	 
    	  return "cadastro/registroCadastro.xhtml?faces-redirect=true";
    	}
    
    
    public void changeRadio(ValueChangeEvent evento){
    	tipoReg = (String)evento.getNewValue();
    }

    public void habilita(){
    	if(tipoReg.equals("Fisica")){
    		tipoReg="Fisica";
    		
    	}else{
    		tipoReg="Juridica";
    	}
    }
    
    
	public Registro getRegistro() {
		return registro;
	}
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}
	public RegistroDao getRegistroDao() {
		return registroDao;
	}
	public void setRegistroDao(RegistroDao registroDao) {
		this.registroDao = registroDao;
	}
	
	public Estado[] getEstado(){
		return Estado.values();
	}

	public Long getNroReg() {
		return nroReg;
	}

	public void setNroReg(Long nroReg) {
		this.nroReg = nroReg;
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
	
	
}
