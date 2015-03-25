/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Escola;
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
public class EscolaDAO implements Serializable{

    public EscolaDAO() {
    }
    
      
    
    public int addEscola(Escola escola){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.persist(escola);
        return escola.getId();
    }
    
    public void setEscola(Escola escola){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        em.merge(escola);
        
    }
    
    public void delEscola(Escola escola){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        Escola esc = em.merge(escola);
        em.remove(esc);
    }
    
    public Escola getEscola(int id) {
        return JPAUtil.getInstance().getEntity(Escola.class, id);
    }
    
    public List<Escola> getEscolas(){
        return JPAUtil.getInstance().getList(Escola.class,"select esc from Escola esc ORDER BY esc.nomeEscola");
    }
    
    public List<Escola> getEscolaByMunicipio(int id){
        return JPAUtil.getInstance().getEscolasByMun(Escola.class,"select esc from Escola esc WHERE esc.municipio.id =:id", id);
    }
}
