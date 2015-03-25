/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Aluno;
import Util.JPAUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author alexandrerocha
 */
public class AlunoDAO implements Serializable{
    
    public void addAluno(Aluno aluno){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.persist(aluno);
    }
    
    public void editAluno(Aluno aluno){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.merge(aluno);
    }
    public void delAluno(Aluno aluno){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        Aluno a = em.merge(aluno);
        em.remove(a);
    }
    
    public Aluno getAlunoByID(int id){
        return JPAUtil.getInstance().getEntity(Aluno.class, id);
    }
    
    public List<Aluno> getAllAlunos(){
        return JPAUtil.getInstance().getList(Aluno.class, "SELECT a FROM Aluno a");
    }
    
    public List<Aluno> getAlunosPorVisita(int visita_id){
        return JPAUtil.getInstance().getAlunosByVisita(Aluno.class, "SELECT aluno FROM Aluno aluno WHERE aluno.visita.id= :visita_id", visita_id);
    }
    
}//"SELECT esc FROM Escola esc WHERE esc.inep = :inep"
