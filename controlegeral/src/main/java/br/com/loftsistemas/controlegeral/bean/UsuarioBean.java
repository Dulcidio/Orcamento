/**
 * @author Dulcidio Sobrinho
 * 15/01/2019
 */
package br.com.loftsistemas.controlegeral.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.loftsistemas.controlegeral.dao.FuncionarioDao;
import br.com.loftsistemas.controlegeral.dao.UsuarioDao;
import br.com.loftsistemas.controlegeral.dominio.Estatus;
import br.com.loftsistemas.controlegeral.dominio.Funcionario;
import br.com.loftsistemas.controlegeral.dominio.Perfil;
import br.com.loftsistemas.controlegeral.dominio.Usuario;

/**
 * @author Dulcidio Sobrinho
 *
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
	
	private Usuario usuario =  new Usuario();
	private List <Usuario> usuarios;
	private UsuarioDao usuarioDao = new UsuarioDao();
	private Funcionario funcionario =  new Funcionario();
	private FuncionarioDao funcionarioDao = new FuncionarioDao();
	private List<Funcionario> funcionarios;
	private String dataForm;
	
	
	@PostConstruct
	public void listar(){
		
	try{
		usuarios=usuarioDao.listar();
		funcionarios=funcionarioDao.listar();
		mostrarData(); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void salvar(){
		
		try{ 
		SimpleHash hash =  new SimpleHash("md5",usuario.getSenhaSemCriptografia());
	    usuario.setSenha(hash.toHex());
		usuarioDao.merge(usuario);
		usuario =  new Usuario();
		usuarios = usuarioDao.listar();
		funcionarios=funcionarioDao.listar(); 
		mensagemOk();
		retornarClean();
		
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao salvar o usuário");
		    erro.printStackTrace();
		}
	}
	
public void alterar(){
		
		try{ 
			SimpleHash hash =  new SimpleHash("md5",usuario.getSenha());
		    usuario.setSenha(hash.toHex());
			usuarioDao.merge(usuario);
			usuario =  new Usuario();
			usuarios = usuarioDao.listar();
			 
			
			Messages.addGlobalInfo("Cadastro alterado com sucesso!");
		 	
		
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao salvar o usuário");
		    erro.printStackTrace();
		}
	}
	
public void alterarSenha(){
	
	try{ 
		SimpleHash hash =  new SimpleHash("md5",usuario.getSenha());
	    usuario.setSenha(hash.toHex());
		usuarioDao.merge(usuario);
		usuario =  new Usuario();
		usuarios = usuarioDao.listar();
	  
		Messages.addGlobalInfo("Senha alterada com sucesso!");
	 
	
	}catch(RuntimeException erro){
		Messages.addGlobalError("Ocorreu um erro ao alterar senha do usuário");
	    erro.printStackTrace();
	}
}
	/*Metodo que evita o reenvio do form apos salvar caso se pressione f5 */
    public void  retornarClean() {
    	 
    	try {
    		 
			FacesContext.getCurrentInstance().getExternalContext().redirect("listaUsuarios.xhtml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
    /*Metodo que evita o reenvio do form apos salvar caso se pressione f5 */
    public void  retornarCleanAlterar() {
    	 
    	try {
    		 
			FacesContext.getCurrentInstance().getExternalContext().redirect("listaUsuarios.xhtml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
	/*Metodo para permitir que as mensagens de confirmação seja apresentadas*/
    public String mensagemOk() {
    	  FacesContext.getCurrentInstance().addMessage(
    	        null, new FacesMessage("Usuário salvo com sucesso!"));
    	 
    	  FacesContext.getCurrentInstance()
    	      .getExternalContext()
    	      .getFlash().setKeepMessages(true);
    	 
    	  return "listaUsuarios.xhtml?faces-redirect=true";
    	}

    public String mostrarData(){
    	Date data = new Date();
    	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    	String dataformatada =  formato.format(data);
    	dataForm = dataformatada;
    	return dataForm;
    	
    }
    
    public void editar(ActionEvent evento){
    	try{
    		 
    		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
    	    String senha = usuario.getSenha();
    	   // System.out.println(senha);
    	}catch(RuntimeException erros){
    		Messages.addGlobalError("Erro ao selecionar Usuário");
    	}
    }
    
    public void excluir(ActionEvent evento){
    	try{
    		 
    		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
    		UsuarioDao dao = new UsuarioDao();
    		dao.excluir(usuario);
    		usuarios=dao.listar();
    		Messages.addGlobalInfo("Usuário excluido");
    	   
    	}catch(RuntimeException erros){
    		Messages.addGlobalError("Erro ao selecionar Usuário");
    		erros.printStackTrace();
    	}
    }

    public String inicialPage(){
		return "/pages/inicio.xhtml?faces-redirect=true";
	}
    
    
    public String usuarioPages(){
		return "/pages/usuarioCadastro.xhtml?faces-redirect=true";
	}
    
    public String voltaInicio(){
		 
		return "/pages/inicio.xhtml?faces-redirect=true" ;
	}
    
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	 public Perfil[] getPerfil(){
		 return Perfil.values();
	 }
  

	public String getDataForm() {
		return dataForm;
	}

	public void setDataForm(String dataForm) {
		this.dataForm = dataForm;
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
   
	
	
	
	
	

}
