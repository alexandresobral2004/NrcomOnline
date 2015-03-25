/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author alexandrerocha
 */
@Entity()
@Table(name="visita")
public class Visita implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date data_visita;
    @Transient
    @Temporal(TemporalType.DATE)
    private Date Data_inicial;
    @Transient
    @Temporal(TemporalType.DATE)
    private Date Data_final;
     @ManyToOne(fetch= FetchType.EAGER)
    private Escola escola;
    @ManyToOne(fetch= FetchType.EAGER)
    private Municipio municipio;
    @OneToMany(mappedBy="visita",cascade= CascadeType.REMOVE)
    private Collection<Aluno> aluno;

    public Collection<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(Collection<Aluno> aluno) {
        this.aluno = aluno;
    }

    private String serie;
    private String turno;
    private String turma;
    private String nomeCoord;
    private String nomeProfes;
    private Integer numAlunos;
    private Integer numPresentes;
    private Integer numFaltosos;
    @Column(length=310)
    private String justificativa;
    private String tecnicoCrede;
    private String planoAula;
    private String rotina;
    @Column(length=300)
    private String aspectos;
    @Column(length=300)
    private String intervencao;

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getIntervencao() {
        return intervencao;
    }

    public void setIntervencao(String intervencao) {
        this.intervencao = intervencao;
    }

    public String getAspectos() {
        return aspectos;
    }

    public void setAspectos(String aspectos) {
        this.aspectos = aspectos;
    }


   

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getNomeCoord() {
        return nomeCoord;
    }

    public void setNomeCoord(String nomeCoord) {
        this.nomeCoord = nomeCoord;
    }

    public String getNomeProfes() {
        return nomeProfes;
    }

    public void setNomeProfes(String nomeProfes) {
        this.nomeProfes = nomeProfes;
    }

    public Integer getNumAlunos() {
        return numAlunos;
    }

    public void setNumAlunos(Integer numAlunos) {
        this.numAlunos = numAlunos;
    }

    public Integer getNumPresentes() {
        return numPresentes;
    }

    public void setNumPresentes(Integer numPresentes) {
        this.numPresentes = numPresentes;
    }

    public Integer getNumFaltosos() {
        return numFaltosos;
    }

    public void setNumFaltosos(Integer numFaltosos) {
        this.numFaltosos = numFaltosos;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getTecnicoCrede() {
        return tecnicoCrede;
    }

    public void setTecnicoCrede(String tecnicoCrede) {
        this.tecnicoCrede = tecnicoCrede;
    }

    public String getPlanoAula() {
        return planoAula;
    }

    public void setPlanoAula(String planoAula) {
        this.planoAula = planoAula;
    }

    public String getRotina() {
        return rotina;
    }

    public void setRotina(String rotina) {
        this.rotina = rotina;
    }
    
    
    
    public Date getData_inicial() {
        return Data_inicial;
    }

    public void setData_inicial(Date Data_inicial) {
        this.Data_inicial = Data_inicial;
    }

    public Date getData_final() {
        return Data_final;
    }

    public void setData_final(Date Data_final) {
        this.Data_final = Data_final;
    }

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getData_visita() {
      
        return data_visita;
    }

    public void setData_visita(Date data_visita) {
        this.data_visita = data_visita;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

   

   
    
         
    
    
    
    
    
    
}
