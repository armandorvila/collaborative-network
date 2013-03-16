package com.armandorv.cnpd.web.shared.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class ResourceInfo
{
   public enum Kind {
      NONE, MEDIA, FILE, DOCUMENT, FOLDER, MARKER , ROOT
   }

   private long id;

   private String name;

   private Kind kind;

   private String url;

   //TODO quitar
   private boolean checked;

   public ResourceInfo()
   {
   }

   public ResourceInfo(long id, String name, Kind kind)
   {
      this.name = name;
      this.id = id;
      this.kind = kind;
   }

   private List<ResourceInfo> childs = new ArrayList<ResourceInfo>();

   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Kind getKind()
   {
      return kind;
   }

   public void setKind(Kind kind)
   {
      this.kind = kind;
   }

   public List<ResourceInfo> getChilds()
   {
      return Collections.unmodifiableList(childs);
   }

   public void setChilds(List<ResourceInfo> childs)
   {
      this.childs = childs;
   }

   public void addChild(ResourceInfo resource)
   {
      this.childs.add(resource);
   }

   public String getUrl()
   {
      return url;
   }

   public void setUrl(String url)
   {
      this.url = url;
   }

   public boolean isChecked()
   {
      return checked;
   }

   public void setChecked(boolean checked)
   {
      this.checked = checked;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (id ^ (id >>> 32));
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      ResourceInfo other = (ResourceInfo) obj;
      if (id != other.id)
         return false;
      return true;
   }

}