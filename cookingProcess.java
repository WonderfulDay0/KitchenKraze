import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
import java.net.*;

class cookingProcess
{
   Ingredients current = null;
   Ingredients[] ing; 
   int x, y;
   boolean finalProduct = false;
   
   public cookingProcess(Ingredients[] ing, int x, int y, Ingredients product)
   {
      this.ing = ing;
      this.current = product;
   }
   
   public void chopping()
   {
      if(ing[0].name == "Lettuce")
      {
         current = new Ingredients("Chopped Lettuce", x, y);
      }
      else if(ing[0].name == "Tomato")
      {
         current = new Ingredients("Chopped Tomato", x, y);
      }
      else
      {
         current = new Ingredients("Food Waste", x, y);
      }
   }
   public void frying()
   {
      if(ing[0].name == "Beef")
      {
         current = new Ingredients("Cooked Beef", x, y);
      }
      else if(ing[0].name == "Pork")
      {
         current = new Ingredients("Cooked Pork", x, y);
      }
      else if(ing[0].name == "Chicken")
      {
         current = new Ingredients("Cooked Chicken", x, y);
      }
      else
      {
         current = new Ingredients("Food Waste", x, y);
         
      }   
   }
   public void boiling()
   {
      int tomatoes = 0;
      int rice = 0;
      int waste = 0;
      for(int i = 0; i < ing.length; i++)
      {
         if (ing[i] == null)
            break;
         switch(ing[i].name)
         {
            case "Chopped Tomato":
               tomatoes+= 1;
               break;
            case "Rice":
               rice+= 1;
               break;
            default:
               waste+= 1;
               break;
         }
      }
      if(tomatoes == 4)
      {
         current = new Ingredients("Tomato Soup", x, y);
         finalProduct = true;
      }
      else if(rice == 1)
      {
         current = new Ingredients("Cooked Rice", x, y);
      }
      else
      {
         current = new Ingredients("Food Waste", x, y);
         finalProduct = true;
      }
   }
   public void baking()
   {
      int dough = 0;
      int tomatoes = 0;
      int cheese = 0;
      int pork = 0;
      int chicken = 0;
      int waste = 0;
      
      for(int i = 0; i < ing.length; i++)
      {
         switch(ing[i].name)
         {
            case "Pizza Dough":
               dough+= 1;
               break;
            case "Chopped Tomato":
               tomatoes+= 1;
               break;
            case "Cheese":
               cheese+= 1;
               break;
            case "Chicken":
               chicken+= 1;
               break;
            case "Pork":
               pork+= 1;
               break;
            default:
               waste+= 1;
               break;
         }
      }
      if(dough == 1 && tomatoes == 1 && cheese == 1)
      {
         if(pork == 1)
         {
            current = new Ingredients("Pork Pizza", x, y);
            finalProduct = true;
         }
         else if(chicken == 1)
         {
            current = new Ingredients("Chicken Pizza", x, y);
            finalProduct = true;
         }
         else
         {
            current = new Ingredients("Food Waste", x, y);
            finalProduct = true;
         }
      }
      else
      {
         current = new Ingredients("Food Waste", x, y);
         finalProduct = true;
      }
   } 
   public void plating()
   {
      int bun = 0;
      int tortilla = 0;
      int cheese = 0;
      int lettuce = 0;
      int tomatoes = 0;
      int beef = 0;
      int pork = 0;
      int chicken = 0;
      int rice = 0;
      int waste = 0;
      for(int i = 0; i < ing.length; i++)
      {
         if (ing[i] == null)
            break;
         switch(ing[i].name)
         {
            case "Bun":
               bun+= 1;
               break;
            case "Tortilla":
               tortilla+= 1;
               break;
            case "Cheese":
               cheese+= 1;
               break;
            case "Chopped Lettuce":
               lettuce+= 1;
               break;
            case "Chopped Tomato":
               tomatoes+= 1;
               break;
            case "Cooked Rice":
               rice+= 1;
               break;
            case "Cooked Beef":
               beef+= 1;
               break;
            case "Cooked Chicken":
               chicken+= 1;
               break;
            case "Cooked Pork":
               pork+= 1;
               break;
            default:
               waste+= 1;
               break;
         } 
         
      }    
      
      if(bun == 1 && cheese == 1 && lettuce == 1 && tomatoes == 1)
      {
         if(beef == 1)
         {
            current = new Ingredients("Beef Burger", x, y);
            finalProduct = true;
         }
         else if(pork == 1)
         {
            current = new Ingredients("Pork Burger", x, y);
            finalProduct = true;
         }
         else
         {
            current = new Ingredients("Food Waste", x, y);
            finalProduct = true;
         }
      }
      else if(tortilla == 1 && rice == 1)
      {
         if(chicken == 1)
         {
            current = new Ingredients("Chicken Burrito", x, y);
         }
         else if(beef == 1)
         {
            current = new Ingredients("Beef Burrito", x, y);
         }
         else{
            current = new Ingredients("Food Waste", x, y);
            finalProduct = true;
         }
      }
      else if(lettuce == 1 && tomatoes == 1){
         current = new Ingredients("Salad", x, y);
      }
      else
      {
         current = new Ingredients("Food Waste", x, y);
         finalProduct = true;
      } 
   }    
}