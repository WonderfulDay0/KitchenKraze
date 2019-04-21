import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
import java.net.*;

class Ingredients extends Object
{
   public final String type = "Ingredient";
   public int x, y;
   public int resizeX, resizeY;
   public ImageIcon image;
   public ImageIcon tomato = new ImageIcon(getClass().getResource("Ingredients//Good//Tomato.png"));
   public ImageIcon lettuce = new ImageIcon(getClass().getResource("Ingredients//Good//Lettuce.png"));
   public ImageIcon beef = new ImageIcon(getClass().getResource("Ingredients//Good//Beef.png"));
   public ImageIcon pork = new ImageIcon(getClass().getResource("Ingredients//Good//Pork.png"));
   public ImageIcon chicken = new ImageIcon(getClass().getResource("Ingredients//Good//Chicken.png"));
   public ImageIcon bun = new ImageIcon(getClass().getResource("Ingredients//Good//Bun.png"));
   public ImageIcon pizzaDough = new ImageIcon(getClass().getResource("Ingredients//Good//PizzaDough.png"));
   public ImageIcon tortilla = new ImageIcon(getClass().getResource("Ingredients//Good//Tortilla.png"));
   public ImageIcon rice = new ImageIcon(getClass().getResource("Ingredients//Good//Rice2.png"));
   public ImageIcon cheese = new ImageIcon(getClass().getResource("Ingredients//Good//Cheese.png"));
   public ImageIcon choppedLettuce = new ImageIcon(getClass().getResource("Ingredients//Good//ChoppedLettuce.png"));
   public ImageIcon choppedTomato = new ImageIcon(getClass().getResource("Ingredients//Good//ChoppedTomato.png"));
   public ImageIcon cookedBeef = new ImageIcon(getClass().getResource("Ingredients//Good//CookedBeef.png"));
   public ImageIcon cookedPork = new ImageIcon(getClass().getResource("Ingredients//Good//CookedPork.png"));
   public ImageIcon cookedChicken = new ImageIcon(getClass().getResource("Ingredients//Good//CookedChicken.png"));
   public ImageIcon cookedRice = new ImageIcon(getClass().getResource("Ingredients//Good//Rice2Cooked.png"));
   public ImageIcon foodWaste = new ImageIcon(getClass().getResource("Ingredients//Good//FoodWaste.png"));
   public ImageIcon salad = new ImageIcon(getClass().getResource("Ingredients//Good//Salad.png"));
   public ImageIcon tomatoSoup = new ImageIcon(getClass().getResource("Ingredients//Good//TomatoSoup.png"));
   public ImageIcon beefBurger = new ImageIcon(getClass().getResource("Ingredients//Good//BeefBurger.png"));
   public ImageIcon porkBurger = new ImageIcon(getClass().getResource("Ingredients//Good//PorkBurger.png"));
   public ImageIcon chickenBurrito  = new ImageIcon(getClass().getResource("Ingredients//Good//ChickenBurrito.png"));
   public ImageIcon beefBurrito = new ImageIcon(getClass().getResource("Ingredients//Good//BeefBurrito.png"));
   public ImageIcon chickenPizza = new ImageIcon(getClass().getResource("Ingredients//Good//ChickenPizza.png"));
   public ImageIcon porkPizza = new ImageIcon(getClass().getResource("Ingredients//Good//PorkPizza.png"));
   public String name;
   
   public Ingredients()
   {
      x = -9999;
      y = -9999;
      resizeX = 90;
      resizeY = 90;
   }
   
   public String toString()
   {
      return this.name;
   }
   
   public boolean finalProductCheck()
   {
      if (this.name == "Food Waste" || this.name == "Salad" || this.name == "Tomato Soup" || this.name == "Beef Burger" || this.name == "Pork Burger" || this.name == "Chicken Burrito" || this.name == "Beef Burrito" || this.name == "Chicken Pizza" || this.name == "Pork Pizza")
         return true;
      else
         return false;
         
   }
   
   public Ingredients(String input, int cX, int cY)
   {
      
      switch(input)
      {
         case "Tomato":
            name = "Tomato";
            image = tomato;
            resizeX = 93;
            resizeY = 93;
            break;
         case "Lettuce":
            name = "Lettuce";
            image = lettuce;
            resizeX = 100;
            resizeY = 92;
            break;
         case "Beef":
            name = "Beef";
            image = beef;
            resizeX = 90;
            resizeY = 90;
            break;
         case "Pork":
            name = "Pork";
            image = pork;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Chicken":
            name = "Chicken";
            image = chicken;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Bun":
            name = "Bun";
            image = bun;
            resizeX = 95;
            resizeY = 90;
            break;
         case "Pizza Dough":
            name = "Pizza Dough";
            image = pizzaDough;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Tortilla":
            name = "Tortilla";
            image = tortilla;
            resizeX = 105;
            resizeY = 105;
            break;
         case "Rice":
            name = "Rice";
            image = rice;
            resizeX = 86;
            resizeY = 86;
            break;
         case "Cheese":
            name = "Cheese";
            image = cheese;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Chopped Lettuce":
            name = "Chopped Lettuce";
            image = choppedLettuce;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Chopped Tomato":
            name = "Chopped Tomato";
            image = choppedTomato;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Cooked Beef":
            name = "Cooked Beef";
            image = cookedBeef;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Cooked Pork":
            name = "Cooked Pork";
            image = cookedPork;
            resizeX = 90;
            resizeY = 90;
            break;
         case "Cooked Chicken":
            name = "Cooked Chicken";
            image = cookedChicken;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Cooked Rice":
            name = "Cooked Rice";
            image = cookedRice;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Food Waste":
            name = "Food Waste";
            image = foodWaste;
            resizeX = 90;
            resizeY = 90;
            break;
         case "Salad":
            name = "Salad";
            image = salad;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Tomato Soup":
            name = "Tomato Soup";
            image = tomatoSoup;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Beef Burger":
            name = "Beef Burger";
            image = beefBurger;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Pork Burger":
            name = "Pork Burger";
            image = porkBurger;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Chicken Burrito":
            name = "Chicken Burrito";
            image = chickenBurrito;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Beef Burrito":
            name = "Beef Burrito";
            image = beefBurrito;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Chicken Pizza":
            name = "Chicken Pizza";
            image = chickenPizza;
            resizeX = 100;
            resizeY = 100;
            break;
         case "Pork Pizza":
            name = "Pork Pizza";
            image = porkPizza;
            resizeX = 100;
            resizeY = 100;
            break;
         default:
            break;
      }
      x = cX;
      y = cY;
   }
   
   public void updateCoordinates(int x, int y)
   {
      this.x = x;
      this.y = y;
   }
}