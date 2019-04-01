/**
 * @author Dulcidio Sobrinho
 * 17/01/2019
 */
package br.com.loftsistemas.controlegeral.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Dulcidio Sobrinho
 *
 */
public class HibernateContexto implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		HibernateUtil.getFabricaDeSessoes().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		HibernateUtil.getFabricaDeSessoes();
		
	}

	
}
