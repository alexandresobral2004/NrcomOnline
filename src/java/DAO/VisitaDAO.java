/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Visita;
import Util.DatabaseUtil;
import Util.JPAUtil;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;


/**
 *
 * @author alexandrerocha
 */
public class VisitaDAO extends DatabaseUtil implements Serializable{
    
     

    public VisitaDAO() {
    }
    
    
    public int add(Visita vis){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        em.persist(vis);
        return vis.getId();
        
    }
    
    public int set(Visita vis){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        em.merge(vis);
        return vis.getId();
    }
    
    public int del(Visita vis){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        Visita vis2 = em.merge(vis);
        em.remove(vis2);
        return vis2.getId();
    }
    
    public Visita getVisita(int id){
        return JPAUtil.getInstance().getEntity(Visita.class,id);
    }
    
    public List<Visita> getVisitas(){
        return JPAUtil.getInstance().getList(Visita.class,"select vis from Visita vis ORDER BY vis.data_visita DESC");
    }
    
 /*   public List<Visita> getVisitasporData(Date data_inicial, Date data_final){
        
        
        return JPAUtil.getInstance().getVisitasporDatas(Visita.class,"SELECT vis " + "FROM Visita vis " + "WHERE vis.data_visita BETWEEN :data_inicial AND :data_final",data_inicial,data_final);
        
    }*/
    
    ResultSet rs  =null;
 public List<Visita> getRelatorioporData(java.sql.Date data_inicial, java.sql.Date  data_final){
 
         return JPAUtil.getInstance().getRelatorioporDatas(Visita.class,"SELECT  vis FROM Visita vis WHERE vis.data_visita BETWEEN :data_inicial AND :data_final",data_inicial,data_final);
       
        
        
    }
    
     public List<Visita> getRelatorioporID(int id){
 
         return JPAUtil.getInstance().getRelatorioporID(Visita.class,"SELECT vis  FROM Visita vis   WHERE vis.id=:id ",id);
       //vis.serie AS serie,vis.turno as turno,vis.turma AS turma,vis.nomeCoord AS nomeCoord,vis.nomeProfes AS nomeProfes,vis.numPresentes AS numPresentes,vis.numFaltosos AS numFaltosos,vis.justificativa AS justificativa,vis.tecnicoCrede AS tecnicoCrede,vis.planoAula AS planoAula,vis.rotina AS rotina,vis.aspectos AS aspectos,vis.intervencao AS intervencao,
    }
     
    
     
     
   

}
