package solitaire;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Card class to store the information of single card
 * @member {Suit} suit The suit of the card (Spades,Hearts,Diamonds,Clubs)
 * @member {Integer} value The value of the card (1->13)
 */
public class Card extends JPanel{
		// Members
		public int value;
		public Suit suit;
		private BufferedImage image;
		private BufferedImage backImage;
		boolean isReversed;
		Point positionOffset;
		public String backImgDef = "./img/back6.bmp";
	
		/**
		 * Enum to store the suit values
		 */
		
		public void changeBackImg(String imgStr) {
			try {
				File imgBack = new File(imgStr);
				backImage = ImageIO.read(imgBack);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		public enum Suit {
			Clubs(1, false),
			Diamonds(2, true),
			Hearts(3, true),
			Spades(4, false);
			
			public int value;
			public boolean isRed;
			
			private Suit(int value, boolean isRed) {
				this.value = value;
				this.isRed = isRed;
			}
		};
		
		/**
		 * Converts the value of the card to a string
		 * @param {Integer} value The value of the card 
		 */
		public static String valueString(int value) {
								
			if(value == 11) return "J";
			if(value == 12) return "Q";
			if(value == 13) return "K";
			if(value == 1) return "A";
			
			// Value between 2 and 10
			return Integer.toString(value);
		}

		/**
		 * Converts the value of the card to a int
		 * @param {String} value The value of the card 
		 */
		public static int valueInt(String value) {
			
			if(value.equals("J")) return 11;
			if(value.equals("Q")) return 12;
			if(value.equals("K")) return 13;
			if(value.equals("A")) return 1;
			
			return Integer.parseInt(value);
		}
		/**
		 * toString method, eg: "K of Diamonds"
		 * @return {String} Description of the current card
		 */
		public String toString() {
			return valueString(value) + " of " + suit.name();
		}
		
		public String getCardID() {
			//System.out.println(suit.name());
			String idValue="0";
			if(value>52) {
				if (value==53) {
					idValue="53";
					return "cards_"+(idValue)+".bmp";
				}
				if (value==54) {
					idValue="54";
					return "cards_"+(idValue)+".bmp";
				}
				if (value==101) {
					idValue="O";
					return "cards_"+(idValue)+".bmp";
				}
				
				
				if (value==401) {
					idValue="401";
					return "cards_"+(idValue)+".bmp";
				}
				if (value==402) {
					idValue="402";
					return "cards_"+(idValue)+".bmp";
				}
				if (value==403) {
					idValue="403";
					return "cards_"+(idValue)+".bmp";
				}
				if (value==404) {
					idValue="404";
					return "cards_"+(idValue)+".bmp";
				}
			}
			else if (suit.name()=="Clubs") {
				idValue=String.valueOf(value+(13*0));
			}
			else if (suit.name()=="Diamonds") {
				idValue=String.valueOf(value+(13*1));
				
			} else if (suit.name()=="Hearts") {
				idValue=String.valueOf(value+(13*2));
				
			} else if (suit.name()=="Spades") {
				idValue=String.valueOf(value+(13*3));
				
			}
			else {
				return null;
			}
			
			//System.out.println(idValue);
			return "cards_"+(idValue)+".bmp";
		}
		
		/**
		 * Returns a string that can be used to re-initialize the card
		 * @return {String} Class properties, " of " separated.
		 */
		public String saveAsString() {
			return valueString(value) + " of " + suit.name() + " of " + isReversed;
		}
		
		/**
		 * Class constructor
		 * @param {Integer} value The value of the card, in [1,14]
		 * @param {Suit} suit The suit of the card
		 */
		
		
		public Card(int value, Suit suit, String backImg) {
			this.value = value;
			this.suit = suit;		
			
			isReversed = false;
			//System.out.println(this.toString());
			try {
				// Load the image for the current file
				//System.out.println(getCardID());
			    //File img =new File("./img/"+getCardID());
				
				File img = new File("\\\\WJ424-DATA\\STUHOME\\427242\\Documents\\tmp\\Solitaire_Swing\\img\\" +getCardID());
			    image = ImageIO.read(img); 
			    //System.out.println(Arrays.toString(ImageIO.getReaderFileSuffixes()));
				// Load the backImage
			    File img2 = new File(backImg);
				backImage = ImageIO.read(img2 );
				
				setBounds(0, 0, image.getWidth(), image.getHeight());
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			positionOffset = new Point(0,0);
			//setSize(new Dimension(100, 145));
			
			setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		    setMinimumSize(new Dimension(image.getWidth(), image.getHeight()));
		    //System.out.println("width: "+image.getWidth());
		    //System.out.println("height: "+image.getHeight());
			setOpaque(false);
		}
		
		public Card(int value, Suit suit) {
			this((int)value,(Suit)suit,(String)"./img/back6.bmp");
		}
		
		/**
		 * Turns the card with the back up
		 */
		public void hide() {
			isReversed = true;
		}
		
		/**
		 * Turns the card with the face up
		 */
		public void show() {
			isReversed = false;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			BufferedImage img = image;
			if(isReversed) img = backImage;

			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		}	
}