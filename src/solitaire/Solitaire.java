package solitaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import java.util.Stack;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.Collections;

import solitaire.Card.Suit;



public class Solitaire {
	//ArrayList<Stack <Card>> columns;
	ArrayList<Stack<Card>> listOfStacks=new ArrayList<Stack<Card>>();
	ArrayList<Stack<Card>> listOfFinalStacks=new ArrayList<Stack<Card>>();
	
	ArrayList<Card> cardBacksArray=new ArrayList<Card>();
	//Stack<Card> deck;
	public String deckBackStr="./img/back6.bmp";
	Stack<Card> deck_52 = new Stack<Card>();
	Stack<Card> drawPile = new Stack<Card>();
	
	Card DeckDisplay;
	
	Card deckRefresh = new Card(101, Suit.Diamonds,deckBackStr);
	Card backOfDeck = new Card(53,Suit.Clubs,deckBackStr);
	
	Card finalClubsCard = new Card(401,Suit.Clubs);
	Card finalDiamondsCard = new Card(402,Suit.Diamonds);
	Card finalHeartsCard = new Card(403,Suit.Hearts);
	Card finalSpadesCard = new Card(404,Suit.Spades);
    
	
	Card placeHoldActiveOne = new Card(53,Suit.Clubs,deckBackStr);
	Card placeHoldActiveTwo = new Card(53,Suit.Clubs,deckBackStr);
	Map<Integer, Stack<Card>> colGrab = new HashMap<Integer, Stack<Card>>();
	Map<Suit, String> SuitToColor = new HashMap<Suit, String>();
	public Stack<Card> stackOne = new Stack<Card>();
	public Stack<Card> stackTwo = new Stack<Card>();
	public Stack<Card> stackThree = new Stack<Card>();
	public Stack<Card> stackFour = new Stack<Card>();
	public Stack<Card> stackFive = new Stack<Card>();
	public Stack<Card> stackSix = new Stack<Card>();
	public Stack<Card> stackSeven = new Stack<Card>();
	
	
	
    Card backOne = new Card(1,Suit.Clubs,"./img/back1.bmp");
    Card backTwo = new Card(1,Suit.Clubs,"./img/back2.bmp");
    Card backThree = new Card(1,Suit.Clubs,"./img/back3.bmp");
    Card backFour = new Card(1,Suit.Clubs,"./img/back4.bmp");
    Card backFive = new Card(1,Suit.Clubs,"./img/back5.bmp");
    Card backSix = new Card(1,Suit.Clubs,"./img/back6.bmp");
    Card backSeven = new Card(1,Suit.Clubs,"./img/back7.bmp");
    Card backEight = new Card(1,Suit.Clubs,"./img/back8.bmp");
    Card backNine = new Card(1,Suit.Clubs,"./img/back9.bmp");
    Card backTen = new Card(1,Suit.Clubs,"./img/back10.bmp");
    Card backEleven = new Card(1,Suit.Clubs,"./img/back11.bmp");
    Card backTwelve = new Card(1,Suit.Clubs,"./img/back12.bmp");
    
    
	public Stack<Card> finalStackClubs = new Stack<Card>();
	public Stack<Card> finalStackDiamonds = new Stack<Card>();
	public Stack<Card> finalStackHearts = new Stack<Card>();
	public Stack<Card> finalStackSpades = new Stack<Card>();
	
	public Stack<Card> dispDrawnPile = new Stack<Card>();
	
	public Stack<Card> movingStack = new Stack<Card>();
	
	//the part of your program that's in charge of game rules goes here.
	
	public void putCardsToPiles() {
		for (int i = 0; i < 7; i++) {
	    	   
			for (int j = 0; j <= i; j++) {
    	    	
				listOfStacks.get(i).push( (Card)deck_52.pop() );
    	    	listOfStacks.get(i).peek().hide();
    	    	//if(i!=j) {
    	    	//	(listOfStacks.get(i).peek()).hide();
    	    	//}
    	    }
    	    //System.out.println("\\/");
    	}
	}
	public Solitaire() {

		
		initiate();
		//legalMove(stackOne.peek(),stackTwo.peek());
		//moveCard(stackOne.peek(),stackTwo.peek());
		
		//whereCard(drawPile.peek());
		//moveCard(stackThree.get(1),stackSix.peek());
		
		//moveCard(stackOne.peek(),stackTwo.peek());
		
		//moveCard(stackTwo.peek(),stackThree.peek());
		
		//moveCard(stackThree.peek(),stackFour.peek());
		
		//moveCard(stackFour.peek(),stackFive.peek());
		
		//moveCard(stackSix.get(2),stackOne.peek());
		
		//System.out.println(stackSix.peek());
		//System.out.println(stackSix.get(stackSix.size()-1));
		//moveCard(stackSeven.get(2),stackThree.peek());
		
		//moveCard(stackSix.get(stackSix.size()-2),stackTwo.peek());
		//drawCard();
		//drawCard();
		//drawCard();
		//drawCard();
		//drawCard();
		//drawCard();
		//drawCard();
		//drawCard();
		//drawCard();
		//drawCard();
		//drawCard();
		//drawCard();
		//drawCard();
		//drawCard();
//		for (int g = 0; g<12; g++) {
//			for (int i = 0; i<7; i++) {
//				if (!drawPile.empty()) {
//					moveCard(drawPile.peek(),listOfStacks.get(i).peek());
//					
//					
//				}
//				
//			}
//			
//		}
		
		
//		addCard(6,new Card(12, Suit.Diamonds));
//		addCard(6,new Card(11, Suit.Diamonds));
//		addCard(6,new Card(10, Suit.Diamonds));
//		addCard(6,new Card(9, Suit.Diamonds));
//		addCard(6,new Card(8, Suit.Diamonds));
//		addCard(6,new Card(7, Suit.Diamonds));
//		addCard(6,new Card(6, Suit.Diamonds));
//		addCard(6,new Card(5, Suit.Diamonds));
//		addCard(6,new Card(4, Suit.Diamonds));
//		addCard(6,new Card(3, Suit.Diamonds));
//		addCard(6,new Card(2, Suit.Diamonds));
//		addCard(6,new Card(1, Suit.Diamonds));
		//flipPileCard();
	}
	public void addCard(int stackNum,Card card) {
		//STACKS -> 0-6!!
		//colGrab.get(stackNum);
		
		listOfStacks.get(stackNum).push(card);
	}
	
	public void deckDispUpdate() {
		if(deck_52.isEmpty()) {
			DeckDisplay=deckRefresh;
		}else {
			DeckDisplay=backOfDeck;
		}
		
	}
	public void drawCard() {
		if(!deck_52.isEmpty()) {
			Card drew = deck_52.pop();
			drawPile.push(drew);
			deckDispUpdate();
		}
	}
	public String whereCard(Card theCard) {
		for(Stack<Card> i : listOfStacks){
			if(i.contains(theCard)){
				for(int g=0; g<listOfStacks.size();g++) {
					if(listOfStacks.get(g).contains(theCard)) {
						//System.out.println(listOfStacks.get(g) + " this is the stack!!");
						//System.out.println("mainStack " + Integer.toString(g) + " " + i.indexOf(theCard));
						return "mainStack " + Integer.toString(g) + " "  + i.indexOf(theCard);
					}
				}
				//System.out.println(i.indexOf(theCard)+" "+ listOfStacks); 
			}
		}
		if (!drawPile.empty()) {
			if(drawPile.peek()==theCard) {
				//the card is on top of draw pile
				//System.out.println(drawPile.indexOf(drawPile.peek()));
				//System.out.println("Drawpile 0 " + drawPile.indexOf(drawPile.peek()));
				return "drawPile 0 " + drawPile.indexOf(drawPile.peek());
			}
		}
		
		for(Stack<Card> g : listOfFinalStacks){
			
			for(int i=0; i<listOfFinalStacks.size();i++) {
				if(listOfFinalStacks.get(i).peek()==(theCard)) {
					//System.out.println("finalStack " + Integer.toString(i) + " " + g.indexOf(theCard));
					return "finalStack " + Integer.toString(i) +" "  + g.indexOf(theCard);
				}
			}
			

		}
		
		if(theCard.value==101) {
			switch (theCard.suit) {
			
			case Clubs:
				return "finalStack " + Integer.toString(0) +" "  + Integer.toString(0);

			case Diamonds:
				return "finalStack " + Integer.toString(1) +" "  + Integer.toString(0);
			case Hearts:
				return "finalStack " + Integer.toString(2) +" "  + Integer.toString(0);
			case Spades:
				return "finalStack " + Integer.toString(3) +" "  + Integer.toString(0);
			default: System.out.println("switch error!");
			
			}
				
					}
		System.out.println(theCard);
		System.out.println("gonna return null");
		return null;
	}
	 
	public String wC_Decomp(String whereCardOutput, int valueToGet) { //value 1 2 or 3
		//if(whereCardOutput!=null)
		System.out.println("\n\n cardoutput  " + whereCardOutput);
		String[] arrDecomp = whereCardOutput.split(" ");
		valueToGet--;
		return arrDecomp[valueToGet];
		
	}
	public boolean moveCard(Card toMove, Card locationToMove) {
		//whereCard(toMove);
		//whereCard(locationToMove);
		String toMove_StackName = wC_Decomp(whereCard(toMove),1);
		int toMove_StackIndex = Integer.valueOf(wC_Decomp(whereCard(toMove),2));
		int toMove_CardIndex = Integer.valueOf(wC_Decomp(whereCard(toMove),3));
		
		
		String locationToMove_StackName = wC_Decomp(whereCard(locationToMove),1);
		int locationToMove_StackIndex = Integer.valueOf(wC_Decomp(whereCard(locationToMove),2));
		int locationToMove_CardIndex = Integer.valueOf(wC_Decomp(whereCard(locationToMove),3));
		
		//CRITICAL TO ADD BACK, DISABLED FOR TESTING
		//CRITICAL TO ADD BACK, DISABLED FOR TESTING
		if(legalMove(toMove,locationToMove)) {
		//CRITICAL TO ADD BACK, DISABLED FOR TESTING
		//CRITICAL TO ADD BACK, DISABLED FOR TESTING
		
		//if (true) {  //TESTING ONLY
			if(toMove_StackName.contentEquals("mainStack")) {
				int toMove_Size=listOfStacks.get(toMove_StackIndex).size();
				System.out.println(toMove_CardIndex);
				System.out.println(toMove_Size);
				//listOfStacks.get(toMove_StackIndex);
				
				
				for (int i=toMove_Size-1;i>=toMove_CardIndex;i--) {
					System.out.println(listOfStacks.get(toMove_StackIndex));
					//System.out.println(listOfStacks.get(toMove_StackIndex).peek());
					//System.out.println(i);
					movingStack.push(listOfStacks.get(toMove_StackIndex).pop());
				}
				System.out.println("loaded from mainstack: " + movingStack);
				
			}
			else if (toMove_StackName.contentEquals("drawPile")) {
				System.out.println("is drawPile");
				//listOfStacks.get(toMove_StackIndex);
				//for (int i=0;i<=toMove_CardIndex;i++) {
				movingStack.push(drawPile.pop());
				//}
				System.out.println("loaded from drawPile: " + movingStack);
			}
			
			else if(toMove_StackName.contentEquals("finalStack")) {
				//listOfStacks.get(toMove_StackIndex);
				//for (int i=0;i<=toMove_CardIndex;i++) {
				movingStack.push(listOfFinalStacks.get(toMove_StackIndex).pop());
				//}
				System.out.println("loaded from finalStack: " + movingStack);
			}
			else {
				return false;
			}
			
			///Now have the Move Stack.
			//flipPileCard();
			int movSize = movingStack.size();
			
			System.out.println(locationToMove_StackName);
			if(locationToMove_StackName.contentEquals("mainStack")) {
				System.out.println(movingStack);
				//listOfStacks.get(toMove_StackIndex);
				for (int i=0;i<movSize;i++) {
					System.out.println(movingStack);
					listOfStacks.get(locationToMove_StackIndex).push(movingStack.pop());
					
					
				}
				System.out.println("Pushed moveStack to mainstack");
				return true;
			}
			
			else if (locationToMove_StackName.contentEquals("finalStack")) {
				//listOfStacks.get(toMove_StackIndex);
				//for (int i=0;i<=toMove_CardIndex;i++) {
				listOfFinalStacks.get(locationToMove_StackIndex).push(movingStack.pop());
				//}
				
				System.out.println("Pushed moveStack to finalstack");
				return true;
			}
			else {
				if(movingStack.isEmpty()!=true) System.out.println("MOVING STACK DIDNT EMPTY WHYYYYYYY");
				return false;
			}
			
			
			//return false;
		}
		
		return false;
	}
	
	public void flipPileCard() {
		for(Stack<Card> i : listOfStacks){
			if(i.isEmpty()) return;
			i.peek().show();
		}
	}
	public boolean legalMove(Card toMove, Card location) {
		
		//make sure you check to see if the card is actually visible to us! You can do so by doing.
		boolean isValidCard =true;
		boolean isValidFlip = false;
		boolean isValidLoc = false;
		boolean isValidOrder = false;
		// you are likely going to need to know where both the “toMove” and the “location” cards are so ‘.contains()’ will be a useful method for you to call
		
		//for example you could check to see if the card you’re planning to move is in one of the piles in the center by doing:
		for(Stack<Card> i : listOfStacks){
			if(i.isEmpty() ) {
				//i.push(new Card(54,Suit.Clubs));
				//System.out.println();
				//break;
			}
			if(i.peek().value==54 && i.peek()==location) {//is blank slot
				if(toMove.value==13) { //trying to move king
					isValidLoc = true;
					isValidOrder = true;
				}
			}
			
			
			System.out.println("/\\/\\/\\/\\/\\ legalMoveLoc = " + location + "/\\/\\/\\/\\/\\");
			//System.out.println(i);
			//System.out.println(toMove);
			if(i.contains(toMove)){
			//the card is inside one of the middle piles
				isValidCard=true;
			}
			if (!i.isEmpty()) {
				System.out.println(i.peek()+ " =(top card)= " + location);
			}
			if(i.peek()==location) {
				isValidLoc=true;
				
				if(SuitToColor.get(toMove.suit) !=  SuitToColor.get(location.suit)) { //Make sure alternate color
					if(toMove.value == (location.value-1)) //Make sure it one value lower
					{
						isValidOrder=true;
					}
				}
			}
			
			
		}
		
		if (!drawPile.empty()) {
			if(drawPile.peek()==toMove) {
				//the card is on top of draw pile
				isValidCard=true;
				
			}

		}
		for(Stack<Card> g : listOfFinalStacks){
			if(g.isEmpty()) {
				break;
			}
			try {
				if(g.peek()==toMove) {
					//the card is on top of one of the final Stacks
					isValidCard=true;
				}
				if(g.peek()==location) {
					System.out.println("++++++++++ if statement 1");
					if(SuitToColor.get(toMove.suit) ==  SuitToColor.get(location.suit)) {
						if(toMove.suit==location.suit) {
							System.out.println(toMove.suit + " < to move loc > " + location.suit);
							//System.out.println("++++++++++ if statement 2");
							//System.out.println(location.value);
							//System.out.println(toMove.value);
							isValidLoc=true;
							System.out.println(" \n\nVALUEEEEEEEEESSSS :" + toMove.value + "   and   " + location.value);
							if((toMove.value == (location.value+1))) //Make sure it one value lower
							{
								isValidOrder=true;
								System.out.println("++++++++++ if statement 3");
								
								System.out.println("#1 " + (wC_Decomp(whereCard(toMove),1) != "mainStack"));
								System.out.println("#2 " + (wC_Decomp(whereCard(toMove),1)=="mainStack"));
								System.out.println("#3 " +(Integer.valueOf(wC_Decomp(whereCard(toMove),3))==0));
								
								
								
								if( (wC_Decomp(whereCard(toMove),1) != "mainStack") || ( (wC_Decomp(whereCard(toMove),1)=="mainStack") && (Integer.valueOf(wC_Decomp(whereCard(toMove),3))==0)) )						
								{
									System.out.println("++++++++++ if statement 4");
									isValidOrder=true;
								}
							}
							if((((location.value==401) || (location.value==402) || (location.value==403) || (location.value==404)) && (toMove.value==1))) {
								isValidOrder=true;
							}
						}
					}
				}
			}catch(Exception e) {
		    	   e.printStackTrace(); 
		    }
		}
		//OR dispDrawnPile.peek() == toMove ?
		
		if(!toMove.isReversed){
			//DO SUIT CHECK TESTING AND VALUE TESTING ELSEWHERE OR SOMETHING ELSE CAUSE
			//MOVING TO FINAL STACK IS DIFFERENT
				isValidFlip=true;
		//}
		//here the card is
		}
		
//		if(( (wC_Decomp(whereCard(toMove),1)=="mainStack")) && (Integer.valueOf(wC_Decomp(whereCard(toMove),3))!=0) ) {
//			
//			for (int i=0;i<=Integer.valueOf(wC_Decomp(whereCard(toMove),3));i++) {
//				System.out.println(i+" fawsa");
//				
//				listOfStacks.get(Integer.valueOf(wC_Decomp(whereCard(toMove),2)));  //get currStack
//				
//			}
//			
//			/*
//			 * for (int i=0;i<=toMove_CardIndex;i++) { movingStack.push(.pop()); }
//			 */
//			
//		}
		
		//if needed, check if all cards below aren't reversed
		if (toMove == location) {
			isValidLoc=false;
		}
		System.out.println("Debug legal move: " + isValidFlip + "  "+  isValidCard  + "  "+ isValidLoc  + "  "+ isValidOrder);
		if(isValidFlip==true && isValidCard==true && isValidLoc == true && isValidOrder==true) {
			//System.out.println("valid!");
			return true;
		}
		//System.out.println("Not Valid!");
		return false;
	}
	private void initiate() {
		backOfDeck.hide();
		DeckDisplay=backOfDeck;
		SuitToColor.put(Suit.Clubs,"Black");
		SuitToColor.put(Suit.Spades,"Black");
		SuitToColor.put(Suit.Hearts,"Red");
		SuitToColor.put(Suit.Diamonds,"Red");
		colGrab.put(0,stackOne);
		colGrab.put(1,stackTwo);
		colGrab.put(2,stackThree);
		colGrab.put(3,stackFour);
		colGrab.put(4,stackFive);
		colGrab.put(5,stackSix);
		colGrab.put(6,stackSeven);
		
		listOfStacks.add(stackOne);
		listOfStacks.add(stackTwo);
		listOfStacks.add(stackThree);
		listOfStacks.add(stackFour);
		listOfStacks.add(stackFive);
		listOfStacks.add(stackSix);
		listOfStacks.add(stackSeven);
		finalStackClubs.push(finalClubsCard);
		finalStackDiamonds.push(finalDiamondsCard);
		finalStackHearts.push(finalHeartsCard);
		finalStackSpades.push(finalSpadesCard);
		
		listOfFinalStacks.add(finalStackClubs);
		listOfFinalStacks.add(finalStackDiamonds);
		listOfFinalStacks.add(finalStackHearts);
		listOfFinalStacks.add(finalStackSpades);
		
		
		
		cardBacksArray.add(backOne);
		cardBacksArray.add(backTwo);
		cardBacksArray.add(backThree);
		cardBacksArray.add(backFour);
		cardBacksArray.add(backFive);
		cardBacksArray.add(backSix);
		cardBacksArray.add(backSeven);
		cardBacksArray.add(backEight);
		cardBacksArray.add(backNine);
		cardBacksArray.add(backTen);
		cardBacksArray.add(backEleven);
		cardBacksArray.add(backTwelve);
		
		
		//cardMap.put(0, Suit.Clubs);
	    //cardMap.put(1, Suit.Diamonds);
	    //cardMap.put(2, Suit.Hearts);
	    //cardMap.put(3, Suit.Spades);
		
		
		for (Suit suit : Suit.values()) {
		       for (int i =1; i <= 13; i++) {
		    	   //Card tmp = new Card(i,suit);
		    	   //tmp.addMouseListener();
		    	   deck_52.push(new Card(i,suit,deckBackStr));
		    	   //deck_52.peek().backImgStr=backImg;
		    	   //System.out.println(j + " of " + i);
		       }
	       }
		
		//DEBUG UNCOMMENT LATER 
		//DEBUG UNCOMMENT LATER 
		//DEBUG UNCOMMENT LATER 
		//DEBUG UNCOMMENT LATER 
		//DEBUG UNCOMMENT LATER 
		//DEBUG UNCOMMENT LATER 
	    Collections.shuffle(deck_52);
		//DEBUG UNCOMMENT LATER 
		//DEBUG UNCOMMENT LATER 
		//DEBUG UNCOMMENT LATER 
		//DEBUG UNCOMMENT LATER 
		//DEBUG UNCOMMENT LATER 
			//Stack<Card> tempReturn= new Stack<Card>();
			//while(!deck_52.empty()){
			//	tempReturn.push(deck_52.pop());
			//}
			//deck_52.sort(Collections.reverseOrder());
			
			//deck_52=tempReturn;
			//System.out.println(tempReturn);
			for (int i = 0; i < 7; i++) {
				listOfStacks.get(i).push(new Card(54,Suit.Clubs));
				for (int j = 0; j <= i; j++) {
	    	    	
					listOfStacks.get(i).push( (Card)deck_52.pop() );
	    	    	listOfStacks.get(i).peek().hide();
	    	    	//if(i!=j) {
	    	    	//	(listOfStacks.get(i).peek()).hide();
	    	    	//}
	    	    }
				
	    	    //System.out.println("\\/");
	    	}
	       //putCardsToPiles();
		
		flipPileCard();  //Seems to work fine, just keep in mind. Should run this frequently.
		
		//drawCard();
		
		
	}
	
	
	       

}
