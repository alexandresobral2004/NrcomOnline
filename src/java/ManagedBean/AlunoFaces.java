/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import DAO.AlunoDAO;
import MODEL.Aluno;
import MODEL.Visita;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author alexandrerocha
 */
@ManagedBean
@SessionScoped
public class AlunoFaces implements Serializable{
    
    private Aluno selectedAluno;
    private Visita selectedVisita;
    private List<Aluno> alunos;
    private AlunoDAO aluDAO = new AlunoDAO();
    //private Visita selectedVisita  = new Visita();

    public Visita getSelectedVisita() {
        return selectedVisita;
    }

    public void setSelectedVisita(Visita selectedVisita) {
        this.selectedVisita = selectedVisita;
    }

   
    public Aluno getSelectedAluno() {
        return selectedAluno;
    }

    public void setSelectedAluno(Aluno selectedAluno) {
        this.selectedAluno = selectedAluno;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    public String startAddAluno(){
        selectedAluno = new Aluno();
        System.out.println("Aluno Criado");
        return "/pages/aluno.jsf";
    }
     public String startAddListaAlunos(){
        selectedAluno = new Aluno();
        selectedVisita = new Visita();
       
        System.out.println("Aluno Criado");
        return "/pages/ListaAlunos.jsf";
    }
    
    public void addAluno(){
        try
        {
          
           aluDAO.addAluno(selectedAluno);
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Adicionando Aluno", "Dados Gravados Com Sucesso!!");
           FacesContext.getCurrentInstance().addMessage("message", message);
           selectedAluno = new Aluno();
           alunos = null;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void delAluno(){
         try
        {
           aluDAO.delAluno(selectedAluno);
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Apagando Aluno", "Dados Apagados Com Sucesso!!");
           FacesContext.getCurrentInstance().addMessage("message", message);
           preencheAlunosPorVisita();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void editAluno(){
         try
        {
           aluDAO.editAluno(selectedAluno);
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Editando Aluno", "Dados Editados Com Sucesso!!");
           FacesContext.getCurrentInstance().addMessage("message", message);
           selectedAluno = new Aluno();
           
           
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String dialogAluno(){
        return "aluno:modalDialog";
    }
    
    
    
      public void reset() {
        RequestContext.getCurrentInstance().reset("aluno:panel4");
    }

    public void handleToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nível de Leitura", "Visibility:" + event.getVisibility());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void preencheAlunosPorVisita(){
        int id = selectedVisita.getId();
        alunos = null;
        alunos = new ArrayList();
        alunos = aluDAO.getAlunosPorVisita(id);
        
    }
    
    
      public void onEdit(RowEditEvent event) {
        
        Aluno aluno = (Aluno)event.getObject();
         this.aluDAO.editAluno(aluno);
         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Editando Aluno","Informação Alterada com Sucesso");
         FacesContext.getCurrentInstance().addMessage("message", message);
    }
    
    public void onCancel(RowEditEvent event) {
        Aluno aluno = (Aluno)event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aluno não Alterado","Nenhuma Alteração Realizada");
         FacesContext.getCurrentInstance().addMessage("message", message);
    }
    
    public String editForm(){
        return "/pages/editAluno.jsf";
    }
    
    public List<Aluno> getAllAlunos(){
        alunos = null;
        alunos = new ArrayList<Aluno>();
        alunos = aluDAO.getAllAlunos();
        return alunos;
    }
    
    
}
