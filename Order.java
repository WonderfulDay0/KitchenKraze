import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.applet.*;
import java.net.*;
import javax.swing.ImageIcon;
import javax.imageio.*;
import java.io.IOException;


public class Order{
   public ImageIcon image;
   public ImageIcon PorkPizzaOr = new ImageIcon(getClass().getResource("Orders//Pork Pizza.png"));
   public ImageIcon ChickenPizzaOr = new ImageIcon(getClass().getResource("Orders//Chicken Pizza.png"));
   public ImageIcon BeefBurritoOr = new ImageIcon(getClass().getResource("Orders//Beef Burrito.png"));
   public ImageIcon ChickenBurritoOr = new ImageIcon(getClass().getResource("Orders//Chicken Burrito.png"));
   public ImageIcon BeefBurgerOr = new ImageIcon(getClass().getResource("Orders//Beef Burger.png"));
   public ImageIcon PorkBurgerOr = new ImageIcon(getClass().getResource("Orders//Pork Burger.png"));
   public ImageIcon SaladOr = new ImageIcon(getClass().getResource("Orders//Salad.png"));
   public ImageIcon TomatoSoupOr = new ImageIcon(getClass().getResource("Orders//Tomato Soup.png"));
   
   int cX = 0, cY = 0;
   double orderTime = 0;
   String name;
   int points = 0;
   int random = 0;
   

   
   public Order(int x, int y, int diff, double diffOTime){
      cX = x;
      cY = y;
      String[] order;
      if(diff == 0){
         order = new String[]{"Salad", "Tomato Soup"};
         random = (int)(Math.random() * 2 + 0);
      }else{
         order = new String[]{"Pork Pizza", "Chicken Pizza", "Beef Burrito", "Chicken Burrito", "Beef Burger", "Pork Burger", "Salad", "Salad", "Tomato Soup", "Tomato Soup"};
         random = (int)(Math.random() * 10 + 0);
      }
      
      name = order[random];
      image = new ImageIcon(getClass().getResource("Orders//" + order[random] + ".png"));
      if(order[random].equals("Pork Pizza") || order[random].equals("Chicken Pizza")){
         orderTime = (20.0 - diffOTime);
         this.points = 75;
      }else if
      (order[random].equals("Beef Burrito") || order[random].equals("Chicken Burrito")){
         orderTime = (20.0 - diffOTime);
         this.points = 50;
      }else if
      (order[random].equals("Beef Burger") || order[random].equals("Pork Burger")){
         orderTime = (35.0 - diffOTime);
         this.points = 75;
      }else if
      (order[random].equals("Salad")){
         orderTime = (10.0 - diffOTime);
         this.points = 15;
      }else if
      (order[random].equals("Tomato Soup")){
         orderTime = (10.0 - diffOTime);
         this.points = 25;
      }  
   }
   
   public int checkOrder(Ingredients[] finalProduct, int percent, int i)
   {
      int temp = 0;
   
      if (this.name == finalProduct[0].name)
      {
         if (percent > 55)
            temp = this.points;
         else if (percent > 30)
            temp = (int)(this.points * 0.7); 
         else
            temp = (int)(this.points * 0.5);
         finalProduct[0] = null; 
         return temp;            
      } 
      else{
         if(i == 3){
            finalProduct[0] = null; 
            return -15; 
         }
         return 0;     
      } 
      
   }
}
