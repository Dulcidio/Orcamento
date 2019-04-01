/**
 * @author Dulcidio Sobrinho
 * 20/01/2019
 */
package br.com.loftsistemas.controlegeral.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
 


import org.hibernate.criterion.Restrictions;

import br.com.loftsistemas.controlegeral.dominio.Cliente;
import br.com.loftsistemas.controlegeral.util.HibernateUtil;

/**
 * @author Dulcidio Sobrinho
 *
 */
public class ClienteDao extends GenericDAO<Cliente>{

	private String dataForm;
	private Date data = new Date();
	 
	
	public Long ContarTotal(Long total){
		
		Session sessao=HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Cliente.class);
			 
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
	
	public List<Cliente> listaNiver(){
		 
		Session sessao=HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Cliente.class);
			 
			consulta.add(Restrictions.eq("aniversario",data ));
			 
			List<Cliente> resultado = consulta.list();
			return resultado;
			 
			
		}catch (RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
	}
	 
}
