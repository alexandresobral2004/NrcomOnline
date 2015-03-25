/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.EscolaDAO;
import DAO.MunicipioDAO;
import MODEL.Municipio;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author alexandrerocha
 */
public class MunicipioConverter implements Converter{
   MunicipioDAO munDAO = new MunicipioDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
      
      Integer id  = Integer.valueOf(value);
       Municipio mun  = new Municipio();
       try
       {
           mun = munDAO.getMunicipio(id);
       }
       catch(Exception e){
           e.printStackTrace();
       }
       
        return mun;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Municipio mun = (Municipio)value;
        if(mun != null){
            return String.valueOf(mun.getId());
        }
        return null;
        
        
            
        
        
    }
    
}
