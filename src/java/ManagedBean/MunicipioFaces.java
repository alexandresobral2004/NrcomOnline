/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import DAO.EscolaDAO;
import DAO.MunicipioDAO;
import MODEL.Escola;
import MODEL.Municipio;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author alexandrerocha
 */
@ManagedBean
@SessionScoped
public class MunicipioFaces implements Serializable{
    
    private Municipio selectedMunicipio;
    private List<Municipio> municipios;
        private MunicipioDAO munDAO = new MunicipioDAO();
        
    


    public MunicipioFaces() {
    }

    public Municipio getSelectedMunicipio() {
        return selectedMunicipio;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }
    
    
    
  
    
    
    
    public List<Municipio> getAllMunicipios(){
        if(municipios == null){
             this.municipios = munDAO.getAllMunicipios();
        }
       
        return municipios;
    }
    
    
   
        
    
}
