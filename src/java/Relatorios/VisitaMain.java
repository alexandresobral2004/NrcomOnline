/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Relatorios;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alexandrerocha
 */
public class VisitaMain {
    
    public static void main(String[] args){
        
    }
    
    public void abrirRelatorioVisita(){
        InputStream inputStream = getClass().getResourceAsStream("WEB-INF/relatorios/rel_por_data.jasper");
        Map parametros = new HashMap();    
        parametros.put("Visita", "F%");
        
      
    
    }
}
