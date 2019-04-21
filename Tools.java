import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.util.*;
import java.applet.*;
import java.net.*;
import javax.swing.ImageIcon;
import javax.imageio.*;
import java.io.IOException;

class Tools
{
   int resizeX, resizeY;
   public Ingredients[] items;
   public Ingredients product;
   int x, y;
   int customX, customY;
   int nextItem;
   boolean onFire = false;
   boolean putOut = false;
   boolean toolTake = true;
   String type;
   cookingProcess process;
   ImageIcon image;
   ImageIcon stockPot = new ImageIcon(getClass().getResource("Tools//StockPot2.png"));
   ImageIcon cookingPan = new ImageIcon(getClass().getResource("Tools//CookingPan.png"));
   ImageIcon oven = new ImageIcon(getClass().getResource("Tools//Oven.png"));
   ImageIcon plate = new ImageIcon(getClass().getResource("Tools//Plate.png"));
   ImageIcon choppingBoard = new ImageIcon(getClass().getResource("Tools//ChoppingBoard.png"));
   ImageIcon fireExt = new ImageIcon(getClass().getResource("Tools//fireExt.png"));
   public boolean startTimer = false;
   
   
   public Tools(String type, int Cx, int Cy)
   {
      resizeX = 100;
      resizeY = 100;
      customX = 0;
      customY = 0;
      switch (type)
      {
         case "Cooking Pan":
            items = new Ingredients[1];
            image = cookingPan;
            resizeX = 120;
            resizeY = 120;
            customY = 26;
            break;
         case "Chopping Board":
            items = new Ingredients[1];
            image = choppingBoard;
            break;
         case "Stock Pot":
            items = new Ingredients[4];
            image = stockPot;
            customX = 9;
            customY = 9;
            break;
         case "Oven":
            items = new Ingredients[4];
            image = oven;
            break;
         case "Plate":
            items = new Ingredients[5];
            image = plate;
            resizeX = 90;
            resizeY = 90;
            break; 
         case "Fire Extinguisher":
            items = new Ingredients[1];
            image = fireExt;
            resizeX = 100;
            resizeY = 100;
            break;
               
      }
      x = Cx;
      y = Cy;
      this.type = type;
      nextItem = 0;
      process = new cookingProcess(items, this.x, this.y, product);
   }
   
   public void addItem(Ingredients item)
   {
      this.items[nextItem++] = item;
   }
   
   public void removeItem(Ingredients item)
   {
      this.items[--nextItem] = null;
   }
   
   public void performAction(int i)
   {
      switch (this.type)
      {
         case "Cooking Pan":
            if (i == 0 || i == 9)
            {
               process.frying(); 
            } 
            product = process.current;
            break;
         case "Chopping Board":
            process.chopping();
            product = process.current;
            break;
         case "Stock Pot":
            if (i == 0 || i == 9)
               process.boiling();
            product = process.current;
            break;
         case "Oven":
            process.baking();
            product = process.current;
            break;
         case "Plate":
            process.plating();
            product = process.current;
            break; 
      }
      if (product != null)
      {
         for (int x = 0; x < this.items.length; x++)
         {
            this.items[x] = null;
         }
         this.nextItem = 0;
      }
   }
}