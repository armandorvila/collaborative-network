package com.armandorv.cnpd.web.server.mapper;

/**
 * Interface for map objects. 
 * @author armandorv
 *
 * @param <T> type of object to map.
 * @param <K> type of mapped object.
 */
public interface Mapper<T, K>
{

   /**
    * Map an object of type T to another of type K. 
    * @param object object to map.
    * @return mapped object of type K.
    */
   K map(T object);
}
