/**
 * @author Dulcidio Sobrinho
 * 26/01/2019
 */
package br.com.loftsistemas.controlegeral.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
 
import br.com.loftsistemas.controlegeral.dominio.ServicosOrcamento;
import br.com.loftsistemas.controlegeral.util.HibernateUtil;

/**
 * @author Dulcidio Sobrinho
 *
 */
public class ServicosOrcamentosDao extends GenericDAO<ServicosOrcamento> {

	public Long ContarTotal(Long total){
		Session sessao=HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(ServicosOrcamento.class);
			 
			total = (Long) consulta.setProjection(Projections.max("codigo")).uniqueResult();
			 if(total != 0){
				 //System.out.println(total);
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
}
