/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.VisitaDAO;
import MODEL.Visita;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author alexandrerocha
 */
public class VisitaConverter implements Converter{

    private VisitaDAO visDAO = new VisitaDAO();
    private Visita vis = null;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
      Integer id = Integer.valueOf(value);
      vis = visDAO.getVisita(id);
      return vis;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       vis = (Visita)value;
       if(vis!= null){
       return String.valueOf(vis.getId());
       }
       return null;
    }
    
}
