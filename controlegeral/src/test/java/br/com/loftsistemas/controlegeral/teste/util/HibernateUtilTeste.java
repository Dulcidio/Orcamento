/**
 * @author Dulcidio Sobrinho
 * 14/01/2019
 */
package br.com.loftsistemas.controlegeral.teste.util;

import org.hibernate.Session;
import org.junit.Test;

import br.com.loftsistemas.controlegeral.util.HibernateUtil;

 

/**
 * @author Dulcidio Sobrinho
 *
 */
public class HibernateUtilTeste {
	
@Test
	
	public void conectar(){
		
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	sessao.close();
	HibernateUtil.getFabricaDeSessoes().close();
}

}
