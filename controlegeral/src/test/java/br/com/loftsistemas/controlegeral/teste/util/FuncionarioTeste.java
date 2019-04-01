/**
 * @author Dulcidio Sobrinho
 * 16/01/2019
 */
package br.com.loftsistemas.controlegeral.teste.util;

import java.util.List;

import org.junit.Test;

import br.com.loftsistemas.controlegeral.dao.FuncionarioDao;
import br.com.loftsistemas.controlegeral.dominio.Funcionario;

/**
 * @author Dulcidio Sobrinho
 *
 */

public class FuncionarioTeste {
	
	@Test
	public void lista(){
		
		FuncionarioDao dao = new FuncionarioDao();
		List<Funcionario>resulta = dao.listar();
		for(Funcionario f:resulta){
			System.out.println(f);
		}
	}

}
