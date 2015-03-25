/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import DAO.AlunoDAO;
import DAO.EscolaDAO;
import DAO.VisitaDAO;
import MODEL.Aluno;
import MODEL.Escola;
import MODEL.Visita;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ToggleEvent;


/**
 *
 * @author alexandrerocha
 */
@ManagedBean
@SessionScoped
public class VisitaFaces implements Serializable{

    private Visita selectedVisita;
    private List<Visita> visitas;
    private List<Escola> Allescolas;
    private VisitaDAO visDAO = new VisitaDAO();
    private EscolaDAO escDAO = new EscolaDAO();
    private AlunoDAO aluDAO = new AlunoDAO();
    //private Object selectedRelatorio;
    private Aluno selectedAluno;
    ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
    private UIForm form;

    public UIForm getForm() {
        return form;
    }

    public void setForm(UIForm form) {
        this.form = form;
    }

    public ArrayList<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(ArrayList<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    public Aluno getSelectedAluno() {
        return selectedAluno;
    }

    public List<Escola> getAllescolas() {
        return Allescolas;
    }
    
    public void setAllescolas(List<Escola> Allescolas) {
        this.Allescolas = Allescolas;
    }

  
    public Visita getSelectedVisita() {
        return selectedVisita;
    }

    public void setSelectedVisita(Visita selectedVisita) {
         this.selectedVisita = selectedVisita;
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
    }
    
    public VisitaFaces() {
    }
    
    
    
     public void handleToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nível de Leitura", "Visibility:" + event.getVisibility());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String startAddVisita(){
        selectedVisita = new Visita();
        selectedAluno = new Aluno();
       // cleanSubmittedValues(form);
        System.out.println("Visita Criada");
        return "/pages/visita.jsf";
    }
    
     public String startAddVisitaRelatorio(){
        selectedVisita = new Visita();
        System.out.println("Visita Relatório Criado");
        return "/relatorio/relatorio.jsf";
    }
    
     public String startAListVisitas(){
        selectedVisita = new Visita();
        visitas = null;
        System.out.println("Lista de Visita Criada");
        return "/pages/listaVisitas.jsf";
    }
     
     public String startAddRelatorio(){
         selectedVisita = new Visita();
         return "/relatorio/relatorio.jsf";
     }
     
     public String editVisita(){
         return "/pages/editVisita.jsf";
     }
    
   /* public void addAluno(){
        selectedVisita.getAluno().add(selectedAluno);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluno Incluído com Sucesso!!"," ");
        FacesContext.getCurrentInstance().addMessage("mensagem", message);
        selectedAluno = new Aluno();
    }*/
     
    public void add() {
            
            visDAO.add(selectedVisita);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados Gravados com Sucesso!!"," ");
            FacesContext.getCurrentInstance().addMessage("mensagem", message);
            selectedVisita = new Visita();
            cleanSubmittedValues(form);
    
    }
    
    public void set() {
        
      
            visDAO.set(selectedVisita);
            FacesMessage message = new FacesMessage(FacesMessage.FACES_MESSAGES, "Visita Alterada com Sucesso");
            FacesContext.getCurrentInstance().addMessage("mensagem", message);
            selectedVisita = new Visita();
             
       
    }
    
    public void del(){
       
        FacesMessage message = new FacesMessage(FacesMessage.FACES_MESSAGES, "Visita Apagada com Sucesso");
        
        FacesContext.getCurrentInstance().addMessage("mensagem", message);
        visDAO.del(selectedVisita);
        selectedVisita = new Visita();
         getAllVisitas();
         
        
    }
    
    public Visita getVisita(){
        Visita visita = null;
      
            visita = visDAO.getVisita(selectedVisita.getId());
       
        
        return visita;
    }
    
    public List<Visita> getAllVisitas(){
        this.visitas = null;
       
            visitas = visDAO.getVisitas();
       
        return visitas;
    }
    
    public List getPlanoAula(){
        ArrayList plano = new ArrayList();
        plano.add("SIM");
        plano.add("Não");
        return plano;
    }
    
     public List getRotinaList(){
        ArrayList plano = new ArrayList();
        plano.add("SIM");
        plano.add("Não");
        return plano;
    }
    
    /**
     *
     * @return
     */
 public void visitasPorDate(){
       try{
           this.visitas = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
       Date data_inicial = new java.sql.Date(selectedVisita.getData_inicial().getTime());
       Date data_final = new java.sql.Date(selectedVisita.getData_final().getTime());
       this.visitas = new ArrayList<Visita>();
        this.visitas = visDAO.getRelatorioporData(data_inicial,data_final);
        
       }
       catch(Exception e){
           e.printStackTrace();
       }
       
        
    }
    
     public void mudaEscola() throws ClassNotFoundException, SQLException {
        System.out.println("Valor do ID " + this.selectedVisita.getMunicipio().getId());
        this.Allescolas = null;
        this.Allescolas = new ArrayList();
        this.Allescolas = this.escDAO.getEscolaByMunicipio(this.selectedVisita.getMunicipio().getId());
    }
     
     public void reset(){
         RequestContext.getCurrentInstance().reset("visitaForm:panel2");
     }
     
     
     //Método para limpar a arvore de componentes
     public void cleanSubmittedValues(UIComponent component) {
		if (component instanceof EditableValueHolder) {
			EditableValueHolder evh = (EditableValueHolder) component;
			evh.setSubmittedValue(null);
			evh.setValue(null);
			evh.setLocalValueSet(false);
			evh.setValid(true);
		}
		if(component.getChildCount()>0){
			for (UIComponent child : component.getChildren()) {
				cleanSubmittedValues(child);
			}
		}
	}
     
      public void onEdit(RowEditEvent event) {
        
         Visita visita = (Visita)event.getObject();
         this.visDAO.set(visita);
         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Visita Editada","Informação Alterada com Sucesso");
         FacesContext.getCurrentInstance().addMessage("message", message);
    }
    
    public void onCancel(RowEditEvent event) {
        Visita visita = (Visita)event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Visita Não Editada","Nenhuma Alteração Realizada");
         FacesContext.getCurrentInstance().addMessage("message", message);
    }
     
     /*
      *  Date data_inicial = new java.sql.Date(selectedVisita.getData_inicial().getTime());
         Date data_final = new java.sql.Date(selectedVisita.getData_final().getTime());  
         *    // JRDataSource jrRS = new JRResultSetDataSource(visDAO.getVisitasporData(data_inicial, data_final));
      */
      public void gerarRelatorio() throws ClassNotFoundException, SQLException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        String arquivo = context.getRealPath("WEB-INF/relatorios/relatorioVisita.jasper");
        String subreport1 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF"+ File.separator + "relatorios" + File.separator+ "relatorioVisita_subreport4.jasper");
        //String subreport2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF"+ File.separator + "relatorios" + File.separator + "relatorioVisita_subreport2.jasper");
        
     //  Collection listas = new ArrayList();
     
        int id = selectedVisita.getId();
        List valor = new ArrayList();
        List listaAlunos = new ArrayList();
    
        listaAlunos = this.aluDAO.getAlunosPorVisita(id);
      
        valor = this.visDAO.getRelatorioporID(id);
       
        
       JRDataSource jrds = new JRBeanCollectionDataSource(valor);
    
      
        String arquivo2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF"+ File.separator + "imagens" + File.separator + "paic.jpg");
         
         File paic = new File(arquivo2);   
        System.out.println(paic.isFile());
         
      String arquivo3 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF"+ File.separator + "imagens" + File.separator + "paic2.jpg");
         
        File paic2 = new File(arquivo3);   
        System.out.println(paic2.isFile());
       
        Map parameters = new HashMap();   
       parameters.put("paic", arquivo2);   
      parameters.put("paic2", arquivo3);
       parameters.put("listaAlunos", listaAlunos);
       
       parameters.put("SUBREPORT_DIR",context.getRealPath("WEB-INF/relatorios/"));
     
        
        gerarRelatorioWeb(jrds, parameters, arquivo);
    }
      
    

    private void gerarRelatorioWeb(JRDataSource jrRS , Map<Object, Object> parametros, String arquivo) {
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
    
    
    
}
