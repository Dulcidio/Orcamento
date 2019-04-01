/**
 * @author Dulcidio Sobrinho
 * 22/01/2019
 */
package br.com.loftsistemas.controlegeral.bean;

 
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
 
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
 
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletResponse;
 
 
import org.omnifaces.util.Messages;
 
import org.primefaces.model.chart.PieChartModel;

 

import br.com.loftsistemas.controlegeral.dao.ClienteDao;
import br.com.loftsistemas.controlegeral.dao.FuncionarioDao;
import br.com.loftsistemas.controlegeral.dao.OrcamentoDao;
 
 
import br.com.loftsistemas.controlegeral.dao.ServicosOrcamentosDao;
import br.com.loftsistemas.controlegeral.dao.UsuarioDao;
import br.com.loftsistemas.controlegeral.dominio.Cliente;
import br.com.loftsistemas.controlegeral.dominio.Funcionario;
import br.com.loftsistemas.controlegeral.dominio.Orcamento;
import br.com.loftsistemas.controlegeral.dominio.Produto;
 
import br.com.loftsistemas.controlegeral.dominio.ServicosOrcamento;
import br.com.loftsistemas.controlegeral.dominio.SituacaoOrcamento;
import br.com.loftsistemas.controlegeral.dominio.Usuario;
import br.com.loftsistemas.controlegeral.util.HibernateUtil;
import br.com.loftsistemas.controlegeral.util.UtilRelatorios;

/**
 * @author Dulcidio Sobrinho
 *
 */

@SuppressWarnings("serial")
@ManagedBean (name="orcamentoBean")
@SessionScoped
public class OrcamentoBean implements Serializable{
	
	private List<Produto> produtos;
	 
	private List<ServicosOrcamento> servicosOrcamento;
	private List<ServicosOrcamento> produtosOrcamento;
	private OrcamentoDao orcamentoDao = new OrcamentoDao();
	private Orcamento orcamento = new Orcamento();
	private List <Cliente> clientes;
	private List<Funcionario> funcionarios;
	private List<Usuario>usuarios;
	private Cliente cliente = new Cliente();
	private String dataForm; 
	private Usuario usuario = new Usuario();
	private Funcionario funcionario = new Funcionario();
	private FuncionarioDao funcionarioDao = new FuncionarioDao();
    private PieChartModel pizza;
    private List<Orcamento> orcamentos;
    private List<Orcamento> listadeOrcamentos;
    private Long totalG=0L;
    private Long produtoOrca = 0L;
	private ServicosOrcamentosDao servorcaDao = new ServicosOrcamentosDao();
	private List<ServicosOrcamento> servicosOrcamentoPizza;
	private BigDecimal descon;
	private Long numero;
	private Long nroReg;
    private List<String> lista =  new ArrayList<String>();
    private String ativo;
    private Long codigoOrcamento;
  
    private Cliente tipoReg ;
   {
		lista.add(0,"Ativo");
		lista.add(1,"Desativo");
	}
   
	public void detalhesOrcamento(){
		mostrarData();
		UsuarioDao uDao =  new UsuarioDao();
		contar(totalG);
		usuarios=uDao.listar();
		orcamento.setValorTotal(new BigDecimal("0.00"));
		orcamento.setDesconto(0);
		orcamento.setCliente(null);
		orcamento.setFuncionario(null);
		orcamento.setData(new Date());
		servicosOrcamento = new ArrayList<>();
		 
		servicosOrcamentoPizza= servorcaDao.listar();
		pizza(); 
	}
	
	public void carregaFunfClien() {
		OrcamentoDao oDao = new OrcamentoDao();
		orcamentos=oDao.listar();
		FuncionarioDao fDao = new FuncionarioDao();
	    funcionarios=fDao.listar();
	    ClienteDao cDao = new ClienteDao();
	    clientes=cDao.listar();
	}
	
	 
	
	public void carregaOrcamentos(){
		try{
		OrcamentoDao orcaDao = new OrcamentoDao();
		listadeOrcamentos=orcaDao.listar();
		servicosOrcamentoPizza= servorcaDao.listar();
		contar(totalG);
		pizza();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void contar(Long total){
		try{
            total=orcamentoDao.ContarTotal(total);
            totalG=total;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void contarProduto(Long total){
		try{
            total=servorcaDao.ContarTotal(total);
            produtoOrca=total;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void pizza(){
		
		pizza = new PieChartModel();
		
		for(ServicosOrcamento sp:servicosOrcamentoPizza ){
			pizza.set(sp.getDescricao(),sp.getQuantidade());
			
		}
		pizza.setTitle("Produtos e Serviços");
		pizza.setLegendPosition("e");
		pizza.setFill(false);
		pizza.setShowDataLabels(true);
		pizza.setDiameter(100);
		
	}
	
	
	public String mostrarData(){
    	Date data = new Date();
    	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    	String dataformatada =  formato.format(data);
    	dataForm = dataformatada;
    	return dataForm;
    	
    }
	 
	
	public String getDataForm() {
		return dataForm;
	}

	public void setDataForm(String dataForm) {
		this.dataForm = dataForm;
	}

	public void adicionarProduto( ActionEvent evento ){
		Produto produto = (Produto)evento.getComponent().getAttributes().get("produtoSelecionado");
		 
        int achou= -1;
		
		for(int posicao=0; posicao < servicosOrcamento.size(); posicao++){
			
			if(servicosOrcamento.get(posicao).getProduto().equals(produto)){
				achou=posicao;
			}
		}
		if(achou < 0 ){
		
	    ServicosOrcamento prodOrca = new ServicosOrcamento();
		prodOrca.setDescricao(produto.getDescricao());
		prodOrca.setValor(produto.getPrecoVista());
		prodOrca.setTipo(produto.getTipo());
		prodOrca.setQuantidade(new Short("1"));
		prodOrca.setProduto(produto);
		
		servicosOrcamento.add(prodOrca);
		mensagemAdicionarProduto(); 
		
		}else{
			 ServicosOrcamento prodOrca = servicosOrcamento.get(achou);
			 prodOrca.setQuantidade(new Short ( prodOrca.getQuantidade()+ 1 +""));
			 prodOrca.setValor(produto.getPrecoVista().multiply(new BigDecimal(prodOrca.getQuantidade())));
			 mensagemAdicionarProduto();  
		}
		
		calcularTotal();
	}
	
	public void remover(ActionEvent evento){
		ServicosOrcamento items = (ServicosOrcamento) evento.getComponent().getAttributes().get("itemSelecionado");
		
		int achou= -1;
		for(int posicao = 0; posicao < servicosOrcamento.size(); posicao++ ){
			if(servicosOrcamento.get(posicao).getProduto().equals(items.getProduto())){
				achou=posicao;
			}
		}
		if(achou > -1){
			servicosOrcamento.remove(achou);
		}
		calcularTotal();
	}
	
	public void calcularTotal(){
		orcamento.setValorTotal(new BigDecimal("0.00"));
		for(int posicao = 0; posicao < servicosOrcamento.size(); posicao++ ){
		ServicosOrcamento items = servicosOrcamento.get(posicao);
		orcamento.setValorTotal(orcamento.getValorTotal().add(items.getValor()));
		
		}
 	}
	
	public void desconto(){
		calcularTotal();
		int desc = orcamento.getDesconto();
		float val =  orcamento.getValorTotal().floatValue();
		float total =0;
		
		 if(desc > 0){
		   total = (val*desc)/100;
		   descon=(new BigDecimal(total));
		   orcamento.setValorTotal(orcamento.getValorTotal().subtract(descon).setScale(2,RoundingMode.DOWN));
		   //System.out.println(descon);
		 }else{
			 calcularTotal();
		 }
		cleanDescont();
		total=0;
		val=0;
		desc=0;
	}
	 
	public void cleanDescont(){
		descon = (new BigDecimal(1));
	}
	public void salvar(){
		
		try{
			if(orcamento.getValorTotal().signum()==0){
				Messages.addGlobalWarn("Adicione ao menos um item ao orçamento!");
				return;
			}
			
			OrcamentoDao orcaDao = new OrcamentoDao();
			orcamento.setSituacaoOrcamento(SituacaoOrcamento.OK);
			orcaDao.salvar(orcamento, servicosOrcamento);
			
			orcamento = new Orcamento();
			cliente = new Cliente();
			funcionario = new Funcionario();
			//servicosOrcamento = new ArrayList<>();
			 
			mensagemOk();
			retornarClean();
			 
			
			//Messages.addGlobalInfo("Orcamento finalizado!");
		}catch(Exception e){
			Messages.addGlobalError("Ocorreu erro ao finalizar orçamento");
			e.printStackTrace();
		}
	}
	
	/*Metodo que evita o reenvio do form apos salvar caso se pressione f5 */
    public void  retornarClean() {
    	 
    	try {
    		 
			FacesContext.getCurrentInstance().getExternalContext().redirect("abriOrcamento.xhtml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*Metodo que evita o reenvio do form apos salvar caso se pressione f5 */
    public void  retornarCleanLista() {
    	 
    	try {
    		 
			FacesContext.getCurrentInstance().getExternalContext().redirect("listaOrcamentos.xhtml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*Metodo para permitir que as mensagens de confirmação seja apresentadas*/
    public String mensagemOk() {
    	  FacesContext.getCurrentInstance().addMessage(
    	        null, new FacesMessage("Orçamento Finalizado!"));
    	 
    	  FacesContext.getCurrentInstance()
    	      .getExternalContext()
    	      .getFlash().setKeepMessages(true);
    	 
    	  return "listar/abriOrcamento.xhtml?faces-redirect=true";
    	}
	
    /*Metodo para permitir que as mensagens de confirmação seja apresentadas*/
    public String mensagemAdicionarProduto() {
    	  FacesContext.getCurrentInstance().addMessage(
    	        null, new FacesMessage("Produto Adicionado!"));
    	 
    	  FacesContext.getCurrentInstance()
    	      .getExternalContext()
    	      .getFlash().setKeepMessages(true);
    	 
    	  return "listar/abriOrcamento.xhtml?faces-redirect=true";
    	}
    
    public void editar(ActionEvent evento){
    	try{
    		orcamento=(Orcamento)evento.getComponent().getAttributes().get("orcamentoSelecionado");
    		cancelarOrcamento();
    	}catch(RuntimeException erros){
    		Messages.addGlobalError("Erro ao selecionar orçamento");
    	}
    } 
    public String mensagemCancelarOrcamento() {
  	  FacesContext.getCurrentInstance().addMessage(
  	        null, new FacesMessage("Orçamento Cancelado!"));
  	 
  	  FacesContext.getCurrentInstance()
  	      .getExternalContext()
  	      .getFlash().setKeepMessages(true);
  	 
  	  return "listar/abriOrcamento.xhtml?faces-redirect=true";
  	}
    public void cancelarOrcamento(){
    	try{
    		
    		orcamento.setSituacaoOrcamento(SituacaoOrcamento.CANCELADO);
    		orcamentoDao.merge(orcamento);
    		mensagemCancelarOrcamento(); 
    		retornarCleanLista();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    public void imprimirOrcamento(){
   	 
    	    HashMap parametros = new HashMap();
    		parametros.put("COD_ORCAMENTO",codigoOrcamento);
    		Connection conexao = HibernateUtil.getConexao();
    		UtilRelatorios.imprimirRelatorio("orcamento", parametros, conexao);
    		
    		 
    	 
    }
    
    public void changeRadio(ValueChangeEvent evento){
    	tipoReg = (Cliente)evento.getNewValue();
    }
 
    public void printOrcamento(ActionEvent evento){
    	try{
    		orcamento=(Orcamento)evento.getComponent().getAttributes().get("orcamentoSelecionado");
    		codigoOrcamento=orcamento.getCodigo();
    		 
    		 
    	}catch(RuntimeException erros){
    		Messages.addGlobalError("Erro ao selecionar cliente");
    	}
    }
    
    
    public void imprimir() {
    	Long nnn = numero=orcamentoDao.ultimoOrcamento(nroReg);
    	HashMap parametros = new HashMap();
		parametros.put("COD_ORCAMENTO",nnn);
		Connection conexao = HibernateUtil.getConexao();
		UtilRelatorios.imprimirRelatorio("orcamento", parametros, conexao);
		 
    }
    
    
    public void limpar() {
    	String nome = "/temporarios/OrcamentoTemp.pdf";  
        File f = new File(nome);  
        f.delete();
    }
 
public void imprimirLista(){
    	 
	HashMap parametros = new HashMap();
	//parametros.put("COD_ORCAMENTO",nnn);
	Connection conexao = HibernateUtil.getConexao();
	UtilRelatorios.imprimirRelatorio("orcamentosLista", parametros, conexao);
    	 
    }
    public void identificaOrcamento(){
    	numero=orcamentoDao.ultimoOrcamento(nroReg);
    	System.out.println(numero);
    }
    
    
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	 
	public List<ServicosOrcamento> getServicosOrcamento() {
		return servicosOrcamento;
	}
	public void setServicosOrcamento(List<ServicosOrcamento> servicosOrcamento) {
		this.servicosOrcamento = servicosOrcamento;
	}

	public List<ServicosOrcamento> getProdutosOrcamento() {
		return produtosOrcamento;
	}

	public void setProdutosOrcamento(List<ServicosOrcamento> produtosOrcamento) {
		this.produtosOrcamento = produtosOrcamento;
	}

	 

	public Orcamento getOrcamento() {
		return orcamento;
	}
    
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}



	public List<Cliente> getClientes() {
		return clientes;
	}



	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public PieChartModel getPizza() {
		return pizza;
	}

	public void setPizza(PieChartModel pizza) {
		this.pizza = pizza;
	}

	public List<Orcamento> getOrcamentos() {
		return orcamentos;
	}

	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}

	public OrcamentoDao getOrcamentoDao() {
		return orcamentoDao;
	}

	public void setOrcamentoDao(OrcamentoDao orcamentoDao) {
		this.orcamentoDao = orcamentoDao;
	}

	public Long getTotalG() {
		return totalG;
	}

	public void setTotalG(Long totalG) {
		this.totalG = totalG;
	}

	public ServicosOrcamentosDao getServorcaDao() {
		return servorcaDao;
	}

	public void setServorcaDao(ServicosOrcamentosDao servorcaDao) {
		this.servorcaDao = servorcaDao;
	}

	public List<ServicosOrcamento> getServicosOrcamentoPizza() {
		return servicosOrcamentoPizza;
	}

	public void setServicosOrcamentoPizza(
			List<ServicosOrcamento> servicosOrcamentoPizza) {
		this.servicosOrcamentoPizza = servicosOrcamentoPizza;
	}

	public Long getProdutoOrca() {
		return produtoOrca;
	}

	public void setProdutoOrca(Long produtoOrca) {
		this.produtoOrca = produtoOrca;
	}
 
	public SituacaoOrcamento [] getSituacaoOrcamentos(){
		return SituacaoOrcamento.values();
	}

	public BigDecimal getDescon() {
		return descon;
	}

	public void setDescon(BigDecimal descon) {
		this.descon = descon;
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}
 

	public Long getNroReg() {
		return nroReg;
	}

	public void setNroReg(Long nroReg) {
		this.nroReg = nroReg;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public Cliente getTipoReg() {
		return tipoReg;
	}

	public void setTipoReg(Cliente tipoReg) {
		this.tipoReg = tipoReg;
	}

	public FuncionarioDao getFuncionarioDao() {
		return funcionarioDao;
	}

	public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
	}

	public Long getCodigoOrcamento() {
		return codigoOrcamento;
	}

	public void setCodigoOrcamento(Long codigoOrcamento) {
		this.codigoOrcamento = codigoOrcamento;
	}

	public List<Orcamento> getListadeOrcamentos() {
		return listadeOrcamentos;
	}

	public void setListadeOrcamentos(List<Orcamento> listadeOrcamentos) {
		this.listadeOrcamentos = listadeOrcamentos;
	}

	 
	
	
}
