/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author alexandrerocha
 */
@Entity()
@Table(name="aluno")
public class Aluno implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @ManyToOne
    private Visita visita;
    private String nome;
    private Boolean naoLeitor;
    private Boolean palavra;
    private Boolean frase;
    private Boolean txt_sem_fluencia;
    private Boolean txt_com_fluencia;
    private Boolean soma;
    private Boolean subtrai;
    private Boolean multiplica;
    private Boolean divide;
    private Boolean nao_sabe;

    public Boolean getNao_sabe() {
        return nao_sabe;
    }

    public void setNao_sabe(Boolean nao_sabe) {
        this.nao_sabe = nao_sabe;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

  

  

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getNaoLeitor() {
        return naoLeitor;
    }

    public void setNaoLeitor(Boolean naoLeitor) {
        this.naoLeitor = naoLeitor;
    }

    public Boolean getPalavra() {
        return palavra;
    }

    public void setPalavra(Boolean palavra) {
        this.palavra = palavra;
    }

    public Boolean getFrase() {
        return frase;
    }

    public void setFrase(Boolean frase) {
        this.frase = frase;
    }

    public Boolean getTxt_sem_fluencia() {
        return txt_sem_fluencia;
    }

    public void setTxt_sem_fluencia(Boolean txt_sem_fluencia) {
        this.txt_sem_fluencia = txt_sem_fluencia;
    }

    public Boolean getTxt_com_fluencia() {
        return txt_com_fluencia;
    }

    public void setTxt_com_fluencia(Boolean txt_com_fluencia) {
        this.txt_com_fluencia = txt_com_fluencia;
    }

    public Boolean getSoma() {
        return soma;
    }

    public void setSoma(Boolean soma) {
        this.soma = soma;
    }

 

    public Boolean getSubtrai() {
        return subtrai;
    }

    public void setSubtrai(Boolean subtrai) {
        this.subtrai = subtrai;
    }

    public Boolean getMultiplica() {
        return multiplica;
    }

    public void setMultiplica(Boolean multiplica) {
        this.multiplica = multiplica;
    }

    public Boolean getDivide() {
        return divide;
    }

    public void setDivide(Boolean divide) {
        this.divide = divide;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

  

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno other = (Aluno) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + '}';
    }
    
    
    
}
