import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.applet.*;
import java.net.*;
import javax.swing.ImageIcon;
import javax.imageio.*;
import java.io.IOException;

import java.awt.image.*;

class Player extends Thread
{
   public ImageIcon character;
   public int x, y, dir, playerType;
   boolean[] movements = new boolean[4]; //0 is up, 1 is right, 2 is down, 3 is left
   public boolean useKeyOn = false;
   public boolean handFull = false;
   public boolean useKeyReleased = false;
   public Ingredients itemInHand = null;
   public Tools toolInHand = null;
   public Ingredients[] tables;
   public Tools[] tablesTools;
   public int toolUsed = -1;
   boolean paused = false;
   boolean gamePaused = false;
   boolean characterDisabled = false;
   boolean getProduct = true;
   public int speed;
   public int speed2;
   Ingredients[] finalProduct = new Ingredients[1];
   public static Sound[] gameSounds = new Sound[11];
   ImageIcon character1 = new ImageIcon(getClass().getResource("CharacterModels//Character1-1.png"));
   ImageIcon character2 = new ImageIcon(getClass().getResource("CharacterModels//Character2-1.png"));
   //tools
      //player 1
   ImageIcon character1StockPot = new ImageIcon(getClass().getResource("NewCharacterModels//Character1StockPot.png"));
   ImageIcon character1CookingPan = new ImageIcon(getClass().getResource("NewCharacterModels//Character1CookingPan.png"));    
   ImageIcon character1FireExt = new ImageIcon(getClass().getResource("NewCharacterModels//Character1FireExt.png"));  
      //player 2
   ImageIcon character2StockPot = new ImageIcon(getClass().getResource("NewCharacterModels//Character2StockPot.png")); 
   ImageIcon character2CookingPan = new ImageIcon(getClass().getResource("NewCharacterModels//Character2CookingPan.png"));  
   ImageIcon character2FireExt = new ImageIcon(getClass().getResource("NewCharacterModels//Character2FireExt.png"));  
       
   //ingredients
      //player 2
   ImageIcon character2Lettuce = new ImageIcon(getClass().getResource("NewCharacterModels//Character2Lettuce.png"));
   ImageIcon character2Tomato = new ImageIcon(getClass().getResource("NewCharacterModels//Character2Tomato.png"));
   ImageIcon character2Bun = new ImageIcon(getClass().getResource("NewCharacterModels//Character2Bun.png"));
   ImageIcon character2Beef = new ImageIcon(getClass().getResource("NewCharacterModels//Character2Beef.png"));
   ImageIcon character2Cheese = new ImageIcon(getClass().getResource("NewCharacterModels//Character2Cheese.png"));
   ImageIcon character2Rice = new ImageIcon(getClass().getResource("NewCharacterModels//Character2Rice.png"));
   ImageIcon character2Chicken = new ImageIcon(getClass().getResource("NewCharacterModels//Character2Chicken.png"));
   ImageIcon character2PizzaDough = new ImageIcon(getClass().getResource("NewCharacterModels//Character2PizzaDough.png"));
   ImageIcon character2Pork = new ImageIcon(getClass().getResource("NewCharacterModels//Character2Pork.png"));
   ImageIcon character2Tortilla = new ImageIcon(getClass().getResource("NewCharacterModels//Character2Tortilla.png"));
   ImageIcon character2CookedRice = new ImageIcon(getClass().getResource("NewCharacterModels//Character2CookedRice.png"));  
   ImageIcon character2ChoppedLettuce = new ImageIcon(getClass().getResource("NewCharacterModels//Character2ChoppedLettuce.png"));
   ImageIcon character2ChoppedTomato = new ImageIcon(getClass().getResource("NewCharacterModels//Character2ChoppedTomato.png"));
   ImageIcon character2CookedBeef = new ImageIcon(getClass().getResource("NewCharacterModels//Character2CookedBeef.png"));
   ImageIcon character2CookedPork = new ImageIcon(getClass().getResource("NewCharacterModels//Character2CookedPork.png"));
   ImageIcon character2CookedChicken = new ImageIcon(getClass().getResource("NewCharacterModels//Character2CookedChicken.png"));
      //player 1
   ImageIcon character1Lettuce = new ImageIcon(getClass().getResource("NewCharacterModels//Character1Lettuce.png"));
   ImageIcon character1Tomato = new ImageIcon(getClass().getResource("NewCharacterModels//Character1Tomato.png"));
   ImageIcon character1Bun = new ImageIcon(getClass().getResource("NewCharacterModels//Character1Bun.png"));
   ImageIcon character1Beef = new ImageIcon(getClass().getResource("NewCharacterModels//Character1Beef.png"));
   ImageIcon character1Cheese = new ImageIcon(getClass().getResource("NewCharacterModels//Character1Cheese.png"));
   ImageIcon character1Rice = new ImageIcon(getClass().getResource("NewCharacterModels//Character1Rice.png"));
   ImageIcon character1Chicken = new ImageIcon(getClass().getResource("NewCharacterModels//Character1Chicken.png"));
   ImageIcon character1PizzaDough = new ImageIcon(getClass().getResource("NewCharacterModels//Character1PizzaDough.png"));
   ImageIcon character1Pork = new ImageIcon(getClass().getResource("NewCharacterModels//Character1Pork.png"));
   ImageIcon character1Tortilla = new ImageIcon(getClass().getResource("NewCharacterModels//Character1Tortilla.png"));
   ImageIcon character1CookedRice = new ImageIcon(getClass().getResource("NewCharacterModels//Character1CookedRice.png"));  
   ImageIcon character1ChoppedLettuce = new ImageIcon(getClass().getResource("NewCharacterModels//Character1ChoppedLettuce.png"));
   ImageIcon character1ChoppedTomato = new ImageIcon(getClass().getResource("NewCharacterModels//Character1ChoppedTomato.png"));
   ImageIcon character1CookedBeef = new ImageIcon(getClass().getResource("NewCharacterModels//Character1CookedBeef.png"));
   ImageIcon character1CookedPork = new ImageIcon(getClass().getResource("NewCharacterModels//Character1CookedPork.png"));
   ImageIcon character1CookedChicken = new ImageIcon(getClass().getResource("NewCharacterModels//Character1CookedChicken.png"));   
   
   //complete orders
      //player 1
   ImageIcon character1BeefBurger = new ImageIcon(getClass().getResource("NewCharacterModels//Character1BeefBurger.png"));
   ImageIcon character1Salad = new ImageIcon(getClass().getResource("NewCharacterModels//Character1Salad.png"));
   ImageIcon character1PorkPizza = new ImageIcon(getClass().getResource("NewCharacterModels//Character1PorkPizza.png"));
   ImageIcon character1ChickenPizza = new ImageIcon(getClass().getResource("NewCharacterModels//Character1ChickenPizza.png"));
   ImageIcon character1BeefBurrito = new ImageIcon(getClass().getResource("NewCharacterModels//Character1BeefBurrito.png"));
   ImageIcon character1ChickenBurrito = new ImageIcon(getClass().getResource("NewCharacterModels//Character1ChickenBurrito.png"));
   ImageIcon character1PorkBurger = new ImageIcon(getClass().getResource("NewCharacterModels//Character1PorkBurger.png"));
   ImageIcon character1TomatoSoup = new ImageIcon(getClass().getResource("NewCharacterModels//Character1TomatoSoup.png"));
      //player 2
   ImageIcon character2BeefBurger = new ImageIcon(getClass().getResource("NewCharacterModels//Character2BeefBurger.png"));
   ImageIcon character2Salad = new ImageIcon(getClass().getResource("NewCharacterModels//Character2Salad.png"));
   ImageIcon character2PorkPizza = new ImageIcon(getClass().getResource("NewCharacterModels//Character2PorkPizza.png"));
   ImageIcon character2ChickenPizza = new ImageIcon(getClass().getResource("NewCharacterModels//Character2ChickenPizza.png"));
   ImageIcon character2BeefBurrito = new ImageIcon(getClass().getResource("NewCharacterModels//Character2BeefBurrito.png"));
   ImageIcon character2ChickenBurrito = new ImageIcon(getClass().getResource("NewCharacterModels//Character2ChickenBurrito.png"));
   ImageIcon character2PorkBurger = new ImageIcon(getClass().getResource("NewCharacterModels//Character2PorkBurger.png"));
   ImageIcon character2TomatoSoup = new ImageIcon(getClass().getResource("NewCharacterModels//Character2TomatoSoup.png"));
      
   
   //waste
      //player 1
   ImageIcon character1FoodWaste = new ImageIcon(getClass().getResource("NewCharacterModels//Character1FoodWaste.png"));
      //player 2
   ImageIcon character2FoodWaste = new ImageIcon(getClass().getResource("NewCharacterModels//Character2FoodWaste.png"));

   public void run()
   {
      try
      {
         while (true)
         {
            sleep(12);
            
            //changes speed of characters
            if (this.handFull)
            {
               speed = 3;
               speed2 = 4;
            }
            else
            {
               speed = 4;
               speed2 = 6;
            }
            
            //pauses player threads accordingly
            if(!paused && !gamePaused && !characterDisabled){
            //dir 2 --> top-right
            //direction determination
               if (movements[0] && movements[1])
               {   
               //checks to see if next move is out of bounds                 
                  if (!(this.x + speed > 1054))
                     this.x += speed;
                  if (!(this.y - speed < 188))
                     this.y -= speed;
                  this.dir = 45;   
                
               }  
               //dir 4 --> bottom-right
               else if (movements[1] && movements[2])
               {
                  if (!(this.x + speed > 1054))
                     this.x += speed;
                  if (!(this.y + speed > 800))
                     this.y += speed;
                  this.dir = 135;
               } 
               //dir 6 --> bottom-left
               else if (movements[2] && movements[3])
               {
                  if (!(this.x - speed < 28))  
                     this.x -= speed; 
                  if (!(this.y + speed > 800))
                     this.y += speed;
                  this.dir = 225;
               }   
               //dir 8 --> top-left
               else if (movements[0] && movements[3])
               {
                  if (!(this.x - speed < 28))  
                     this.x -= speed; 
                  if (!(this.y - speed < 188))
                     this.y -= speed;
                  this.dir = 315;
               }
               //dir 1 --> top
               else if (movements[0])
               {
                  if (!(this.y - speed2 < 188))
                     this.y -= speed2;
                  this.dir = 0;   
               }   
               //dir 3 --> right
               else if (movements[1])
               {  
                  if (!(this.x + speed2 > 1054))
                     this.x += speed2;
                  this.dir = 90;
               }   
               //dir 5 --> bottom
               else if (movements[2])
               {    
                  if (!(this.y + speed2 > 800))
                     this.y += speed2;
                  this.dir = 180;      
               }   
               //dir 7 --> left
               else if (movements[3])
               {
                  if (!(this.x - speed2 < 28))  
                     this.x -= speed2;  
                  this.dir = 270;        
               }
            
            
               //determines whether a tool should do its action and start a timer
               for (int i = 0; i < 15; i++)
               {
                  if (tablesTools[i] != null)
                  {
                     if (tablesTools[i].items[0] != null) 
                     {   
                        //very big if, checks all cases 
                            //--->                                                      ____                                                                ____  <-- -->                                    ____                                                               ____  <-   ->                                 ____                                                                                            ____ <-- --> 
                        if (tablesTools[i].product == null && (tablesTools[i].items.length == tablesTools[i].nextItem && (tablesTools[i].type != "Cooking Pan" && tablesTools[i].type != "Stock Pot")) || (tablesTools[i].type == "Plate" && ((tablesTools[i].items[0].name == "Tortilla" && tablesTools[i].nextItem == 3) || (tablesTools[i].nextItem == 2 && (tablesTools[i].items[0].name == "Chopped Lettuce" || tablesTools[i].items[0].name == "Chopped Tomato")))) || (((i == 0 || i == 9) && ((tablesTools[i].type == "Cooking Pan" && tablesTools[i].nextItem == 1) ||(tablesTools[i].type == "Stock Pot") && ((tablesTools[i].nextItem == 4) || (tablesTools[i].items[0].name == "Rice" && tablesTools[i].nextItem == 1))))))
                        {  
                           //tool does action
                           tablesTools[i].performAction(i);
                           gameSounds[9].play();
                           //starts timer if needed
                           if (!tablesTools[i].type.equals("Plate"))
                              tablesTools[i].startTimer = true;
                        }
                     }
                  }
               }
            
            
               //checks all the table hit boxes against all cases of putting item on table
               //taking item from table, putting tool on table, taking tool from table
               //putting ingredient in tool, taking ingredient from tool
               //trash bin, fire extinguisher
               //determines coordinates for everything that is to be drawn in main
  
               if (this.useKeyOn && this.useKeyReleased)
               {
                  this.useKeyReleased = false;
               
               //top tables
                  if (this.dir == 0 && (this.y <= 206 && this.y >= 188))
                  {
                     for (int i = 0; i < 6; i++)
                     {
                        if (this.x >= (320 + 128*i) && this.x <= (370 + 128*i))
                        {
                           System.out.println("Done");
                           if (this.handFull)
                           {
                              if (tablesTools[i] == null && tables[i] == null)
                              {
                                 //checks if the player is trying to put an ingredient on a table
                                 if (this.itemInHand != null) 
                                 {
                                    if (i == 5)
                                    {
                                       this.itemInHand = null;
                                       this.handFull = false;
                                       gameSounds[5].play();
                                    }
                                    else if (this.itemInHand.name != "Food Waste")
                                    {
                                       System.out.println("put");
                                       tables[i] = this.itemInHand;
                                       tables[i].x = (397 + 128*i); 
                                       tables[i].y = 163;
                                       this.handFull = false;
                                       this.itemInHand = null;
                                       gameSounds[5].play();
                                    }
                                 } 
                                 //checks if the player is trying to put a tool on a table
                                 else if (this.toolInHand != null && !(this.toolInHand.type.equals("Fire Extinguisher") && i != 4))
                                 {
                                    tablesTools[i] = this.toolInHand;
                                    tablesTools[i].x = (390 + 128*i) + tablesTools[i].customX;
                                    tablesTools[i].y = 161;
                                    this.toolInHand = null;
                                    this.handFull = false;
                                    gameSounds[5].play();
                                 }
                              }
                              else if (tablesTools[i] != null && this.itemInHand != null)
                              {
                                 if (!this.itemInHand.finalProductCheck())
                                 {
                                    //checks if the tool can accept more ingredients
                                    if (tablesTools[i].items.length > tablesTools[i].nextItem && tablesTools[i].product == null)
                                    {
                                       tablesTools[i].addItem(this.itemInHand);
                                       this.toolUsed = i;
                                       this.itemInHand = null;  
                                       this.handFull = false;
                                       gameSounds[5].play();
                                    }   
                                 } 
                              }
                              else if (tablesTools[i] != null && this.toolInHand.type.equals("Fire Extinguisher") && tablesTools[i].onFire)
                              {
                                 tablesTools[i].onFire = false;
                                 tablesTools[i].putOut = true;
                                 gameSounds[5].play();
                              }
                           }
                           else
                           {
                              if (tablesTools[i] == null && tables[i] != null)
                              {
                                 this.itemInHand = tables[i];
                                 tables[i] = null;
                                 this.handFull = true;
                                 gameSounds[5].play();
                              
                                 //display appropriate character image for INGREDIENTS
                                 if (this.playerType == 1)
                                    this.character = getCharImage(this.itemInHand.name, 1);
                                 else
                                    this.character = getCharImage(this.itemInHand.name, 2);
                              } 
                              
                              else if (this.tablesTools[i] != null)
                              {  
                                 if (tablesTools[i].product != null && this.getProduct && tablesTools[i].toolTake && !tablesTools[i].onFire)
                                 {
                                    this.itemInHand = tablesTools[i].product;
                                    tablesTools[i].product = null;
                                    tablesTools[i].process.current = null;
                                    this.handFull = true; 
                                 
                                    //display appropriate character image for INGREDIENTS
                                    if (this.playerType == 1)
                                       this.character = getCharImage(this.itemInHand.name, 1);
                                    else
                                       this.character = getCharImage(this.itemInHand.name, 2);
                                 }
                                 else if (!tablesTools[i].onFire && tablesTools[i].toolTake && (tablesTools[i].type.equals("Fire Extinguisher") || tablesTools[i].type.equals("Stock Pot") || tablesTools[i].type.equals("Cooking Pan")))  
                                 {
                                    System.out.println("done");
                                    this.toolInHand = tablesTools[i];
                                    tablesTools[i] = null;
                                    gameSounds[5].play();
                                 
                                    //display appropriate character image for TOOLS
                                    if (this.playerType == 1)
                                       this.character = getCharImage(this.toolInHand.type, 1);
                                    else
                                       this.character = getCharImage(this.toolInHand.type, 2); 
                                    this.handFull = true;   
                                 }
                              }
                           }   
                        }
                     }
                  } 
               
               
               //bottom tables
                  if (this.dir == 180 && (this.y <= 800 && this.y >= 782))
                  {
                     for (int i = 1; i < 5; i++)
                     {
                        if (this.x >= (320 + 128*i) && this.x <= (370 + 128*i))
                        {
                           if (this.handFull)
                           {
                              if (tablesTools[i + 5] == null && tables[i + 5] == null)
                              {
                                 //checks if the player is trying to put an ingredient on a table
                                 if (this.itemInHand != null && this.itemInHand.name != "Food Waste")
                                 {
                                    tables[i + 5] = this.itemInHand;
                                    tables[i + 5].x = (397 + 128*i); 
                                    tables[i + 5].y = 929;
                                    this.handFull = false;
                                    this.itemInHand = null;
                                    gameSounds[5].play();
                                 } 
                                 //checks if the player is trying to put a tool on a table
                                 else if (this.toolInHand != null && !(this.toolInHand.type.equals("Fire Extinguisher") && i != 4))
                                 {
                                    tablesTools[i + 5] = this.toolInHand;
                                    tablesTools[i + 5].x = (390 + 128*i) + tablesTools[i + 5].customX;
                                    tablesTools[i + 5].y = 931 - tablesTools[i + 5].customY;
                                    this.toolInHand = null;
                                    this.handFull = false;
                                    gameSounds[5].play();
                                 }
                              }
                              else if (tablesTools[i + 5] != null && this.itemInHand != null)
                              {
                                 if (!this.itemInHand.finalProductCheck())
                                 {
                                    //checks if the tool can accept more ingredients
                                    if (tablesTools[i + 5].items.length > tablesTools[i + 5].nextItem && tablesTools[i + 5].product == null)
                                    {
                                       tablesTools[i + 5].addItem(this.itemInHand);
                                       this.toolUsed = i + 5;
                                       this.itemInHand = null;  
                                       this.handFull = false;
                                    }   
                                 } 
                              }
                              else if (tablesTools[i + 5] != null && this.toolInHand.type.equals("Fire Extinguisher") && tablesTools[i + 5].onFire)
                              {
                                 tablesTools[i + 5].onFire = false;
                                 tablesTools[i + 5].putOut = true;
                                 gameSounds[5].play();
                              }
                           }
                           else
                           {
                              if (tablesTools[i + 5] == null && tables[i + 5] != null)
                              {
                                 this.itemInHand = tables[i + 5];
                                 tables[i + 5] = null;
                                 this.handFull = true;
                                 gameSounds[5].play();
                              
                                 //display appropriate character image for INGREDIENTS
                                 if (this.playerType == 1)
                                    this.character = getCharImage(this.itemInHand.name, 1);
                                 else
                                    this.character = getCharImage(this.itemInHand.name, 2);
                              } 
                              else if (this.tablesTools[i + 5] != null)
                              {
                                 if (tablesTools[i + 5].product != null && this.getProduct && tablesTools[i + 5].toolTake && !tablesTools[i + 5].onFire)
                                 {
                                    this.itemInHand = tablesTools[i + 5].product;
                                    tablesTools[i + 5].product = null;
                                    tablesTools[i + 5].process.current = null;
                                    this.handFull = true; 
                                 
                                    //display appropriate character image for INGREDIENTS
                                    if (this.playerType == 1)
                                       this.character = getCharImage(this.itemInHand.name, 1);
                                    else
                                       this.character = getCharImage(this.itemInHand.name, 2);
                                 }
                                 else if (!tablesTools[i + 5].onFire && tablesTools[i + 5].toolTake && (tablesTools[i + 5].type.equals("Stock Pot") || tablesTools[i + 5].type.equals("Cooking Pan")))  
                                 {
                                    this.toolInHand = tablesTools[i + 5];
                                    tablesTools[i + 5] = null;
                                    gameSounds[5].play();
                                 
                                    //display appropriate character image for TOOLS
                                    if (this.playerType == 1)
                                       this.character = getCharImage(this.toolInHand.type, 1);
                                    else
                                       this.character = getCharImage(this.toolInHand.type, 2); 
                                    this.handFull = true;   
                                 }
                              }
                           }   
                        }
                     }
                  } 
               
               //left tables
                  if (this.dir == 270 && (this.x <= 46 && this.x >= 28))
                  {
                     for (int i = 0; i < 3; i++)
                     {
                        if (this.y >= (288 + 128*i) && this.y <= (338 + 128*i))
                        {
                           if (this.handFull)
                           {
                              if (tablesTools[i + 10] == null && tables[i + 10] == null)
                              {
                                 //checks if the player is trying to put an ingredient on a table
                                 if (this.itemInHand != null && this.itemInHand.name != "Food Waste")
                                 {
                                    tables[i + 10] = this.itemInHand;
                                    tables[i + 10].x = 3; 
                                    tables[i + 10].y = (365 + 128*i);
                                    this.handFull = false;
                                    this.itemInHand = null;
                                    gameSounds[5].play();
                                 } 
                                 //checks if the player is trying to put a tool on a table
                                 else if (this.toolInHand != null && !(this.toolInHand.type.equals("Fire Extinguisher") && i != 4))
                                 {
                                    tablesTools[i + 10] = this.toolInHand;
                                    tablesTools[i + 10].x = 1 + tablesTools[i + 10].customX;
                                    tablesTools[i + 10].y = (375 + 128*i) - tablesTools[i + 10].customY;
                                    this.toolInHand = null;
                                    this.handFull = false;
                                    gameSounds[5].play();
                                 }
                              }
                              else if (tablesTools[i + 10] != null && this.itemInHand != null)
                              {
                                 if (!this.itemInHand.finalProductCheck())
                                 {
                                    //checks if the tool can accept more ingredients
                                    if (tablesTools[i + 10].items.length > tablesTools[i + 10].nextItem && tablesTools[i + 10].product == null)
                                    {
                                       tablesTools[i + 10].addItem(this.itemInHand);
                                       this.toolUsed = i + 10;
                                       this.itemInHand = null;  
                                       this.handFull = false;
                                    }   
                                 } 
                              }
                              else if (tablesTools[i + 10] != null && this.toolInHand.type.equals("Fire Extinguisher") && tablesTools[i + 10].onFire)
                              {
                                 tablesTools[i + 10].onFire = false;
                                 tablesTools[i + 10].putOut = true;
                                 gameSounds[5].play();
                              }
                           }
                           else
                           {
                              if (tablesTools[i + 10] == null && tables[i + 10] != null)
                              {
                                 this.itemInHand = tables[i + 10];
                                 tables[i + 10] = null;
                                 this.handFull = true;
                                 gameSounds[5].play();
                              
                                 //display appropriate character image for INGREDIENTS
                                 if (this.playerType == 1)
                                    this.character = getCharImage(this.itemInHand.name, 1);
                                 else
                                    this.character = getCharImage(this.itemInHand.name, 2);
                              } 
                              else if (this.tablesTools[i + 10] != null)
                              {
                                 if (tablesTools[i + 10].product != null && this.getProduct && tablesTools[i + 10].toolTake && !tablesTools[i + 10].onFire)
                                 {
                                    this.itemInHand = tablesTools[i + 10].product;
                                    tablesTools[i + 10].product = null;
                                    tablesTools[i + 10].process.current = null;
                                    this.handFull = true; 
                                 
                                    //display appropriate character image for INGREDIENTS
                                    if (this.playerType == 1)
                                       this.character = getCharImage(this.itemInHand.name, 1);
                                    else
                                       this.character = getCharImage(this.itemInHand.name, 2);
                                 }
                                 else if (tablesTools[i + 10].toolTake && (tablesTools[i + 10].type.equals("Stock Pot") || tablesTools[i + 10].type.equals("Cooking Pan")))  
                                 {
                                    this.toolInHand = tablesTools[i + 10];
                                    tablesTools[i + 10] = null;
                                    gameSounds[5].play();
                                 
                                    //display appropriate character image for TOOLS
                                    if (this.playerType == 1)
                                       this.character = getCharImage(this.toolInHand.type, 1);
                                    else
                                       this.character = getCharImage(this.toolInHand.type, 2); 
                                    this.handFull = true;   
                                 }
                              }
                           }   
                        }
                     }
                  }
               
               //right tables
                  if (this.dir == 90 && (this.x <= 1054 && this.x >= 1036))
                  {
                     for (int i = 0; i < 2; i++)
                     {
                        if (this.y >= (288 + 128*i) && this.y <= (338 + 128*i))
                        {
                           if (this.handFull)
                           {
                              if (tablesTools[i + 13] == null && tables[i + 13] == null)
                              {
                                 //checks if the player is trying to put an ingredient on a table
                                 if (this.itemInHand != null && this.itemInHand.name != "Food Waste")
                                 {
                                    tables[i + 13] = this.itemInHand;
                                    tables[i + 13].x = 1204; 
                                    tables[i + 13].y = (365 + 128*i);
                                    this.handFull = false;
                                    this.itemInHand = null;
                                    gameSounds[5].play();
                                 } 
                                 //checks if the player is trying to put a tool on a table
                                 else if (this.toolInHand != null && !(this.toolInHand.type.equals("Fire Extinguisher") && i != 4))
                                 {
                                    tablesTools[i + 13] = this.toolInHand;
                                    tablesTools[i + 13].x = 1204 + tablesTools[i + 13].customX;
                                    tablesTools[i + 13].y = (375 + 128*i) - tablesTools[i + 13].customY;
                                    this.toolInHand = null;
                                    this.handFull = false;
                                    gameSounds[5].play();
                                 }
                                 
                              }
                              else if (tablesTools[i + 13] != null && this.itemInHand != null)
                              {
                                 if (!this.itemInHand.finalProductCheck())
                                 {
                                    //checks if the tool can accept more ingredients
                                    if (tablesTools[i + 13].items.length > tablesTools[i + 13].nextItem && tablesTools[i + 13].product == null)
                                    {
                                       tablesTools[i + 13].addItem(this.itemInHand);
                                       this.toolUsed = i + 13;
                                       this.itemInHand = null;  
                                       this.handFull = false;
                                    }   
                                 } 
                              }
                              else if (tablesTools[i + 13] != null && this.toolInHand.type.equals("Fire Extinguisher") && tablesTools[i + 13].onFire)
                              {
                                 tablesTools[i + 13].onFire = false;
                                 tablesTools[i + 13].putOut = true;
                                 gameSounds[5].play();
                              }
                           }
                           else
                           {
                              if (tablesTools[i + 13] == null && tables[i + 13] != null)
                              {
                                 this.itemInHand = tables[i + 13];
                                 tables[i + 13] = null;
                                 this.handFull = true;
                                 gameSounds[5].play();
                              
                                 //display appropriate character image for INGREDIENTS
                                 if (this.playerType == 1)
                                    this.character = getCharImage(this.itemInHand.name, 1);
                                 else
                                    this.character = getCharImage(this.itemInHand.name, 2);
                              } 
                              else if (this.tablesTools[i + 13] != null)
                              {
                                 if (tablesTools[i + 13].product != null && this.getProduct)
                                 {
                                    this.itemInHand = tablesTools[i + 13].product;
                                    tablesTools[i + 13].product = null;
                                    tablesTools[i + 13].process.current = null;
                                    this.handFull = true; 
                                 
                                    //display appropriate character image for INGREDIENTS
                                    if (this.playerType == 1)
                                       this.character = getCharImage(this.itemInHand.name, 1);
                                    else
                                       this.character = getCharImage(this.itemInHand.name, 2);
                                 }
                                 else if (tablesTools[i + 13].toolTake && (tablesTools[i + 13].type.equals("Stock Pot") || tablesTools[i + 13].type.equals("Cooking Pan")))  
                                 {
                                    this.toolInHand = tablesTools[i + 13];
                                    tablesTools[i + 13] = null;
                                    gameSounds[5].play();
                                 
                                    //display appropriate character image for TOOLS
                                    if (this.playerType == 1)
                                       this.character = getCharImage(this.toolInHand.type, 1);
                                    else
                                       this.character = getCharImage(this.toolInHand.type, 2); 
                                    this.handFull = true;   
                                 }
                              }
                           }  
                        }
                     }
                  } 
               }
            
            //reverts image to default
               if (!this.handFull)
                  if (this.playerType == 1) 
                     this.character = character1;
                  else
                     this.character = character2;   
            
            //conveyor belt
               if (this.dir == 90 && (this.x <= 1054 && this.x >= 1036))
                  if (this.y >= 188 && this.y <= 220 && this.useKeyOn && this.handFull && this.toolInHand == null && this.itemInHand.name != "Food Waste")
                  {
                     finalProduct[0] = this.itemInHand; 
                     this.itemInHand = null;
                     this.handFull = false;
                  }
            
            
            //checks to see if user is trying to interact with ingredient box
               if (this.useKeyOn && !this.handFull)
               { 
               //checks top counters
                  if (this.dir == 0 && (this.y <= 206 && this.y >= 188))
                  {
                  //lettuce
                     if (this.x >= 76 && this.x <= 106)
                     {
                        if (this.playerType == 1)
                           this.character = character1Lettuce;
                        else
                           this.character = character2Lettuce;
                        
                        this.itemInHand = new Ingredients("Lettuce", this.x, this.y);
                        this.handFull = true;
                        gameSounds[5].play();
                     }     
                  //tomato   
                     if (this.x >= 204 && this.x <= 234)
                     {
                        if (this.playerType == 1)
                           this.character = character1Tomato;
                        else
                           this.character = character2Tomato;
                        this.itemInHand = new Ingredients("Tomato", this.x, this.y);
                        this.handFull = true; 
                        gameSounds[5].play();
                     }   
                  }
               
               //checks left counters
                  if (this.dir == 270 && (this.x <= 46 && this.x >= 28))
                  {
                  //cheese
                     if (this.y >= 188 && this.y <= 208)
                     {
                        if (this.playerType == 1)
                           this.character = character1Cheese;
                        else
                           this.character = character2Cheese;
                        this.itemInHand = new Ingredients("Cheese", this.x, this.y);
                        this.handFull = true; 
                        gameSounds[5].play();
                     } 
                  //pizza dough
                     if (this.y >= 686 && this.y <= 716)
                     {
                        if (this.playerType == 1)
                           this.character = character1PizzaDough;
                        else
                           this.character = character2PizzaDough;
                        this.itemInHand = new Ingredients("Pizza Dough", this.x, this.y);
                        this.handFull = true; 
                        gameSounds[5].play();
                     } 
                  }
               
               //checks right counters
                  if (this.dir == 90 && (this.x <= 1054 && this.x >= 1036))
                  {
                  //beef
                     if (this.y >= 558 && this.y <= 588)
                     {
                        if (this.playerType == 1)
                           this.character = character1Beef;
                        else
                           this.character = character2Beef;
                        this.itemInHand = new Ingredients("Beef", this.x, this.y);
                        this.handFull = true;
                        gameSounds[5].play();
                     }
                  //chicken
                     if (this.y >= 686 && this.y <= 716)
                     {
                        if (this.playerType == 1)
                           this.character = character1Chicken;
                        else
                           this.character = character2Chicken;
                        this.itemInHand = new Ingredients("Chicken", this.x, this.y);
                        this.handFull = true;
                        gameSounds[5].play();
                     }
                  }
               //checks bottom counters            
                  if (this.dir == 180 && (this.y >= 782 && this.y <= 800))
                  {
                  //bun
                     if (this.x >= 76 && this.x <= 106)
                     {
                        if (this.playerType == 1)
                           this.character = character1Bun;
                        else
                           this.character = character2Bun;
                        
                        this.itemInHand = new Ingredients("Bun", this.x, this.y);
                        this.handFull = true;
                        gameSounds[5].play();
                     }     
                  //rice
                     if (this.x >= 204 && this.x <= 234)
                     {
                        if (this.playerType == 1)
                           this.character = character1Rice;
                        else
                           this.character = character2Rice;
                        this.itemInHand = new Ingredients("Rice", this.x, this.y);
                        this.handFull = true; 
                        gameSounds[5].play();
                     }  
                  //tortilla
                     if (this.x >= 332 && this.x <= 362)
                     {
                        if (this.playerType == 1)
                           this.character = character1Tortilla;
                        else
                           this.character = character2Tortilla;
                        this.itemInHand = new Ingredients("Tortilla", this.x, this.y);
                        this.handFull = true; 
                        gameSounds[5].play();
                     }
                  //pork
                     if (this.x >= 972 && this.x <= 1002)
                     {
                        if (this.playerType == 1)
                           this.character = character1Pork;
                        else
                           this.character = character2Pork;
                        this.itemInHand = new Ingredients("Pork", this.x, this.y);
                        this.handFull = true; 
                        gameSounds[5].play();
                     }
                  }        
               } 
            }
         }
      }
      catch (InterruptedException e)
      {
      }
   }
   
  
   
   public Player(int playerX, int playerY, int playerKind, Ingredients[] table, Tools[] tools, Ingredients[] finalProduct)
   {
      x = playerX;
      y = playerY;
      playerType = playerKind;
      if (playerType == 1)
         character = character1;
      else
         character = character2;
      tables = table;
      tablesTools = tools;
      this.finalProduct = finalProduct;
      gameSounds[0] = new Sound("Sounds//Alert.wav");
      gameSounds[1] = new Sound("Sounds//ClockTick.wav");
      gameSounds[2] = new Sound("Sounds//Conveyor.wav");
      gameSounds[3] = new Sound("Sounds//CookingCompletion.wav");
      gameSounds[4] = new Sound("Sounds//FireBurning.wav");
      gameSounds[5] = new Sound("Sounds//Interaction.wav");
      gameSounds[6] = new Sound("Sounds//NewOrder.wav");
      gameSounds[7] = new Sound("Sounds//NewOrder2.wav");
      gameSounds[8] = new Sound("Sounds//OrderExpired.wav");
      gameSounds[9] = new Sound("Sounds//UseToolCorrectIngredients.wav");
      gameSounds[10] = new Sound("Sounds//WasteProduced.wav");
   }  
   
 
   
   public ImageIcon getCharImage(String name, int type)
   {
      ImageIcon temp = null;
      
      switch (name)
      {
            //tools
         case "Stock Pot":
            if (type == 1)
               temp = character1StockPot;
            else
               temp = character2StockPot;
            break;
         case "Cooking Pan":
            if (type == 1)
               temp = character1CookingPan;
            else
               temp = character2CookingPan;
            break;   
         case "Fire Extinguisher":
            if (type == 1)
               temp = character1FireExt;
            else
               temp = character2FireExt;   
            break;
            //ingredients
         case "Lettuce":
            if (type == 1)
               temp = character1Lettuce;
            else
               temp = character2Lettuce;   
            break;
         case "Tomato":
            if (type == 1)
               temp = character1Tomato;
            else
               temp = character2Tomato;
            break;
         case "Bun":
            if (type == 1)
               temp = character1Bun;
            else 
               temp = character2Bun;  
            break;   
         case "Beef":
            if (type == 1)
               temp = character1Beef;
            else
               temp = character2Beef;
            break;
         case "Cheese":
            if (type == 1)
               temp = character1Cheese;
            else
               temp = character2Cheese;
            break;
         case "Rice":
            if (type == 1)
               temp = character1Rice;
            else
               temp = character2Rice;
            break;
         case "Chicken":
            if (type == 1)
               temp = character1Chicken;
            else
               temp = character2Chicken;
            break;
         case "Pizza Dough":
            if (type == 1)
               temp = character1PizzaDough;
            else
               temp = character2PizzaDough;
            break;  
         case "Pork":
            if (type == 1)
               temp = character1Pork;
            else
               temp = character2Pork;
            break;
         case "Tortilla":
            if (type == 1)
               temp = character1Tortilla;
            else
               temp = character2Tortilla;
            break;
         case "Cooked Rice":
            if (type == 1)
               temp = character1CookedRice;
            else
               temp = character2CookedRice;
            break; 
         case "Chopped Lettuce":
            if (type == 1)
               temp = character1ChoppedLettuce;
            else
               temp = character2ChoppedLettuce;
            break;
         case "Chopped Tomato":
            if (type == 1)
               temp = character1ChoppedTomato;
            else
               temp = character2ChoppedTomato;
            break;
         case "Cooked Beef":
            if (type == 1)
               temp = character1CookedBeef;
            else
               temp = character2CookedBeef;
            break;      
         case "Cooked Pork":
            if (type == 1)
               temp = character1CookedPork;
            else
               temp = character2CookedPork;
            break;   
         case "Cooked Chicken":
            if (type == 1)
               temp = character1CookedChicken;
            else
               temp = character2CookedChicken;
            break;
                     
            //waste         
         case "Food Waste":
            if (type == 1)
               temp = character1FoodWaste;
            else
               temp = character2FoodWaste;
            break;
            
            //complete orders
         case "Salad":
            if (type == 1)
               temp = character1Salad;
            else
               temp = character2Salad;
            break;
         case "Tomato Soup":
            if (type == 1)
               temp = character1TomatoSoup;
            else
               temp = character2TomatoSoup;
            break;
         case "Beef Burger":
            if (type == 1)
               temp = character1BeefBurger;
            else
               temp = character2BeefBurger;
            break;
         case "Pork Burger":
            if (type == 1)
               temp = character1PorkBurger;
            else
               temp = character2PorkBurger;  
            break;
         case "Chicken Burrito":
            if (type == 1)
               temp = character1ChickenBurrito;
            else
               temp = character2ChickenBurrito;
            break;
         case "Beef Burrito":
            if (type == 1)
               temp = character1BeefBurrito;
            else
               temp = character2BeefBurrito;
            break;
         case "Chicken Pizza":
            if (type == 1)
               temp = character1ChickenPizza;
            else
               temp = character2ChickenPizza;
            break;      
         case "Pork Pizza":
            if (type == 1)
               temp = character1PorkPizza;
            else
               temp = character2PorkPizza;
            break;                       
      }
      return temp;
   }

   public boolean checkUseKey()
   {
      return this.useKeyOn;
   }
}