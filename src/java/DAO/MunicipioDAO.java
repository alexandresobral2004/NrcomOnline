/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Municipio;
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
public class MunicipioDAO implements Serializable{

    public MunicipioDAO() {
    }
    
   
    public int add(Municipio mun){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        em.persist(mun);
        return mun.getId();
    }
    
    public void set(Municipio mun){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        em.merge(mun);
    }
    
    public void del(Municipio mun){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        Municipio mun2 = em.merge(mun);
        em.remove(mun2);
    }
    
    public Municipio getMunicipio(int id){
      return JPAUtil.getInstance().getEntity(Municipio.class, id);
        
    }
    
    public List<Municipio> getAllMunicipios(){
        return JPAUtil.getInstance().getList(Municipio.class,"select mun from Municipio mun");
        
    }
}
