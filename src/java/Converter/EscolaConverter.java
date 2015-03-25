/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.EscolaDAO;
import MODEL.Escola;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author alexandrerocha
 */
public class EscolaConverter implements Converter{
    private EscolaDAO escDAO = new EscolaDAO();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Escola esc = new Escola();
        Integer id = Integer.valueOf(value);
        try
        {
            esc = escDAO.getEscola(id);
        }
        catch(Exception e){
        e.printStackTrace();
    }
        return esc;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       Escola esc = (Escola)value;
       if(!value.equals("")){
           return String.valueOf(esc.getId());
       }
          
        return null;
    }
    
}
