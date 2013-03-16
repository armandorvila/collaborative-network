package com.armandorv.cnpd.client.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jdesktop.swingx.JXFrame;

/**
 * Frame of the application.
 * 
 * @author armandorv
 *
 */
public class BaseFrame extends JXFrame
{
   private static final long serialVersionUID = 1449805199962516234L;

   public BaseFrame()
   {
      super();
      this.initialize();
   }

   public BaseFrame(String string)
   {
      super(string);
      this.initialize();
   }

   private void initialize()
   {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setPreferredSize(new Dimension(600, 440));
      this.pack();
      this.setLocationRelativeTo(null);
      this.setResizable(false);
      this.setVisible(true);
   }
}