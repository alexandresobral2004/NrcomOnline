/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Usuario;
import Util.JPAUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author alexandrerocha
 */
public class UsuarioDAO implements Serializable{

    public UsuarioDAO() {
    }
    
    
   
    
    public void add(Usuario user){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        em.persist(user);
       
    }
    
    public void set(Usuario user){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        em.merge(user);
    }
    
    public void del(Usuario user){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        Usuario user2 = em.merge(user);
        em.remove(user2);
    }
    
    public Usuario getUsuario(int id){
        return JPAUtil.getInstance().getEntity(Usuario.class, id);
    }
    
    public List<Usuario> getAllUsuarios(){
        return JPAUtil.getInstance().getList(Usuario.class, "select user from Usuario user");
    }
}
