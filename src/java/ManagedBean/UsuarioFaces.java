/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import DAO.UsuarioDAO;
import MODEL.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.RollbackException;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author alexandrerocha
 */
@ManagedBean
@SessionScoped
public class UsuarioFaces implements Serializable{

    public UsuarioFaces() {
    }
    
    public Usuario selectedUsuario;
    public List<Usuario> usuarios;
    private UsuarioDAO userDAO = new UsuarioDAO();
    private List permissoes;
    

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
    
    public String startAddUsuario(){
        selectedUsuario = new Usuario();
        System.out.println("Usuário Criado");
        return "/admin/newUsers.jsf";
    }
    
    public void addUsuario(){
       try
       {
          this.userDAO.add(selectedUsuario);
          selectedUsuario = new Usuario();
          usuarios = null;
          FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuário Gravado com Sucesso!!",null);
          FacesContext.getCurrentInstance().addMessage("message", message);
       }
       catch(RollbackException e){
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao Gravar Usuário!!",null);
            FacesContext.getCurrentInstance().addMessage("message", message);
           e.printStackTrace();
       }
    }
    
    public void delUsuario(){
        try
        {
            this.userDAO.del(selectedUsuario);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuário Apagado com Sucesso",null);
            FacesContext.getCurrentInstance().addMessage("message", message);
        }
        catch(RollbackException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao Apagar Usuário!!",null);
            FacesContext.getCurrentInstance().addMessage("message", message);
            e.printStackTrace();
            
        }
    }
    
    public Usuario getUsuarioByID(){
        return this.userDAO.getUsuario(selectedUsuario.getId());
    }
    
    public List<Usuario> getAllUsuarios(){
        this.usuarios = this.userDAO.getAllUsuarios();
        return usuarios;
    }
    
    public void onEdit(RowEditEvent event){
        Usuario usuario = (Usuario)event.getObject();
        this.userDAO.set(usuario);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuário Alterado com Sucesso",null);
        FacesContext.getCurrentInstance().addMessage("message", message);
        
    }
    
    public void onCancel(RowEditEvent event){
        Usuario usuario = (Usuario)event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuário não Alterado",null);
        FacesContext.getCurrentInstance().addMessage("message", message);
    }
    
    public List getPermissoes(){
       permissoes = null;
       permissoes = new ArrayList();
       permissoes.add("ROLE_ADMIN");
       permissoes.add("ROLE_USER");
       permissoes.add("ROLE_ESCOLA");
               
        return permissoes;
        
    }
    
    
}
