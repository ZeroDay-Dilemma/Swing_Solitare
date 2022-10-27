package solitaire;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.border.*;
import java.util.Stack;

import solitaire.Card.Suit;

public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
	Solitaire game;
	JPanel TopRow,LeftRow,RightRow,CenterPanel,BottomRow;
	Container gamePane;
	Border blackline,redline,greenline;
	JPanel deckToDrawImg;
	JPanel drawPilePanel;
	JPanel finalStacks;
    JFrame popup;
	JFrame youWin;
	boolean hasDoneTheThing = false;
	
	JMenu menu;
	GridBagConstraints c;
	JMenuBar menuBar;
	JMenuItem menuItem_Pane1,menuItem_CardBack,menuItem_Pane2;
	//JMenuItem menuCardStack;
	//Map<Integer, Suit> cardMap = new HashMap<Integer, Suit>();
	//Map<Integer, Stack<Card>> rowPrint = new HashMap<Integer, Stack<Card>>();
	JPanel activeCards;
	public Card compCard = new Card(54,Suit.Clubs);
	Card deckRefresh = new Card(101, Suit.Diamonds);
	
	
	public JLayeredPane drawPile(Stack<Card> stackIn, boolean isVertical,boolean isFinalRow) {
	    Object cards[];
	    //cards = Stream.of(stackIn).toArray(Card[]::new); //please note we convert this stack to an array so that we can iterate through it backwards while drawing. You�ll need to cast each element inside cards to a <Card> in order to use the methods to adjust their position
	    //cards = stackIn.toArray();
	    cards = stackIn.toArray(new Card[0]);
	    //System.out.println(Arrays.toString(cards));
	    JLayeredPane layeredPane = new JLayeredPane();
	    double spacingMult=1;
	    int heightTT=540;
	    if (stackIn.contains(game.finalClubsCard) || stackIn.contains(game.finalDiamondsCard) || stackIn.contains(game.finalHeartsCard) || stackIn.contains(game.finalSpadesCard)) {
	    	spacingMult=0.5;
	    	heightTT=106;
	    }
	    if (isVertical==true) {
	    	if(isFinalRow==true) {
	    		layeredPane.setPreferredSize(new Dimension(71, heightTT));
			    for (int i= 0; i < cards.length; i++) {
			    	if(cards.length>14) {
			    		spacingMult=1.3;
			    	}
		            // accessing each element of array
			    	((Card)cards[i]).setBounds(0,i,71,96);
			    	
		            layeredPane.add((Card)cards[i], new Integer(i));
		            
		        }
			    spacingMult=1;
//Border		LayeredPane.setBorder(redline);
			    //layeredPane.addMouseListener(this);
			    return layeredPane;
	    	}
	    	layeredPane.setPreferredSize(new Dimension(71, heightTT));
		    for (int i= 0; i < cards.length; i++) {
		    	if(cards.length>14) {
		    		spacingMult=1.3;
		    	}
	            // accessing each element of array
		    	((Card)cards[i]).setBounds(0,i*96/(int)(4*spacingMult),71,96);
		    	
	            layeredPane.add((Card)cards[i], new Integer(i));
	            
	        }
		    spacingMult=1;
//Border	layeredPane.setBorder(redline);
		    //layeredPane.addMouseListener(this);
		    return layeredPane;
		    
	    } else {
	    	layeredPane.setPreferredSize(new Dimension(540,96));
	    	for (int i= 0; i < cards.length; i++) {
	            // accessing each element of array
		    	((Card)cards[i]).setBounds(0,0,71,96); //Puts the drawn cards stacks next to eachother

	            layeredPane.add((Card)cards[i], new Integer(i));
	        }
		    spacingMult=1;
 //Border   layeredPane.setBorder(redline);
		    return layeredPane;
	    }
	}
	
	public JLayeredPane drawPile(Stack<Card> stackIn, boolean isVertical) {
		drawPile(stackIn,isVertical,false);
	    Object cards[];
	    //cards = Stream.of(stackIn).toArray(Card[]::new); //please note we convert this stack to an array so that we can iterate through it backwards while drawing. You�ll need to cast each element inside cards to a <Card> in order to use the methods to adjust their position
	    //cards = stackIn.toArray();
	    cards = stackIn.toArray(new Card[0]);
	    
	    JLayeredPane layeredPane = new JLayeredPane();
	    
	    double spacingMult=1;
	    int heightTT=540;
	    if (stackIn.contains(game.finalClubsCard) || stackIn.contains(game.finalDiamondsCard) || stackIn.contains(game.finalHeartsCard) || stackIn.contains(game.finalSpadesCard)) {
	    	spacingMult=0.5;
	    	heightTT=106;
	    }
	    if (isVertical==true) {
	    	layeredPane.setPreferredSize(new Dimension(71, heightTT));
		    for (int i= 0; i < cards.length; i++) {
		    	if(cards.length>14) {
		    		spacingMult=1.3;
		    	}
	            // accessing each element of array
		    	((Card)cards[i]).setBounds(0,i*96/(int)(4*spacingMult),71,96);
		    	
	            layeredPane.add((Card)cards[i], new Integer(i));

	        }
		    spacingMult=1;
//Border    layeredPane.setBorder(redline);
		    //layeredPane.addMouseListener(this);
		    return layeredPane;
		    
	    } else {
	    	layeredPane.setPreferredSize(new Dimension(540,96));
	    	for (int i= 0; i < cards.length; i++) {
	            // accessing each element of array
		    	((Card)cards[i]).setBounds(0,0,71,96); //Puts the drawn cards stacks next to eachother
		    	
	            layeredPane.add((Card)cards[i], new Integer(i));
	        }
		    spacingMult=1;
//Border    layeredPane.setBorder(redline);
		    return layeredPane;
	    }
	}
	
	public void addTheListeners() {
		for(Card c: game.deck_52) {
			c.addMouseListener(this);
			c.addMouseMotionListener(this);
		}
		for (int i = 0; i < 7; i++) {
			if(!game.listOfStacks.get(i).empty()) {
				for (Card card: game.listOfStacks.get(i)) {
					
						card.addMouseListener(this);
					
		        }
			}
		}
		for (int i = 0; i < 4; i++) {
			if(!game.listOfFinalStacks.get(i).empty()) {
				for (Card card: game.listOfFinalStacks.get(i)) {
						card.addMouseListener(this);
					
		        }
			}
		}
		
		if (!tempPile.isEmpty()) {
			for(Card card: tempPile) {
				card.addMouseListener(this);
				card.addMouseMotionListener(this);
			}
		}
		if(!game.drawPile.empty()) {
			for (Card card: game.drawPile) {
	            //System.out.println(card);
				card.addMouseListener(this);
				card.addMouseMotionListener(this);
	        }
		}
		
		
	}
   public GUI(Solitaire game){
	  
	   blackline = BorderFactory.createLineBorder(Color.black,3);
	   redline = BorderFactory.createLineBorder(Color.red,3);
	   greenline = BorderFactory.createLineBorder(Color.green,3);
	   this.game= game;
	   addTheListeners();
        //Create and set up the window.
       setTitle("Solitaire");
       setSize(900,700);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       // this supplies the background
       try {
    	   File img = new File("./img/background.jpg");
    	   setContentPane(new ImagePanel(ImageIO.read(img)));
       }catch(IOException e) {
    	   e.printStackTrace();
       }
       gamePane = getContentPane();

       gamePane.setLayout(new BorderLayout());
       TopRow=new JPanel();
       LeftRow=new JPanel();
       RightRow=new JPanel();
       TopRow.addMouseListener(this);
       TopRow.addMouseMotionListener(this);
		
       LeftRow.addMouseListener(this);
       LeftRow.addMouseMotionListener(this);
		
       RightRow.addMouseListener(this);
       RightRow.addMouseMotionListener(this);
       TopRow.setOpaque(false);

       LeftRow.setOpaque(false);
       RightRow.setOpaque(false);
       menuBar = new JMenuBar();
       menuBar = new JMenuBar();
	   	menu = new JMenu("Menu");
	   	menuBar.add(menu);
	   	
	   	
	   	
	   	
	   	menuItem_Pane1 = new JMenuItem("New Game");
    	menuItem_Pane2 = new JMenuItem("Close");
    	menuItem_CardBack = new JMenuItem("Personalize");
    	
    	menuItem_Pane1.addMouseListener(this);
    	menuItem_Pane1.addMouseMotionListener(this);

    	menuItem_CardBack.addMouseListener(this);
    	menuItem_CardBack.addMouseMotionListener(this);
    	
    	menuItem_Pane2.addMouseListener(this);
    	menuItem_Pane2.addMouseMotionListener(this);


    	menu.add(menuItem_Pane1);
    	menu.add(menuItem_CardBack);
    	menu.add(menuItem_Pane2);

    	
    	menu.getAccessibleContext().setAccessibleDescription(
    	        "The menu stuff. I just wanted to use this line of code tbh");
       //JPanel rowOne = new JPanel();
//Border	rowOne.setBorder(redline);
       
       activeCards = new JPanel();
//Border	       activeCards.setBorder(redline);
       
       

       
       
       
       popup = new JFrame();
       popup.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
       popup.setResizable(false);
       popup.setSize((71*4)+(20*3),(96*3)+(20*4)+100);
       	
       
       youWin = new JFrame();
       youWin.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
       youWin.setResizable(false);
       youWin.setSize(150,150);
       
       youWin.add(new JLabel("You Win!!"));
       popup.setLayout(new GridBagLayout());
       c = new GridBagConstraints();
       c.fill = GridBagConstraints.HORIZONTAL;
       
       
       JLabel popLabel = new JLabel("Pick a style!",JLabel.CENTER);
       popLabel.setPreferredSize(new Dimension(324, 100));
       c.gridx=0;
       c.gridy=0;
       c.insets = new Insets(2,4,2,4);
       for(Card card : game.cardBacksArray) {
    	   card.hide();
    	   
    	   popup.add(card,c);
    	   
    	   
    	   card.addMouseListener(this);
    	   c.gridx+=1;
    	   if(c.gridx==3) {
    		   c.gridx=0;
    		   c.gridy+=1;
    	   }
       }
       c.gridx=1;
       c.gridy=4;
       popup.add(popLabel,c);
       System.out.println(popup.getContentPane().getComponents().length);
       for(int i =0; i< popup.getContentPane().getComponentCount(); i++) {
    	   popup.getContentPane().getComponent(i).setName("bkgdImg:"+(i+1));
       }
       
       deckToDrawImg = new JPanel();
       drawPilePanel = new JPanel();
       finalStacks= new JPanel();
       
       
       popup.setVisible(false);

       TopRow.setPreferredSize(new Dimension(900, 150));
       TopRow.setMinimumSize(new Dimension(900, 150));
       
       LeftRow.setPreferredSize(new Dimension(735, 550));
       LeftRow.setMinimumSize(new Dimension(735, 550));
       
       RightRow.setPreferredSize(new Dimension(150, 550));
       RightRow.setMinimumSize(new Dimension(150, 550));
       
       FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
       FlowLayout middleCards = new FlowLayout(FlowLayout.CENTER,30,10);
       FlowLayout TopDeck = new FlowLayout(FlowLayout.LEFT,10,10);
       FlowLayout TopActiveCards = new FlowLayout(FlowLayout.RIGHT,10,10);
       FlowLayout rightLayout = new FlowLayout(FlowLayout.CENTER,10,60);

       flowLayout.setAlignOnBaseline(true);

       LeftRow.setLayout(middleCards);
       RightRow.setLayout(rightLayout);
       TopRow.setLayout(TopDeck);
       deckToDrawImg.setLayout(new BoxLayout(deckToDrawImg, BoxLayout.PAGE_AXIS));
       finalStacks.setLayout(new BoxLayout(finalStacks, BoxLayout.PAGE_AXIS));
       
       
       gamePane.add(TopRow,"North");

       gamePane.add(LeftRow,"West");
       gamePane.add(RightRow,"East");
     
       
       deckToDrawImg.setOpaque(false);
       deckToDrawImg.add(game.DeckDisplay);
       deckToDrawImg.addMouseListener(this);
       deckToDrawImg.setName("moreCards");
       
       activeCards.setOpaque(false);

       
       finalStacks.setOpaque(false);
       finalStacks.add(drawPile(game.finalStackClubs,true));
       finalStacks.add(drawPile(game.finalStackDiamonds,true));
       finalStacks.add(drawPile(game.finalStackHearts,true));
       finalStacks.add(drawPile(game.finalStackSpades,true));
       
       
       drawPilePanel.setOpaque(false);
       
       drawPilePanel.add(drawPile(game.drawPile,false));
       		//backOfDeck=blank back image
       		//deckRefresh = O card icon, click to loop thru deck again
       TopRow.add(deckToDrawImg);
       //TopRow.add(drawPile(game.drawPile,false));
       TopRow.add(drawPilePanel);
       Stack<Card> activeCardStack= new Stack<Card>();
       activeCards.add(game.placeHoldActiveOne);
       //activeCards.add(game.placeHoldActiveTwo);
       TopRow.add(activeCards);
       RightRow.add(finalStacks);
       
       LeftRow.add(drawPile(game.stackOne,true));
       LeftRow.add(drawPile(game.stackTwo,true));
       LeftRow.add(drawPile(game.stackThree,true));
       LeftRow.add(drawPile(game.stackFour,true));
       LeftRow.add(drawPile(game.stackFive,true));
       LeftRow.add(drawPile(game.stackSix,true));
       LeftRow.add(drawPile(game.stackSeven,true));

       setJMenuBar(menuBar);
       this.setVisible(true);
    }

   
	public Stack<Card> cutStack(Stack<Card> inputStack, Card cardToSplit) {
		Stack<Card> tempReturn  = new Stack<Card>();
		System.out.println(inputStack);
		System.out.println(cardToSplit);
		int indexTemp = inputStack.indexOf(cardToSplit);
		System.out.println("indexTemp" + indexTemp);
		
		do {
			tempReturn.push(inputStack.pop());
		}while(inputStack.size()>indexTemp);

		System.out.println("tempReturn  "+ tempReturn);

		return tempReturn;
	}
	
	
   	public void redrawDraw() {
   		drawPilePanel.removeAll();
		deckToDrawImg.removeAll();
		deckToDrawImg.add(game.DeckDisplay);
		drawPilePanel.add(drawPile(game.drawPile,false));
		deckToDrawImg.revalidate();
		deckToDrawImg.repaint();
		drawPilePanel.revalidate();
		drawPilePanel.repaint();
		gamePane.revalidate();
		gamePane.repaint();
   	}
   	
   	public void redrawStacks() {
   	   LeftRow.removeAll();
       LeftRow.add(drawPile(game.stackOne,true));
       LeftRow.add(drawPile(game.stackTwo,true));
       LeftRow.add(drawPile(game.stackThree,true));
       LeftRow.add(drawPile(game.stackFour,true));
       LeftRow.add(drawPile(game.stackFive,true));
       LeftRow.add(drawPile(game.stackSix,true));
       LeftRow.add(drawPile(game.stackSeven,true));
       LeftRow.repaint();
       LeftRow.revalidate();
       
       RightRow.removeAll();
       finalStacks.removeAll();
       finalStacks.add(drawPile(game.finalStackClubs,true,true));
       finalStacks.add(drawPile(game.finalStackDiamonds,true,true));
       finalStacks.add(drawPile(game.finalStackHearts,true,true));
       finalStacks.add(drawPile(game.finalStackSpades,true,true));
       RightRow.add(finalStacks);
       
       RightRow.repaint();
       RightRow.revalidate();
       
       finalStacks.repaint();
       finalStacks.revalidate();
       
   	}

////////////////////////////////////////////////////////////
////////////// REDECLARE ALL CARDS WITH UPDATED BACKS///////
////////////////////////////////////////////////////////////
///////////// WIP///////////////////////////////////////////
   	
   	public void resetCardBacks() {

   		
		for(Card c: game.deck_52) {
			c.changeBackImg(game.deckBackStr);
		}
		for (int i = 0; i < 7; i++) {
			if(!game.listOfStacks.get(i).empty()) {
				for (Card card: game.listOfStacks.get(i)) {
					
						card.changeBackImg(game.deckBackStr);

		        }
			}
		}
		for (int i = 0; i < 4; i++) {
			if(!game.listOfFinalStacks.get(i).empty()) {
				for (Card card: game.listOfFinalStacks.get(i)) {
					card.changeBackImg(game.deckBackStr);

					
		        }
			}
		
		}
		if (!tempPile.isEmpty()) {
			for(Card card: tempPile) {
				card.changeBackImg(game.deckBackStr);
			}
		}
		if(!game.drawPile.empty()) {
			for (Card card: game.drawPile) {
				card.changeBackImg(game.deckBackStr);
	        }
		}
		game.placeHoldActiveOne.changeBackImg(game.deckBackStr);
		
		game.deckRefresh.changeBackImg(game.deckBackStr);
		game.backOfDeck.changeBackImg(game.deckBackStr);
		redrawStacks();
		redrawDraw();
		
   		
	}

public void winStackCards() { //only run when all cards are in stacks, face up
	
	while(game.boardStacksEmpty()==false) { 
		for(Stack<Card> rowStacks : game.listOfStacks){
			if(rowStacks.size()>1){ //make sure there is a stack to loop thru
				//if there is an applicable final stack, move it to that stack
				for(Stack<Card> finalStack : game.listOfFinalStacks){
					if(game.legalMove(rowStacks.peek(), finalStack.peek())){
						finalStack.push(rowStacks.pop());
						redrawStacks();
						redrawDraw();
						break;
					}
				}
			}
		}
	}
	
}
   	public boolean checkWin(){
   		if(game.drawPile.isEmpty() && tempPile.isEmpty() && game.deck_52.isEmpty()) {
	   		if(checkWin2()==false) {
	   		
		   		for(Stack<Card> rowStacks : game.listOfStacks) {
		   			for(Card card : rowStacks) {
		   				if(card.isReversed==true) { //if all cards are face up, you have won
		   					return false;
		   				}
		   			}
		   		}
		   		return true;
	   		}else {
	   			return true;
	   		}
   		}
   		return false;
   	}
   	public boolean checkWin2() { //check if stacks are all stacked

   		for(int i=0; i<4;i++) {
   			if(game.listOfFinalStacks.get(i).size()<14) {
   				System.out.println(game.listOfFinalStacks.get(i));
   				return false;
   			}
   		}
		return true;
   	}
   	
   	public void stackTheCardsWin() { //Not working. Ignore
	   		for(Stack<Card> rowStacks : game.listOfStacks) {
	   				//HERE IS WHERE YOU STACK ALL THE CARDS WHEN YOU WIN ALL COOL-LIKE
	   				for(Stack<Card> finalRowStacks : game.listOfFinalStacks) {
	   					if(game.legalMove(rowStacks.peek(), finalRowStacks.peek())) {
	   						System.out.println("LEGAL MOVE LEGAL MOVELEGAL MOVE ");
	   						finalRowStacks.push(finalRowStacks.pop());
	   						redrawStacks();
	   						redrawDraw();
	   						System.out.println("Tried to do the cool thing....");
	   					}
	   				}
	   				//
	   			//}
	   		}
   		//}
   	}
   	
   	public void updateActiveCards() {
   		
   		System.out.println("trying to update active cards");
   		TopRow.remove(activeCards);
   		activeCards.removeAll();
		
   		Stack<Card> tempToReverse  = (Stack<Card>)tempPile.clone();
   		Stack<Card> tempCloned = (Stack<Card>)tempPile.clone();

		
   		Stack<Card> tempReversed = new Stack<Card>();
   		while(!tempToReverse.empty()){
   		    tempReversed.push(tempToReverse.pop());
   		}
   		
   		if(!tempPile.isEmpty()) { //then display ActiveCards
   			activeCards.add(tempCloned.peek());
   			System.out.println("added first activecard");
   			if(tempPile.size()>1) {

   				
   				activeCards.add(tempReversed.peek());

   				System.out.println("added second activecard");
   			}
	        
   		}else {
   		
	   		activeCards.add(game.placeHoldActiveOne);
	        System.out.println("set ActiveCards to blanks");
   		}
        System.out.println(tempPile);
   		TopRow.add(activeCards);
   		activeCards.repaint();
   		activeCards.revalidate();
   		TopRow.repaint();
   		TopRow.revalidate();
   		
   		
   		
   	}
    
    
	@Override
	
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		//IF YOU ALREADY HAVE CARDS ATTEMPT TO STACK
		
		//    |-----------------|
		//    | DRAW CARD STUFF |
		//    |-----------------|
		
		//    |--------------------|
		//    | You clicked a card |
		//    |--------------------|
		
		if(arg0.getComponent().getName()!=null) {

			if(arg0.getComponent().getName().contains("bkgdImg")) {
			 
				return;
			}
			
		}
		if(arg0.getComponent() instanceof Card) {
			Card c = (Card)arg0.getComponent();
			JLayeredPane p = (JLayeredPane)c.getParent();

		}
	}

	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//System.out.printf("Mouse exited", arg0);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//System.out.printf("Mouse entered", arg0);
	}
	public Component clickedPanel;
	public JPanel selectedCard = null;
	public Stack<Card> tempPile = new Stack<Card>();
	public JLayeredPane draggedStack;
	public Point mousePoint;
	JLayeredPane tempPileJLayeredPane;
	public String tempLocString;
	
	
	
	
	@Override
	
	
	
	public void mousePressed(MouseEvent arg0) {

		if(arg0.getComponent().getName()!=null) {
			
			
			if(arg0.getComponent().getName().contains("bkgdImg")) {
				String tempName=arg0.getComponent().getName();
				
				System.out.println(tempName.split(":")[1]);
				
				game.deckBackStr="./img/back" + tempName.split(":")[1] + ".bmp";
				resetCardBacks();
				popup.hide();
				return;
			}
			
		}
		if (arg0.getSource()==menuItem_Pane1) {
       	 System.out.println("Menu Button One Pressed");
		hasDoneTheThing=false;
       	 popup.dispose();
       	 youWin.dispose();
       	 this.dispose();
       	
       	Container frame=menuItem_Pane1.getParent();
       	do 
            frame = frame.getParent(); 
        while (!(frame instanceof JFrame));                                      
        ((JFrame) frame).dispose();
        
        this.dispose();
       	 this.game= new Solitaire();
       	 new GUI(game);
       	 
       	 return;
        }
		
		else if (arg0.getSource()==menuItem_CardBack) {
	       	 System.out.println("Menu Card Back Pressed");
	       	 
	       	 popup.show();
	       	 return;
	        }
		
        else if (arg0.getSource()==menuItem_Pane2) {
       	 System.out.println("Menu Button Two Pressed");
       	 this.dispose();
       	popup.dispose();
       	youWin.dispose();
       	Container frame=menuItem_Pane2.getParent();
       	do 
            frame = frame.getParent(); 
        while (!(frame instanceof JFrame));                                      
        ((JFrame) frame).dispose();
        
        this.dispose();
       
        Frame.getFrames()[0].dispose();
        System.out.println(Frame.getFrames());
       	 return;
        }
		
		
		
		
		
		//stackTheCardsWin();
		
		System.out.println(" checkWin: " + checkWin());
		System.out.println(" checkWin2: " + checkWin2());
		if(checkWin() && (checkWin2()==false)) {
			System.out.println("try to do the cool thing");
			//stackTheCardsWin();
			if(!hasDoneTheThing){ //ONLY DO THIS ONCE, WILL GET STUCK IN LOOP OTHERWISE
				hasDoneTheThing=true;
				winStackCards();
			}
			youWin.show();
			for(int i=0;i<50;i++) {
				System.out.println("you win!!");
				
			}
			return;
		}
		
		if(checkWin()) {
			youWin.show();
			for(int i=0;i<50;i++) {
				System.out.println("you win!!");
			}
			return;
		}
		if(checkWin2()) {
			youWin.show();
			for(int i=0;i<50;i++) {
				System.out.println("you win!!");
			}
			return;
		}
		
		//TODID   check for win  --Testing right now
		//       \__>  check if all cards on board are face up, and no cards in drawpile still
		//			   then just do a massive for loop for trying to move cards, auto just stack them I guess with some sort of winning message.
		
		
		//TODID make better indicator for where aces can go
		//COMPLETED
		
		
		//FIXME drawing deck with a card in tempPile causes a weird visual bug with the activeCard, not too critical but might be of note..? 
		System.out.println("Trying to draw card....");
		if(arg0.getComponent().getName()=="moreCards") {
			if (tempPile.isEmpty()) {
			JPanel temp = (JPanel) arg0.getComponent();
			//check if pile empty, then check if you can restack the cards
			
			if(game.deck_52.isEmpty()) { //pile to draw from is empty
				if(!game.drawPile.isEmpty()) { //drawpile has cards, deck_52 doesn't, restack cards!
	
					while(game.drawPile.isEmpty()==false) {
						game.deck_52.push(game.drawPile.pop());
					}
					game.deckDispUpdate();
					redrawDraw();
					redrawStacks();
					return;
				}
				
			}else {  //pile to draw from isnt empty
				game.drawCard();
				redrawDraw();
				redrawStacks();
				return;
				}
			}else {
				System.out.print ("  tried to draw card with a temp stack, returning stack now...");
				
				
				int stackIndex= Integer.valueOf(game.wC_Decomp(tempLocString,2));
				//System.out.println("");
				switch (game.wC_Decomp(tempLocString,1)) {
					case "mainStack":

						System.out.println("reached restack mainPile");
						
						while (!tempPile.empty()) {
						game.listOfStacks.get(stackIndex).push(tempPile.pop());
						}
						
						tempLocString="";
						updateActiveCards();
						redrawStacks();
						redrawDraw();
						return;
						//break;
					case "drawPile":
						System.out.println("reached restack drawPile");
						
						while (!tempPile.empty()) {
							game.drawPile.push(tempPile.pop());
							}
						
						tempLocString="";
						updateActiveCards();
						redrawDraw();
						redrawStacks();
						return;

					case "finalStack":
						System.out.println("reached restack finalPile");
						
						while (!tempPile.empty()) {
							game.listOfFinalStacks.get(stackIndex).push(tempPile.pop());
							}
						
						tempLocString="";
						redrawStacks();
						redrawDraw();
						return;

					default: System.out.println("Switch fail"); break;
				}
				
				
			}
		}
		System.out.print(   "....failed to draw card");
		
		
		System.out.println(tempLocString);
		
		
		System.out.println("Trying to stack the card....");
		if (!tempPile.isEmpty()) {

///////////////////////////////////////
//CHECK IF NEXT CLICKED CARD IS VALID PLACEMENT THEN PLACE WRITE HERE//
///////////////////////////////////////
			if(arg0.getComponent() instanceof Card) {
				Card cardSel = (Card)arg0.getComponent();
				if(cardSel.isReversed) { redrawDraw(); redrawStacks(); return; }
				System.out.println(tempPile.peek()+ "   MovingCard  legal move");
				System.out.println(cardSel + "   CardLoc legal move");
				System.out.println(game.legalMove(tempPile.peek(), cardSel));
				
				
				if (game.legalMove(tempPile.peek(), cardSel)) {
					int stackIndex= Integer.valueOf(game.wC_Decomp(game.whereCard(cardSel),2));
					if (game.wC_Decomp(game.whereCard(cardSel),1)=="drawPile") {System.out.println("cant add to drawPile");return;}
					if(tempPile.size()>1 && game.wC_Decomp(game.whereCard(cardSel),1).equals("finalStack")) {System.out.println("more than one card to final"); return;}
					System.out.println(tempPile.size() +" temp pile size") ;
					System.out.println((game.wC_Decomp(game.whereCard(cardSel),1).equals("finalStack")) + " maybe going to finalstack");


					System.out.println(game.wC_Decomp(game.whereCard(cardSel),1) + "  where is going");
					switch (game.wC_Decomp(game.whereCard(cardSel),1)) {
						case "mainStack":
							System.out.println("attempt to stack to main Stacks");
							while(!tempPile.isEmpty()) {
								game.listOfStacks.get(stackIndex).push(tempPile.pop());
								System.out.println("stacking to main stack");
							}
							game.flipPileCard(); 
							updateActiveCards();
							redrawDraw();
							redrawStacks();
							return;
						case "finalStack":
							//record where cards came from just incase not valid move
							System.out.println("attempt to stack to finalStack");
							while(!tempPile.isEmpty()) {
								game.listOfFinalStacks.get(stackIndex).push(tempPile.pop());
							}
							game.flipPileCard(); 
							updateActiveCards();
							redrawStacks();
							redrawDraw(); 
							return;
						default: System.out.println("Switch fail"); break;
				}
					
					
					


					
					}
				
				//IF get here, return cards to original stack

				}
			}
///////////////////////////////////////
///////////////////////////////////////
			///////////////////////////////////////
			
		System.out.print(   "failed to stack card");
		
		
		
		System.out.println("\ntrying to returnCards..");
		
		
		if (!tempPile.isEmpty()) {
			int stackIndex= Integer.valueOf(game.wC_Decomp(tempLocString,2));
			System.out.println("hi");
			switch (game.wC_Decomp(tempLocString,1)) {
				case "mainStack":
					System.out.println("reached restack mainPile");
					
					while (!tempPile.empty()) {
					game.listOfStacks.get(stackIndex).push(tempPile.pop());
					}
					
					tempLocString="";
					updateActiveCards();
					redrawDraw();
					redrawStacks();
					return;
					//break;
				case "drawPile":
					System.out.println("reached restack drawPile");
					
					while (!tempPile.empty()) {
						game.drawPile.push(tempPile.pop());
						}
					
					tempLocString="";
					redrawStacks();
					redrawDraw();
					updateActiveCards();
					return;
					//break;
				case "finalStack":
					System.out.println("reached restack finalPile");
					
					while (!tempPile.empty()) {
						game.listOfFinalStacks.get(stackIndex).push(tempPile.pop());
						}
					
					tempLocString="";
					updateActiveCards();
					redrawStacks();
					redrawDraw();
					return;
					//break;
				default: System.out.println("Switch fail"); break;
			}
		}
			
		System.out.print(   "failed to return cards");
		
		
		
		
		
		
		System.out.println("Grabbing a new card...");
		
		if(arg0.getComponent() instanceof Card) {
			Card cardSel = (Card)arg0.getComponent();
			if(!(cardSel.value==54) && !(cardSel.value==101) && !(cardSel.value==401) && !(cardSel.value==402) && !(cardSel.value==403) && !(cardSel.value==404)) {
			if(cardSel.isReversed) { redrawDraw(); redrawStacks(); return; }
			if(!tempPile.empty()) { System.out.println("ranMousePressed with not empty tempPile"); }
			JLayeredPane parentPile  = (JLayeredPane)cardSel.getParent();
			int stackIndex=0;
			Stack<Card> evenMoreTempStack;
			stackIndex= Integer.valueOf(game.wC_Decomp(game.whereCard(cardSel),2));
			
			
				
			
			switch (game.wC_Decomp(game.whereCard(cardSel),1)) {
				case "mainStack":
					System.out.println(" \n\n ---- is mainstack grab ---");
					//record where cards came from just incase not valid move
					tempLocString="mainStack " + Integer.toString(stackIndex) + " "  + game.listOfStacks.get(stackIndex).indexOf(cardSel);
					
					//put moving cards into temp Pile
					tempPile = cutStack(game.listOfStacks.get(stackIndex),cardSel);
					System.out.println(tempPile);
					
					updateActiveCards();
					redrawStacks();
					break;
				case "drawPile":
					//record where cards came from just incase not valid move
					tempLocString="drawPile 0 " + game.drawPile.indexOf(game.drawPile.peek());
					
					//put moving cards into temp Pile
					tempPile.push(game.drawPile.pop());
					
					redrawDraw();
					updateActiveCards();
					redrawStacks();
					break;
				case "finalStack":
					//record where cards came from just incase not valid move
					tempLocString="finalStack " + Integer.toString(stackIndex) +" "  + game.listOfFinalStacks.get(stackIndex).indexOf(cardSel);
					
					//put moving cards into temp Pile
					tempPile = cutStack(game.listOfFinalStacks.get(stackIndex),cardSel);
					updateActiveCards();
					redrawStacks();
					break;
				default: System.out.println("Switch fail"); break;
			}
			

			
			System.out.println(   "tempPile: " + tempPile);
			

		}
		
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {

		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
