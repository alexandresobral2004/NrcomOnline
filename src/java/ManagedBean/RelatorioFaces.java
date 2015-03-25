/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author alexandrerocha
 */
public class RelatorioFaces {
    
    private void gerarRelatorioWeb(JRDataSource jrRS, Map<Object, Object> parametros, String arquivo) {
        ServletOutputStream servletOutputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        try {
            servletOutputStream = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), response.getOutputStream(), parametros, jrRS);
           
            response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
            context.renderResponse();
            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   /*  public void gerarRelatorio() throws ClassNotFoundException, SQLException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        //String arquivo = context.getRealPath("WEB-INF/relatorios/rel_por_data.jasper");
        Date dataInicial = new java.sql.Date(selectedDiario.getDataInicial().getTime());
       // Date dataFinal = new java.sql.Date(selectedDiario.getDataFinal().getTime());
      //  int id = selectedDiario.getEscola().getId();
      //DiarioDAO dao = new DiarioDAO();
     JRDataSource jrRS = new JRResultSetDataSource(dao.getRelatorioporData(id,dataInicial, dataFinal));
       String arquivo2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF"+ File.separator + "imagens" + File.separator + "simbolo_crede.jpg");
         
         File logo = new File(arquivo2);   
        System.out.println(logo.isFile());
         
      String arquivo3 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF"+ File.separator + "imagens" + File.separator + "super_log.png");
         
        File super_logo = new File(arquivo3);   
        System.out.println(super_logo.isFile());
       
        Map parameters = new HashMap();   
        parameters.put("logo", logo);   
       parameters.put("super_logo", super_logo);
        gerarRelatorioWeb(jrRS, parameters, arquivo);
    }*/
 
}
