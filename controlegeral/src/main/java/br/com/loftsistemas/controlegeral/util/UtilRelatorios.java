package br.com.loftsistemas.controlegeral.util;

import java.sql.Connection;
import java.util.HashMap;
 

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
 

public class UtilRelatorios {
	Connection conexao = HibernateUtil.getConexao();
	public static void imprimirRelatorio( String nomeRelatorio ,HashMap parametros, Connection conexao) {
		
		try {
			//JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(conexao, false);
			 
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext)
					facesContext.getExternalContext().getContext();
			String path=scontext.getRealPath("/reports/");
			JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/reports/")+nomeRelatorio+".jasper", parametros, conexao);
			
			HttpServletResponse res = (HttpServletResponse)facesContext.getExternalContext().getResponse();
			res.setContentType("application/pdf");
			int codigo=(int)(Math.random()*1000);
			res.setHeader("Content-disposition","inline;filename=relatorio_"+codigo+"pdf");
			byte[]b = JasperExportManager.exportReportToPdf(jasperPrint);
			res.getOutputStream().write(b);
			res.getCharacterEncoding();
			facesContext.responseComplete();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	

}
