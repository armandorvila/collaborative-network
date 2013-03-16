package com.armandorv.cnpd.web.client.view.projects.project.resources.util;

import java.util.List;

import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.sencha.gxt.data.shared.TreeStore;

/**
 * Fit a resources in a tree store.
 * 
 * @author armandorv
 * 
 */
public class ResourcesStoreFitter
{
   private TreeStore<ResourceInfo> store;

   private ResourceInfo ROOT;

   public ResourcesStoreFitter(ResourceInfo ROOT, TreeStore<ResourceInfo> store)
   {
      this.store = store;
      this.ROOT = ROOT;
      store.add(ROOT);
   }

   /**
    * Add a list of resources to tree store.
    */
   public void fitResources(List<ResourceInfo> resources)
   {
      for (ResourceInfo base : resources)
      {
         store.add(ROOT, base);

         if (base.getChilds().size() > 0)
         {
            processFolder(store, base);
         }
      }
   }

   private void processFolder(TreeStore<ResourceInfo> store, ResourceInfo folder)
   {

      for (ResourceInfo child : folder.getChilds())
      {
         store.add(folder, child);

         if (child.getChilds().size() > 0)
         {
            processFolder(store, child);
         }
      }
   }
}