/**
 * @author Dulcidio Sobrinho
 * 24/01/2019
 */
package br.com.loftsistemas.controlegeral.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import br.com.loftsistemas.controlegeral.dominio.Cliente;
import br.com.loftsistemas.controlegeral.dominio.Orcamento;
import br.com.loftsistemas.controlegeral.dominio.Registro;
import br.com.loftsistemas.controlegeral.dominio.ServicosOrcamento;
import br.com.loftsistemas.controlegeral.util.HibernateUtil;

/**
 * @author Dulcidio Sobrinho
 *
 */
public class OrcamentoDao extends GenericDAO<Orcamento>{
	
	private Long nroOrcamento;

	public void salvar(Orcamento orcamento, List<ServicosOrcamento>items){
		Session sessao=HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao =null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(orcamento);
			nroOrcamento=orcamento.getCodigo();
			for(int posicao = 0; posicao < items.size() ; posicao++){
				ServicosOrcamento itenOrca = items.get(posicao);
				itenOrca.setOrcamento(orcamento);
				
				sessao.save(itenOrca);
			}
			
			
			transacao.commit();
	   }catch(RuntimeException erro){
			if(transacao!=null){
				transacao.rollback();
			}
			throw erro;
		}finally{
			sessao.close();
		}
	}
	
	public Long ContarTotal(Long total){
		Session sessao=HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Orcamento.class);
			 
			total = (Long) consulta.setProjection(Projections.max("codigo")).uniqueResult();
			 if(total != 0){
				 System.out.println(total);
				 return total;
			 }else{
					return total=0L;
					}
			 
			
		}catch (RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
	}

	public Long getNroOrcamento() {
		return nroOrcamento;
	}

	public void setNroOrcamento(Long nroOrcamento) {
		this.nroOrcamento = nroOrcamento;
	}
	
	/*Metodo para ver identificar o número */ 
	public Long ultimoOrcamento(Long numero){
		Session sessao=HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Orcamento.class);
			numero =  (Long) consulta.setProjection(Projections.max("codigo")).uniqueResult();
			if(numero!=null){
			 System.out.println("o ultimo registro é: "+numero); 
		    return numero;
			}else{
				System.out.println("não tem nenhum orçamento"+numero); 
			return numero=0L;
			}
		}catch (RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
	}
	
}
