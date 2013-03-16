package com.armandorv.cnpd.persistence;

import java.util.List;

import com.armandorv.cnpd.model.Meeting;

/**
 * Interface which encapsulate the access to Meeting entity.
 * 
 * @author armandorv
 * 
 */
public interface IMeetingDao extends IDao<Meeting>
{

   /**
    * Method to obtains meetings which their title match with a given title.
    * 
    * @param title
    *            title to match.
    * @return a list of meetings with given title.
    */
   public List<Meeting> findByTitle(String title);

}
