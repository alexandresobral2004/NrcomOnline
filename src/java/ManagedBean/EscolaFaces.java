/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import DAO.EscolaDAO;
import MODEL.Escola;
import com.sun.faces.ext.component.UIFocus;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext; 
import org.primefaces.event.RowEditEvent;


/**
 *
 * @author alexandrerocha
 */
@ManagedBean
@SessionScoped
public class EscolaFaces implements Serializable{
   
    private Escola selectedEscola;
    private List<Escola> escolas;
    private EscolaDAO escDAO = new EscolaDAO();
    private UIForm form;

    public UIForm getForm() {
        return form;
    }

    public void setForm(UIForm form) {
        this.form = form;
    }
  
    public EscolaFaces() {
       
    }
    
    public String startAddEscola(){
        selectedEscola = new Escola();
       
        System.out.println("Nova Escola");
        return "/pages/AddEscola.jsf";
    }

    public Escola getSelectedEscola() {
        return selectedEscola;
    }

    public void setSelectedEscola(Escola selectedEscola) {
        this.selectedEscola = selectedEscola;
    }

    public List<Escola> getEscolas() {
        return escolas;
    }

    public void setEscolas(List<Escola> escolas) {
        this.escolas = escolas;
    }
    
    public void reset(){
     RequestContext.getCurrentInstance().reset("addEscola:panel");
    }
    
    public String addEscola(){
        try
        {
            this.escDAO.addEscola(selectedEscola);
            escolas = null;
              selectedEscola = new Escola();
              cleanSubmittedValues(form);
              FacesMessage message = new FacesMessage("Escola Adicionada Com Sucesso!!");
              FacesContext.getCurrentInstance().addMessage("mensagem", message);
                
        }
        catch(Exception  e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao Adicionar Escola","Escola não gravada!");
              FacesContext.getCurrentInstance().addMessage("mensagem", message);
            e.printStackTrace();
        }
        return "/pages/AddEscola.jsf";
    }
    
    public List getLocalidade(){
        ArrayList local = new ArrayList();
        
        
       local.add("Sede");
       local.add("Distrito");
        
        return local;
        
    }
    public  void delEscola(){
        try
        {
            this.escDAO.delEscola(selectedEscola);
            FacesMessage message = new FacesMessage("Escola Apagada Com Sucesso!!");
              FacesContext.getCurrentInstance().addMessage("mensagem", message);
              escolas = null;
              selectedEscola = new Escola();
        }
        catch(Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao Deletar Escola","Escola não Apagada!");
              FacesContext.getCurrentInstance().addMessage("mensagem", message);
            e.printStackTrace();
        }
        
        
    }
    
    public Escola getEscolaById(){
        int id = selectedEscola.getId();
        Escola esc = escDAO.getEscola(id);
        return esc;
    }
    
    public List<Escola> getAllEscolas(){
        this.escolas = escDAO.getEscolas();
        
        return escolas;
        
    }
    
    
    public void onEdit(RowEditEvent event)
            throws ClassNotFoundException, SQLException {
        Escola esc = (Escola) event.getObject();
        this.escDAO.setEscola(esc);
        FacesMessage msg = new FacesMessage("Escola Editada!!");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event)
            throws ClassNotFoundException, SQLException {
        Escola esc = (Escola) event.getObject();

        FacesMessage msg = new FacesMessage("Escola Não Editada!!");

        FacesContext.getCurrentInstance().addMessage(null, msg);
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
    
}
