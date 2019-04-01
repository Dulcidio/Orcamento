/**
 * @author Dulcidio Sobrinho
 * 29/01/2019
 */
package br.com.loftsistemas.controlegeral.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;



import br.com.loftsistemas.controlegeral.dominio.Registro;
import br.com.loftsistemas.controlegeral.util.HibernateUtil;

/**
 * @author Dulcidio Sobrinho
 *
 */
public class RegistroDao extends GenericDAO<Registro>{
	
	/*Metodo para ver identificar o número */ 
	public Long abreReg(Long numero){
		Session sessao=HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Registro.class);
			numero =  (Long) consulta.setProjection(Projections.max("codigo")).uniqueResult();
			if(numero!=null){
			// System.out.println("o codigo não nulo é: "+numero); 
		    return numero;
			}else{
				//System.out.println("o nulo é"+numero); 
			return numero=0L;
			}
		}catch (RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}
	}

}
