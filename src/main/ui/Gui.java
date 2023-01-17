package ui;

import model.Card;
import model.CardButton;
import model.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {

    JFrame welcomeFrame;
    JFrame playMenu;

    JPanel currentStatePanel;
    JPanel yourCardsPanel;

    Deck playerOneDeck;
    Deck playerTwoDeck;
    Deck currentPlayingDeck;

    //Card testCard;
    ImageIcon Gamelogo = new ImageIcon("./data/Game_icon3.png");
    ImageIcon startDoggoIcon = new ImageIcon("./data/Game_icon2.jpg");

    public Gui(){
        welcomeMenu();
    }

    //EFFECTS: creates the first menu page when starting program, with a button to start a game
    private void welcomeMenu(){

        //Card testCard = new Card(10,3);
        CardButton startDoggoButton = new CardButton(); // cardButton class is working, even with test card
        startDoggoButton.setBounds(175,30,250,250);
        startDoggoButton.setIcon(fitIconToButton(startDoggoIcon,startDoggoButton.getWidth(),startDoggoButton.getHeight()));
        //startDoggoButton.setBorder();
        startDoggoButton.setActionCommand("startNewGame"); //sets how the action listener will identify the button
        startDoggoButton.addActionListener(this); //addActionListener ensures the button is included in the tracked events of the listener. "this" refers to the class object, in this case the gui.

        //Border testborder = BorderFactory.createLineBorder(Color.blue,2);




        JLabel welcomeLabel = new JLabel(); //initialize new jlabel
        welcomeLabel.setText("Welcome to Biggie2, click the dog above to begin!"); //adds text to jlabel
        welcomeLabel.setFont(new Font("SimSun", Font.PLAIN,20)); //changes font of jlabel
        welcomeLabel.setVerticalAlignment(JLabel.CENTER); //sets where text & image will be within jlabel
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER); // '' ''
        //welcomeLabel.setBorder(testborder); // used to visualize where label is in frame, removed after use
        welcomeLabel.setBounds(50,300,500,100); // manually set where label is within frame, and label dimensions

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(null); // need this to manually move button
        welcomePanel.setBackground(new Color (255,231,193)); // changes color of panel, used to visualize panel, commented out after use
        welcomePanel.setBounds(0,0,600,300);
        welcomePanel.add(startDoggoButton);

        welcomeFrame = new JFrame("Welcome to BIG 2!");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(600,450);
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setResizable(false);
        welcomeFrame.setIconImage(Gamelogo.getImage());
        welcomeFrame.setLayout(null); // required to be set to null if you want to manually set label (via setBounds) in fram
        welcomeFrame.setVisible(true);
        welcomeFrame.add(welcomeLabel);
        welcomeFrame.add(welcomePanel);



    }

    private void activePlayScreen(){

        JPanel currentStatePanel = new JPanel();
        //currentStatePanel.setLayout(null); // need this to manually move button
        currentStatePanel.setBackground(new Color (85,145,95)); // changes color of panel, used to visualize panel, commented out after use
        currentStatePanel.setBounds(0,0,600,200);

        JPanel yourCardsPanel = new JPanel();
        yourCardsPanel.setBounds(0,200,600,150);
        yourCardsPanel.setBackground(new Color(20,34,50));
        addDeckToPanal(yourCardsPanel,playerOneDeck);

        JPanel functionSelectionPanal = new JPanel();
        functionSelectionPanal.setBounds(0,350,600,100);


        playMenu = new JFrame("Make your move");
        playMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playMenu.setSize(600,450);
        playMenu.setLocationRelativeTo(null);
        playMenu.setResizable(false);
        playMenu.setIconImage(Gamelogo.getImage());
        playMenu.setLayout(null); // required to be set to null if you want to manually set label (via setBounds) in fram
        playMenu.setVisible(true);
        playMenu.add(yourCardsPanel);
        playMenu.add(currentStatePanel);
        playMenu.add(functionSelectionPanal);
    }

    public ImageIcon fitIconToButton(ImageIcon icon, int Width, int Height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void addDeckToPanal(JPanel j, Deck d){
        for (Card c: d.getDeckComposition()){
            CardButton cardb = new CardButton(c);
            cardb.setSize(40,57);
            cardb.setIcon(fitIconToButton(c.getDisplay(),cardb.getWidth(),cardb.getHeight()));
            cardb.setOpaque(false);
            cardb.setContentAreaFilled(false);
            cardb.setBorderPainted(false);
            j.add(cardb);

        }
    }

    public void initializeDecks(){
        currentPlayingDeck = new Deck();
        Deck originalDeck = new Deck();
        for (int val = 1; val <= 13; val++){
            for (int suit = 1; suit <=4; suit++){
                Card ogCard = new Card(val,suit);
                originalDeck.addCard(ogCard);
            }
        }
        originalDeck.randomizeDeck();
        playerOneDeck = new Deck();
        playerOneDeck.overhaulDeckComp(originalDeck.splitDeck(1));
        playerTwoDeck = new Deck();
        playerTwoDeck.overhaulDeckComp(originalDeck.splitDeck(2));

    }

    public void initializeGame(){
        initializeDecks();

        activePlayScreen();



    }

    // sort of like a jump table for button events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("startNewGame")) {
            initializeGame();
        }
    }
}
