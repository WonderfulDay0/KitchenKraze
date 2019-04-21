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
import java.lang.Math; 

public class Main extends JFrame implements KeyListener
{
   private ImageIcon bg;
   private ImageIcon mainMenu;
   private ImageIcon controls;
   private ImageIcon fire;
   private ImageIcon endScreen;
   private ImageIcon pauseScreen;
   private ImageIcon instructions;
   
   //1 is main menu
   //2 is controls
   //3 is instructions 
   //4 is single 
   //5 is coop
   //6 is pause
   //7 is endscreen
   private int screen = 1;
  
   int diff = 0;
   double difff = 0.0;
   int orderNum = 0;
   int totalPoints = 0;
   int oldPoints = 0;
   int currentKeyPressed = 0;
   
   final double length = 119.0;
   double smallest = 99999;
   
   boolean keyChange = false;
   boolean paused = false;
   boolean playFinal = true;
   
   //array initializations
   public static Sound[] gameSounds = new Sound[11];
   public static TimeKeeper[]fireTimer = new TimeKeeper[15];
   public static Ingredients[] tableArray = new Ingredients[15];
   public static Tools[] tableTools = new Tools[15];
   public static TimeKeeper[]toolTimer = new TimeKeeper[15];
   public Keys[] playerKeys = new Keys[15];  
   TimeKeeper[] orderTimer = new TimeKeeper[4];
   TimeKeeper gameTime = new TimeKeeper(15);
   Ingredients[] finalProduct = new Ingredients[1];
   Order[] currentOrders = new Order[4];
  
   Player player1;
   Player player2;
   TimeKeeper timeDiff;
   TimeKeeper timeDiff2;
   Drawing draw;
   Sound NewOrder21, NewOrder22, NewOrder23;
   //100% not a surprise
   Sound surprise;
   
   
   PaintLoop test;
   
   Font controlFont = new Font("SansSerif", Font.PLAIN, 20);
   Font controlFontBig = new Font("SansSerif", Font.PLAIN, 32);
   Color awe = new Color(0, 255 , 0, 100);
   Color awe2 = new Color(255, 165, 0, 100);
   Color awe3 = new Color(255, 69, 0, 100);
   
   JFrame frame = new JFrame("Kitchen Kraze!");
  
   public Main()
   {
      for(int i = 0; i < 15; i++){
         toolTimer[i] = new TimeKeeper(3);
         fireTimer[i] = new TimeKeeper(5);
      }
      
      surprise = new Sound("Sounds//Surprise.wav");
      gameSounds[0] = new Sound("Sounds//Alert.wav");
      gameSounds[1] = new Sound("Sounds//ClockTick.wav");
      gameSounds[2] = new Sound("Sounds//Conveyor.wav");
      gameSounds[3] = new Sound("Sounds//CookingCompletion.wav");
      gameSounds[4] = new Sound("Sounds//FireBurning.wav");
      gameSounds[5] = new Sound("Sounds//Interaction.wav");
      gameSounds[6] = new Sound("Sounds//NewOrder.wav");
      gameSounds[7] = new Sound("Sounds//NewOrder2.wav");
      NewOrder21 = new Sound("Sounds//NewOrder2.wav");
      NewOrder22 = new Sound("Sounds//NewOrder2.wav");
      NewOrder23 = new Sound("Sounds//NewOrder2.wav");
      gameSounds[8] = new Sound("Sounds//OrderExpired.wav");
      gameSounds[9] = new Sound("Sounds//UseToolCorrectIngredients.wav");
      gameSounds[10] = new Sound("Sounds//WasteProduced.wav");
      
      playerKeys[0] = new Keys(87, 872, 658); 
      playerKeys[1] = new Keys(65, 834, 698); 
      playerKeys[2] = new Keys(83, 876, 698); 
      playerKeys[3] = new Keys(68, 916, 698);
      playerKeys[4] = new Keys(32, 554, 696);
   
      playerKeys[5] = new Keys(38, 872, 510);
      playerKeys[6] = new Keys(37, 834, 550);
      playerKeys[7] = new Keys(40, 876, 550);
      playerKeys[8] = new Keys(39, 916, 550);
      playerKeys[9] = new Keys(10, 554, 550);
      playerKeys[10] = new Keys(80, 126, 882);
      
      mainMenu = new ImageIcon(getClass().getResource("MainMenu.png"));
      controls = new ImageIcon(getClass().getResource("Controls.png"));
      fire = new ImageIcon(getClass().getResource("Fire.png"));
      endScreen = new ImageIcon(getClass().getResource("EndScreen.png"));
      pauseScreen = new ImageIcon(getClass().getResource("PauseScreen.png"));
      instructions = new ImageIcon(getClass().getResource("Instructions.png"));
      
      tableTools[4] = new Tools("Fire Extinguisher", 910, 158); 
      tableTools[10] = new Tools("Plate", 3, 370);
      tableTools[13] = new Tools("Chopping Board", 1200, 380);
      tableTools[14] = new Tools("Chopping Board", 1200, 508);
      tableTools[0] = new Tools("Stock Pot", 399, 161);
      tableTools[8] = new Tools("Stock Pot", 781, 930);
      tableTools[9] = new Tools("Cooking Pan", 902, 904);
      tableTools[1] = new Tools("Cooking Pan", 516, 161);
      tableTools[12] = new Tools("Oven", 20, 630);
      tableTools[6] = new Tools("Oven", 530, 910);
      bg = new ImageIcon(getClass().getResource("map.png"));
      player1 = new Player(400, 400, 1, tableArray, tableTools, finalProduct);
      player2 = new Player(600, 400, 2, tableArray, tableTools, finalProduct);
      player1.start();
      player2.start();
      currentOrders[0] = new Order(0, 0, diff, difff);
      currentOrders[1] = new Order(185, 0, diff, difff);
      currentOrders[2] = new Order(370, 0, diff, difff);
      currentOrders[3] = new Order(555, 0, diff, difff);
      orderTimer[0] = new TimeKeeper(currentOrders[0].orderTime);
      orderTimer[1] = new TimeKeeper(currentOrders[1].orderTime);
      orderTimer[2] = new TimeKeeper(currentOrders[2].orderTime);
      orderTimer[3] = new TimeKeeper(currentOrders[3].orderTime);
      timeDiff = new TimeKeeper(10);
      timeDiff2 = new TimeKeeper(10);
      draw = new Drawing();
      test = new PaintLoop();
      //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      //frame.setUndecorated(true);
      frame.setSize(1300, 1080);
      frame.add(draw);
      frame.setVisible(true);
      frame.addKeyListener(this);
      draw.addMouseListener(new ClickHandler());
      test.start();
   }
  
   class ClickHandler extends MouseAdapter
   {
      public void mouseClicked(MouseEvent e)
      {
         int x = e.getX();
         int y = e.getY();
         
         if (screen == 7)
         {
            System.exit(0);
         }
         
         //check player mouse clicks on main menu
         if (screen == 1)
         {
            if (x >= 339 && x <= 917)
            {
               if (y >= 488 && y <= 589)
               {
                  //reinitialize everything so they can restart game
                  gameTime = new TimeKeeper(210);
                  timeDiff = new TimeKeeper(10);
                  timeDiff2 = new TimeKeeper(10);
                  diff = 0;
                  difff = 0.0;
                  for (int i = 0; i < 4; i++)
                  {
                     currentOrders[i] = new Order(185*i, 0, diff, difff);
                     orderTimer[i] = new TimeKeeper(currentOrders[i].orderTime); 
                  }
                  player2.characterDisabled = true;
                  player1.gamePaused = false;
                  paused = false;
                  orderTimer[0].start();
                  orderTimer[1].start();
                  orderTimer[2].start();
                  orderTimer[3].start();
                  timeDiff.start();
                  timeDiff2.start();
                  gameTime.start();
                  player1 = new Player(400, 400, 1, tableArray, tableTools, finalProduct);
                  player2 = new Player(600, 400, 2, tableArray, tableTools, finalProduct);
                  player1.start();
                  player2.start();
                  screen = 4;
                  tableTools[4] = new Tools("Fire Extinguisher", 910, 158); 
                  tableTools[10] = new Tools("Plate", 3, 370);
                  tableTools[13] = new Tools("Chopping Board", 1200, 380);
                  tableTools[14] = new Tools("Chopping Board", 1200, 508);
                  tableTools[0] = new Tools("Stock Pot", 399, 161);
                  tableTools[8] = new Tools("Stock Pot", 781, 930);
                  tableTools[9] = new Tools("Cooking Pan", 902, 904);
                  tableTools[1] = new Tools("Cooking Pan", 516, 161);
                  tableTools[12] = new Tools("Oven", 20, 630);
                  tableTools[6] = new Tools("Oven", 530, 910);
                  
                  totalPoints = 0;
               }
               if (y >= 590 && y <= 689)
               {
                  gameTime = new TimeKeeper(210);
                  timeDiff = new TimeKeeper(10);
                  timeDiff2 = new TimeKeeper(10);
                  for (int i = 0; i < 4; i++)
                  {
                     orderTimer[i] = new TimeKeeper(currentOrders[i].orderTime); 
                  }
                  player2.characterDisabled = false;
                  player1.gamePaused = false;
                  player2.gamePaused = false;
                  paused = false;
                  orderTimer[0].start();
                  orderTimer[1].start();
                  orderTimer[2].start();
                  orderTimer[3].start();
                  timeDiff.start();
                  timeDiff2.start();
                  gameTime.start();
                  player1 = new Player(400, 400, 1, tableArray, tableTools, finalProduct);
                  player2 = new Player(600, 400, 2, tableArray, tableTools, finalProduct);
                  player1.start();
                  player2.start();
                  screen = 5;
                  tableTools[4] = new Tools("Fire Extinguisher", 910, 158); 
                  tableTools[10] = new Tools("Plate", 3, 370);
                  tableTools[13] = new Tools("Chopping Board", 1200, 380);
                  tableTools[14] = new Tools("Chopping Board", 1200, 508);
                  tableTools[0] = new Tools("Stock Pot", 399, 161);
                  tableTools[8] = new Tools("Stock Pot", 781, 930);
                  tableTools[9] = new Tools("Cooking Pan", 902, 904);
                  tableTools[1] = new Tools("Cooking Pan", 516, 161);
                  tableTools[12] = new Tools("Oven", 20, 630);
                  tableTools[6] = new Tools("Oven", 530, 910);
                  diff = 0;
                  difff = 0.0;
                  totalPoints = 0;
               }
               if (y >= 690 && y <= 789)
                  screen = 2;
               if (y >= 790 && y <= 894)
                  screen = 3;
               if (y >= 895 && y <= 989)
                  System.exit(0);      
            }
         }
          
         //back buttons
         if (screen == 2)
         {
            if (x >= 1090 && x <= 1240)
               if (y >= 925 && y <= 985)
                  screen = 1;
         }
         
         if (screen == 6)
         {
            if (x >= 535 && x <= 740)
               if (y >= 555 && y <= 650)
                  screen = 1;
         }
         
         if (screen == 3)
         {
            if (x >= 570 && x <= 765)
               if (y >= 930 && y <= 1015)
                  screen = 1;
         }
         
         draw.repaint();  
      }
      
      public void mousePressed(MouseEvent e)
      {
         if (screen == 2)
         {
            keyChange = true;
         }
      }
      
      public void mouseReleased(MouseEvent e)
      {
         int x = e.getX();
         int y = e.getY();
         
         if (screen == 2)
         {  
            //assigns key bindings
            if (currentKeyPressed == playerKeys[9].value || currentKeyPressed == playerKeys[4].value || currentKeyPressed == playerKeys[5].value || currentKeyPressed == playerKeys[0].value || currentKeyPressed == playerKeys[6].value || currentKeyPressed == playerKeys[1].value || currentKeyPressed == playerKeys[7].value || currentKeyPressed == playerKeys[2].value || currentKeyPressed == playerKeys[8].value || currentKeyPressed == playerKeys[3].value || currentKeyPressed == playerKeys[10].value)
               currentKeyPressed = 0;
               
            //player 1
            if (y >= 521 && y <= 565)
            { 
               if (x >= 543 && x <= 590)
                  playerKeys[9].value = currentKeyPressed;
               if (x >= 819 && x <= 862)
                  playerKeys[6].value = currentKeyPressed; 
               if (x >= 863 && x <= 906)
                  playerKeys[7].value = currentKeyPressed;
               if (x >= 907 && x <= 947)
                  playerKeys[8].value = currentKeyPressed;
            }
            
            //player 2
            if (y >= 667 && y <= 711)
            {
               if (x >= 543 && x <= 590)
                  playerKeys[4].value = currentKeyPressed;
               if (x >= 819 && x <= 862)
                  playerKeys[1].value = currentKeyPressed; 
               if (x >= 863 && x <= 906)
                  playerKeys[2].value = currentKeyPressed;
               if (x >= 907 && x <= 947)
                  playerKeys[3].value = currentKeyPressed;
            }
            
            //player 1
            if (x >= 863 && x <= 906)
               if (y >= 482 && y <= 520)
                  playerKeys[5].value = currentKeyPressed;
               //player 2   
               else if (y >= 628 && y <= 666)
                  playerKeys[0].value = currentKeyPressed;
              
            if (x >= 110 && x <= 157)  
               if (y >= 851 && y <= 895)
                  playerKeys[10].value = currentKeyPressed;    
         }
      }
   }
   
   public class Drawing extends JComponent 
   {
      public Drawing()
      {
         repaint();
      }
     
      public void paint(Graphics g)
      {
         //if the game timer ends, switch screens and play music :)
         if(gameTime.finished == true){
            screen = 7;
            gameSounds[1].stop();
            if (playFinal){
               System.out.println("played");
               gameSounds[0].play();
               surprise.play();
               playFinal = false; 
               
            }  
         }
         
         //pause screen picture
         if (screen == 6)
            pauseScreen.paintIcon(this, g, 0, 0);
      
         //menu screen picture
         if (screen == 1)
         {
            mainMenu.paintIcon(this, g, 0, 0);
         }
         
         //controls screen picture
         if (screen == 2)
         {
            controls.paintIcon(this, g, 0, 0);
            g.setFont(controlFont);
            g.setColor(Color.RED);
            int x = 40;
            int y = 50;
            
            for (int i = 0; i < 11; i++)
            {
               //print special characters
               if (playerKeys[i].value == 37)
                  g.drawString("Lak", playerKeys[i].x - 8, playerKeys[i].y);
               else if (playerKeys[i].value == 38)
                  g.drawString("Uak", playerKeys[i].x - 5, playerKeys[i].y);  
               else if (playerKeys[i].value == 39)
                  g.drawString("Rak", playerKeys[i].x - 8, playerKeys[i].y);
               else if (playerKeys[i].value == 40)
                  g.drawString("Dak", playerKeys[i].x - 8, playerKeys[i].y);
               else if (playerKeys[i].value == 32)
                  g.drawString("Spc", playerKeys[i].x - 6, playerKeys[i].y);
               else if (playerKeys[i].value == 10)
                  g.drawString("Entr", playerKeys[i].x - 6, playerKeys[i].y);
               else if (playerKeys[i].value == 17)
                  g.drawString("Ctrl", playerKeys[i].x - 6, playerKeys[i].y);
               else if (playerKeys[i].value == 18)
                  g.drawString("Alt", playerKeys[i].x, playerKeys[i].y);
               else if (playerKeys[i].value == 16)
                  g.drawString("Shft", playerKeys[i].x - 6, playerKeys[i].y);
               else if (playerKeys[i].value == 20)
                  g.drawString("CpLk", playerKeys[i].x - 6, playerKeys[i].y);
               else if (playerKeys[i].value == 8)
                  g.drawString("Bspc", playerKeys[i].x - 6, playerKeys[i].y);
               else if (playerKeys[i].value == 27)
                  g.drawString("Esc", playerKeys[i].x, playerKeys[i].y);
               else
                  g.drawString(String.valueOf((char)playerKeys[i].value), playerKeys[i].x, playerKeys[i].y);
            }
         }
         
         //print instructions page
         if (screen == 3)
         {
            instructions.paintIcon(this, g, 0, 0);
         }
         
         if (screen == 4)
         {
            player2.characterDisabled = true;
            screen = 5;
         }
      
         //main game screen
         if (screen == 5)
         {
            bg.paintIcon(this, g, 0, 0);
            g.setFont(controlFont);
            //points cannot go under 0
            if (totalPoints < 0)
               totalPoints = 0;
            g.drawString("Time remaining:  " + (int)(gameTime.seconds / 1000), 1000, 30);
            g.drawString("Total points:     " + totalPoints, 1000, 50);
            
            //begins ticking timer if less than 20 sec left
            if (gameTime.seconds / 1000.0 < 20 && gameTime.seconds / 1000.0 > 19.9)
               gameSounds[1].play();
         
            //checks for conveyor belt submission
            for (int i = 0; i < 4; i++)
            {
               if (finalProduct[0] != null && finalProduct[0].name == currentOrders[i].name)
               {
                  if (orderTimer[i].seconds < smallest)
                     smallest = orderTimer[i].seconds;
                  orderNum = i;
               }
            }
         
            //assigns points for submission
            for (int i = 0; i < 4; i++)
            { 
               if(finalProduct[0] != null)
               {
                  int percent = (int)Math.round((smallest / currentOrders[i].orderTime) * 100);
                  oldPoints = totalPoints;
                  totalPoints += currentOrders[i].checkOrder(finalProduct, percent, i);
                  if (finalProduct[0] == null && oldPoints != totalPoints && totalPoints != oldPoints - 15)
                  {
                     currentOrders[orderNum] = new Order(currentOrders[orderNum].cX, 0, diff, difff);
                     orderTimer[orderNum] = new TimeKeeper(currentOrders[orderNum].orderTime);
                     gameSounds[2].play();
                     orderTimer[orderNum].start();
                  }
               }
            }
         
            //cooking timers and fire timers
            smallest = 99999;
            for(int i = 0; i < 15; i++){
               if(tableTools[i] != null){
                  if(tableTools[i].startTimer == true && !toolTimer[i].started){
                     toolTimer[i].start();
                     toolTimer[i].started = true;
                     tableTools[i].toolTake = false;
                     //not allow character to take items
                     System.out.println(player1.toolUsed);
                     System.out.println(player2.toolUsed);
                     if (player1.toolUsed == i){
                        player1.getProduct = false;
                     }
                     else if (player2.toolUsed == i){
                        player2.getProduct = false;
                     }
                     tableTools[i].startTimer = false;
                     //pause characters if using chopping board
                     if(toolTimer[i].finished == false && (i == 13 || i == 14)){
                        if(player1.toolUsed == i){
                           player1.paused = true;
                        }
                        else if (player2.toolUsed == i){
                           player2.paused = true;
                        }
                     }
                  }
               
                  //unpause character and start fire timer if tool on stove
                  if(toolTimer[i].finished == true && player1.toolUsed == i){
                     player1.paused = false;
                     player1.getProduct = true;
                     player1.toolUsed = -1;
                     gameSounds[3].play();
                     if (i == 12 || i == 6 || i == 0 || i == 9 && tableTools[i].product != null)   
                        fireTimer[i].start();
                  }
                  else if(toolTimer[i].finished == true){
                     player2.paused = false;
                     player2.getProduct = true;
                     player2.toolUsed = -1;
                     gameSounds[3].play();
                     if (i == 12 || i == 6 || i == 0 || i == 9 && tableTools[i].product != null)
                     {
                        System.out.println("test");
                        fireTimer[i].start();
                     }
                  }
                  //reset timer if done
                  if (toolTimer[i].finished)
                  {
                     System.out.println("test");
                     toolTimer[i] = new TimeKeeper(3);
                     tableTools[i].toolTake = true;
                  }
                  //if fire is on, but table is put out, reset fire timer and allow item extraction
                  if(fireTimer[i].finished && tableTools[i].putOut){
                     tableTools[i].onFire = false;
                     tableTools[i].putOut = false;
                     fireTimer[i] = new TimeKeeper(5);
                  }
                  else if(fireTimer[i].finished){
                     tableTools[i].onFire = true;
                     tableTools[i].product = new Ingredients("Food Waste", tableTools[i].x, tableTools[i].y);
                     gameSounds[10].play();
                     
                  }
                  else if(fireTimer[i].finished == false && tableTools[i].product == null){
                     fireTimer[i] = new TimeKeeper(5);
                  }
               }
            }
         
            //image drawing
            for (int i = 0; i < 6; i++)
            {
               if (tableArray[i] != null)
               {
                  ImageIcon temp = new ImageIcon(getScaledImage(tableArray[i].image.getImage(), tableArray[i].resizeX, tableArray[i].resizeY));
                  temp.paintIcon(this, g, tableArray[i].x, tableArray[i].y);
               }
               if (tableTools[i] != null)
               {
                  ImageIcon temp = new ImageIcon(getScaledImage(tableTools[i].image.getImage(), tableTools[i].resizeX, tableTools[i].resizeY));
                  temp.paintIcon(this, g, tableTools[i].x, tableTools[i].y);
               
                  if (tableTools[i].nextItem != 0)
                     for (int s = 0; s < tableTools[i].nextItem; s++)
                     {
                        ImageIcon temp2 = new ImageIcon(getScaledImage(tableTools[i].items[s].image.getImage(), 25, 25));
                        temp2.paintIcon(this, g, tableTools[i].x + 30*s, tableTools[i].y);
                     }
               }
            }
         
          
         
            for (int i = 6; i < 10; i++)
            {
               if (tableArray[i] != null)
               {
                  ImageIcon temp = new ImageIcon(getScaledImage(tableArray[i].image.getImage(), tableArray[i].resizeX, tableArray[i].resizeY));
                  rotateImageIcon(temp, 180).paintIcon(this, g, tableArray[i].x, tableArray[i].y);
               }
               if (tableTools[i] != null)
               {
                  ImageIcon temp = new ImageIcon(getScaledImage(tableTools[i].image.getImage(), tableTools[i].resizeX, tableTools[i].resizeY));
                  rotateImageIcon(temp, 180).paintIcon(this, g, tableTools[i].x, tableTools[i].y);
               
                  if (tableTools[i].nextItem != 0)
                     for (int s = 0; s < tableTools[i].nextItem; s++)
                     {
                        ImageIcon temp2 = new ImageIcon(getScaledImage(tableTools[i].items[s].image.getImage(), 25, 25));
                        rotateImageIcon(temp2, 180).paintIcon(this, g, tableTools[i].x + 30*s, tableTools[i].y + 40);
                     }
               }       
            }    
              
            for (int i = 10; i < 13; i++)
            {
               if (tableArray[i] != null)
               {
                  ImageIcon temp = new ImageIcon(getScaledImage(tableArray[i].image.getImage(), tableArray[i].resizeX, tableArray[i].resizeY));
                  rotateImageIcon(temp, 270).paintIcon(this, g, tableArray[i].x, tableArray[i].y);
               }
               if (tableTools[i] != null)
               {
                  ImageIcon temp = new ImageIcon(getScaledImage(tableTools[i].image.getImage(), tableTools[i].resizeX, tableTools[i].resizeY));
                  rotateImageIcon(temp, 270).paintIcon(this, g, tableTools[i].x, tableTools[i].y);
               
                  if (tableTools[i].nextItem != 0)
                     for (int s = 0; s < tableTools[i].nextItem; s++)
                     {
                        ImageIcon temp2 = new ImageIcon(getScaledImage(tableTools[i].items[s].image.getImage(), 25, 25));
                        rotateImageIcon(temp2, 270).paintIcon(this, g, tableTools[i].x, tableTools[i].y - 10 + 30*s);
                     }
               }
            }   
            
            for (int i = 13; i < 15; i++)
            {
               if (tableArray[i] != null)
               {
                  ImageIcon temp = new ImageIcon(getScaledImage(tableArray[i].image.getImage(), tableArray[i].resizeX, tableArray[i].resizeY));
                  rotateImageIcon(temp, 90).paintIcon(this, g, tableArray[i].x, tableArray[i].y);
               }
               if (tableTools[i] != null)
               {
                  ImageIcon temp = new ImageIcon(getScaledImage(tableTools[i].image.getImage(), tableTools[i].resizeX, tableTools[i].resizeY));
                  rotateImageIcon(temp, 90).paintIcon(this,g, tableTools[i].x, tableTools[i].y);
               }
            }    
            
            for (int i = 0; i < 15; i++)
            {
               if (tableTools[i] != null && tableTools[i].product != null){
                  ImageIcon temp = new ImageIcon(getScaledImage(tableTools[i].product.image.getImage(), tableTools[i].product.resizeX, tableTools[i].product.resizeY));
                  if (i < 6)
                     rotateImageIcon(temp, 0).paintIcon(this, g, tableTools[i].x, tableTools[i].y); 
                  else if (i >= 6 && i < 10)
                     rotateImageIcon(temp, 180).paintIcon(this, g, tableTools[i].x + 10, tableTools[i].y + 20); 
                  else if (i >= 10 && i < 13)
                     rotateImageIcon(temp, 270).paintIcon(this, g, tableTools[i].x, tableTools[i].y); 
                  else
                     rotateImageIcon(temp, 90).paintIcon(this, g, tableTools[i].x, tableTools[i].y); 
               }
            }
         
            draw2(g);
         
            //character printing
            rotateImageIcon(player1.character, player1.dir).paintIcon(this, g, player1.x, player1.y);
            if (!player2.characterDisabled)
               rotateImageIcon(player2.character, player2.dir).paintIcon(this, g, player2.x, player2.y);     
         }
         //end game screen
         if(screen == 7){
            endScreen.paintIcon(this, g, 0, 0);
            g.setFont(controlFontBig);
            g.drawString("Thank you for playing Kitchen Kraze!", 250, 600);
            g.drawString("Your total amount of points gained during this session is", 350, 700); 
            g.drawString(" " + totalPoints, 590, 800);
         }
      
      }
   }
   
   //responsible for printing orders and tool timers
   public void draw2(Graphics g)
   {
      if (screen == 5)
      {
         g.setColor(awe);
         if(orderTimer[0].finished == false){
            g.setColor(orderTimer[0].currentCol);
            if (orderTimer[0].currentCol == awe3 && orderTimer[0].playSound)
            {
               gameSounds[0].play();
               orderTimer[0].playSound = false;
            }
            currentOrders[0].image.paintIcon(this, g, currentOrders[0].cX, currentOrders[0].cY);
            g.fillRect(4 + currentOrders[0].cX, 4, (int)(length - orderTimer[0].deduc), 25);
         }
         else{
            currentOrders[0] = new Order(0, 0, diff, difff);
            orderTimer[0] = new TimeKeeper(currentOrders[0].orderTime);
            orderTimer[0].start();
            gameSounds[7].play();
         }
         if(orderTimer[1].finished == false){  
            g.setColor(orderTimer[1].currentCol);
            if (orderTimer[1].currentCol == awe3 && orderTimer[1].playSound)
            {
               gameSounds[0].play();
               orderTimer[1].playSound = false;
            }     
            currentOrders[1].image.paintIcon(this, g, currentOrders[1].cX, currentOrders[1].cY);
            g.fillRect(4 + currentOrders[1].cX, 4, (int)(length - orderTimer[1].deduc), 25);
         }
         else{
            currentOrders[1] = new Order(185, 0, diff, difff);
            orderTimer[1] = new TimeKeeper(currentOrders[1].orderTime);
            orderTimer[1].start();
            NewOrder21.play();
         }
         if(orderTimer[2].finished == false){
            g.setColor(orderTimer[2].currentCol);
            if (orderTimer[2].currentCol == awe3 && orderTimer[2].playSound)
            {
               gameSounds[0].play();
               orderTimer[2].playSound = false;
            }
            currentOrders[2].image.paintIcon(this, g, currentOrders[2].cX, currentOrders[2].cY);
            g.fillRect(4 + currentOrders[2].cX, 4, (int)(length - orderTimer[2].deduc), 25);
         }
         else{
            currentOrders[2] = new Order(370, 0, diff, difff);
            orderTimer[2] = new TimeKeeper(currentOrders[2].orderTime);
            orderTimer[2].start();
            NewOrder22.play();
         }
         if(orderTimer[3].finished == false){
            g.setColor(orderTimer[3].currentCol);
            if (orderTimer[3].currentCol == awe3 && orderTimer[3].playSound)
            {
               gameSounds[0].play();
               orderTimer[3].playSound = false;
            }
            currentOrders[3].image.paintIcon(this, g, currentOrders[3].cX, currentOrders[3].cY);
            g.fillRect(4 + currentOrders[3].cX, 4, (int)(length - orderTimer[3].deduc), 25);
         }
         else{
            currentOrders[3] = new Order(555, 0, diff, difff);
            orderTimer[3] = new TimeKeeper(currentOrders[3].orderTime);
            orderTimer[3].start();
            NewOrder23.play();
         }
         //tool timers
         for(int i = 0; i < 15; i++){
            if(tableTools[i] != null){
               g.setColor(awe);
               g.fillArc((tableTools[i].x + tableTools[i].customX), tableTools[i].y, 75, 75, 0, (int)toolTimer[i].arcDeduc);
            }
         
            if(tableTools[i] != null && tableTools[i].onFire == true)
            {
               ImageIcon temp = new ImageIcon(getScaledImage(fire.getImage(), 90, 90));
               temp.paintIcon(this, g, tableTools[i].x, tableTools[i].y);
            }
         }
         if(timeDiff.finished == true){
            diff++;
         }
         if(timeDiff2.finished == true){
            if(difff <= 5){
               difff+= 0.5;
            }else{
               difff+= 0.25;
            }
            timeDiff2 = new TimeKeeper(10);
            timeDiff2.start();
         }
      }
   }

   //paint thread to redraw draw
   public class PaintLoop extends Thread
   {
      public void run()
      {
         try
         {
            while (true)
            {
               sleep(1);
               draw.repaint();
            }
         }
         catch (InterruptedException q)
         {
         }
      }
   }
    /*** keyPressed **************************************
   * Purpose: collect all key presses and store them/    *
   *          perform necessary action                   *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void keyPressed(KeyEvent e)
   {
      int key = e.getKeyCode();
      
      if (screen == 2)
      {
         if (keyChange)
         {
            currentKeyPressed = key;
            keyChange = false;
         }
      }
      
      if (screen == 5 || screen == 6)
      {
         if (key == playerKeys[5].value) 
            player1.movements[0] = true;
               
         if (key == playerKeys[8].value) 
            player1.movements[1] = true;
               
         if (key == playerKeys[7].value)
            player1.movements[2] = true;
               
         if (key == playerKeys[6].value) 
            player1.movements[3] = true;
              
         if (key == playerKeys[0].value)
            player2.movements[0] = true;
              
         if (key == playerKeys[3].value)
            player2.movements[1] = true;
               
         if (key == playerKeys[2].value)
            player2.movements[2] = true;
              
         if (key == playerKeys[1].value)
            player2.movements[3] = true;
                           
         if (key == playerKeys[4].value)
            player2.useKeyOn = true;
            
         if (key == playerKeys[9].value) 
            player1.useKeyOn = true;
                       
         if (key == playerKeys[10].value)
         {
            if (paused) {
               screen = 5;
               paused = false;
               if (player1 != null)
                  player1.gamePaused = false;
               if (player2 != null)
                  player2.gamePaused = false;
            } 
            else {
               screen = 6;
               paused = true;
               if (player1 != null)
                  player1.gamePaused = true;
               if (player2 != null)
                  player2.gamePaused = true;
            }
         }
      }
   }
  
   public void keyTyped(KeyEvent e) {}
  
   
   public void keyReleased(KeyEvent e)
   {
      int key = e.getKeyCode();
      
      if (screen == 2)
      {
         
      }
      
      if (screen == 5)
      {
         
         if (key == playerKeys[5].value) 
            player1.movements[0] = false;
               
         if (key == playerKeys[8].value) 
            player1.movements[1] = false;
               
         if (key == playerKeys[7].value)
            player1.movements[2] = false;
               
         if (key == playerKeys[6].value) 
            player1.movements[3] = false;
              
         if (key == playerKeys[0].value)
            player2.movements[0] = false;
              
         if (key == playerKeys[3].value)
            player2.movements[1] = false;
               
         if (key == playerKeys[2].value)
            player2.movements[2] = false;
              
         if (key == playerKeys[1].value)
            player2.movements[3] = false;
                           
         if (key == playerKeys[4].value)
         {
            player2.useKeyOn = false;
            player2.useKeyReleased = true;
         }
               
         if (key == playerKeys[9].value)
         {
            player1.useKeyOn = false;
            player1.useKeyReleased = true;
         }
               
         
      }
    
   }
   
    /*** getScaledImage **********************************
   * Purpose: resize an image to the specifed size       *
   * Parameters: Image srcImg - image to resize          *
   *             int w - x value for resize              *
   *             int h - y value for resize              *
   * Returns: Image - resized image                      *
   ******************************************************/
   private Image getScaledImage(Image srcImg, int w, int h)
   {
      BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = resizedImg.createGraphics();
   
      g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      g2.drawImage(srcImg, 0, 0, w, h, null);
      g2.dispose();
   
      return resizedImg;
   }
 
   /*** rotateImageIcon **********************************
   * Purpose: rotate an image by specified degrees       *
   * Parameters: ImageIcon pic - image to rotate         *
   *             double angle - angle of rotation in deg *
   * Returns: ImageIcon - rotated image                  *
   ******************************************************/
   private static ImageIcon rotateImageIcon(ImageIcon picture, double angle)
   {
      int w = picture.getIconWidth();
      int h = picture.getIconHeight();
      int type = 2;
      BufferedImage image = new BufferedImage(h, w, type);
      Graphics2D g2 = image.createGraphics();
      double x = (h - w) / 2.0;
      double y = (w - h) / 2.0;
      AffineTransform at = AffineTransform.getTranslateInstance(x, y);
      at.rotate(Math.toRadians(angle), w / 2.0, h / 2.0);
      g2.drawImage(picture.getImage(), at, null);
      g2.dispose();
      picture = new ImageIcon(image);
    
      return picture;
   }
   
   //TimeKeeper class - creates timers
   class TimeKeeper extends Thread{
      boolean playSound = true;
      boolean finished = false;
      boolean started = false;
      double deduc = 0;
      int hours=0, minutes=0;
      double seconds = 0;
      double divide = 0;
      double arcDeduc = 0;
      Color currentCol = awe;
      public TimeKeeper(double sec){
         seconds = sec * 1000;
         divide = seconds;
      }
      public void run(){
         try
         {
            while (true){
               sleep(1);
               if(!paused){
                  seconds--;
                  deduc+= length/divide;
                  
                  if((seconds / divide) <= 0.45 && (seconds / divide) >= 0.2){
                     currentCol = awe2;
                  }
                  else if((seconds / divide) <= 0.2){
                     currentCol = awe3;
                  }
                  arcDeduc+= 360.0/divide;
                  if (seconds <= 0 && minutes > 0){
                     seconds = 59;
                     minutes--;
                     if (minutes <= 0 && hours > 0){
                        minutes = 0;
                        hours--;
                     }
                  }
                  if(seconds == 0 && minutes == 0 && hours == 0){
                     finished = true;
                     return; 
                  } 
               } 
            } 
         }
         catch (InterruptedException e)
         {
         }
      }
   }
  

   public static void main(String[] args)
   {
      new Main();
   }
}