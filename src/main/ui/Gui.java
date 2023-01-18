package ui;

import model.Card;
import model.CardButton;
import model.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {

    int turn = 1;
    JFrame welcomeFrame;
    JFrame playMenu;
    JFrame transitionFrame;

    JPanel currentStatePanel;
    JPanel yourCardsPanel;
    JPanel functionSelectionPanal;

    Deck playerOneDeck;
    Deck playerTwoDeck;
    Deck currentPlayingDeck;
    Deck attackDeck;

    //Card testCard;
    ImageIcon Gamelogo = new ImageIcon("./data/Game_icon3.png");
    ImageIcon startDoggoIcon = new ImageIcon("./data/Game_icon2.jpg");
    ImageIcon checkDoggoIcon = new ImageIcon("./data/Game_icon4.png");
    ImageIcon passDoggo = new ImageIcon("./data/Game_icon5.png");

    ImageIcon victoryDoggo = new ImageIcon("./data/Game_icon.jpg");

    public Gui(){
        welcomeMenu();
    }

    //EFFECTS: creates the first menu page when starting program, with a button to start a game
    private void welcomeMenu(){

        CardButton startDoggoButton = new CardButton(); // cardButton class is working, even with test card
        startDoggoButton.setBounds(175,30,250,250);
        startDoggoButton.setIcon(fitIconToButton(startDoggoIcon,startDoggoButton.getWidth(),startDoggoButton.getHeight()));
        startDoggoButton.setActionCommand("startNewGame"); //sets how the action listener will identify the button
        startDoggoButton.addActionListener(this); //addActionListener ensures the button is included in the tracked events of the listener. "this" refers to the class object, in this case the gui.

        JLabel welcomeLabel = new JLabel(); //initialize new jlabel
        welcomeLabel.setText("Welcome to Biggie2, click the dog above to begin!"); //adds text to jlabel
        welcomeLabel.setFont(new Font("SimSun", Font.PLAIN,20)); //changes font of jlabel
        welcomeLabel.setVerticalAlignment(JLabel.CENTER); //sets where text & image will be within jlabel
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER); // '' ''
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

        JButton resetAttackButton = new JButton ("Reset Attack");
        resetAttackButton.setActionCommand("reset");
        resetAttackButton.addActionListener(this);
        JButton executeAttackButton = new JButton ("Execute Attack");
        executeAttackButton.setActionCommand("execute");
        executeAttackButton.addActionListener(this);
        JButton passButton = new JButton("Pass");
        passButton.setActionCommand("pass");
        passButton.addActionListener(this);

        currentStatePanel = new JPanel();
        currentStatePanel.setBackground(new Color (85,145,95)); // changes color of panel, used to visualize panel, commented out after use
        currentStatePanel.setBounds(0,0,600,200);

        yourCardsPanel = new JPanel();
        yourCardsPanel.setBounds(0,200,600,150);
        yourCardsPanel.setBackground(new Color(20,34,50));

        functionSelectionPanal = new JPanel();
        functionSelectionPanal.setBounds(0,350,600,100);
        functionSelectionPanal.add(resetAttackButton);
        functionSelectionPanal.add(executeAttackButton);
        functionSelectionPanal.add(passButton);

        playMenu = new JFrame("Make your move!");
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

    public void transitionScreen( String message, ImageIcon doggoPicture, String command){

        CardButton checkDoggoButton = new CardButton(); // cardButton class is working, even with test card
        checkDoggoButton.setBounds(175,30,250,250);
        checkDoggoButton.setIcon(fitIconToButton(doggoPicture,checkDoggoButton.getWidth(),checkDoggoButton.getHeight()));
        checkDoggoButton.setActionCommand(command); //sets how the action listener will identify the button
        checkDoggoButton.addActionListener(this);

        JLabel checkLabel = new JLabel(); //initialize new jlabel
        checkLabel.setText(message); //adds text to jlabel
        checkLabel.setFont(new Font("SimSun", Font.PLAIN,20)); //changes font of jlabel
        checkLabel.setVerticalAlignment(JLabel.CENTER); //sets where text & image will be within jlabel
        checkLabel.setHorizontalAlignment(JLabel.CENTER); // '' ''
        checkLabel.setBounds(50,300,500,100); // manually set where label is within frame, and label dimensions

        JPanel switchPanel = new JPanel();
        switchPanel.setLayout(null); // need this to manually move button
        switchPanel.setBackground(new Color (255,231,193)); // changes color of panel, used to visualize panel, commented out after use
        switchPanel.setBounds(0,0,600,300);
        switchPanel.add(checkDoggoButton);

        transitionFrame = new JFrame();
        transitionFrame.setSize(600,450);
        transitionFrame.setLocationRelativeTo(null);
        transitionFrame.setResizable(false);
        transitionFrame.setLayout(null);
        transitionFrame.add(checkLabel);
        transitionFrame.add(switchPanel);
        transitionFrame.setIconImage(Gamelogo.getImage());
        transitionFrame.setUndecorated(true);
        transitionFrame.setVisible(true);
    }

    public ImageIcon fitIconToButton(ImageIcon icon, int Width, int Height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void addDeckToPanal(JPanel j, Deck d){
        cleanJPanel(j);
        for (Card c: d.getDeckComposition()){
            CardButton cardb = new CardButton(c);
            cardb.setSize(40,57);
            cardb.setIcon(fitIconToButton(c.getDisplay(),cardb.getWidth(),cardb.getHeight()));
            cardb.setOpaque(false);
            cardb.setContentAreaFilled(false);
            cardb.setBorderPainted(false);
            cardb.setActionCommand("cardClick");
            cardb.addActionListener(this);
            j.add(cardb);

        }
    }

    public void initializeDecks(){
        currentPlayingDeck = new Deck();
        attackDeck = new Deck ();
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
        addDeckToPanal(yourCardsPanel,playerOneDeck);
        yourCardsPanel.revalidate();

    }

    public void modifyAttackdeck(CardButton b){
        attackDeck.addCard(b.getButtonsCard());
        b.setEnabled(false);
    }

    public void resetAttack(){
        attackDeck.clearDeck();
        if(turn == 1) {
            addDeckToPanal(yourCardsPanel, playerOneDeck);
        } else{
            addDeckToPanal(yourCardsPanel, playerTwoDeck);
        }
    }

    public void executeAttack(){
        if((attackDeck.checkValueConsistent() && (attackDeck.checkDecksSameSize(currentPlayingDeck)||currentPlayingDeck.getDeckComposition().isEmpty()))) {
            if(currentPlayingDeck.getDeckComposition().isEmpty()||currentPlayingDeck.fightOutcome(attackDeck)) {
                currentPlayingDeck.clearDeck();
                currentPlayingDeck.overhaulDeckComp(attackDeck.getDeckComposition());
                transitionScreen("<html>Hi! I'm the no cheating guard dog. Hand the device to the other player, and give me a pet (click) once you're done. (I'm watching you:3) <html>",
                        checkDoggoIcon, "switchplayer");
                switchTurn();
                attackDeck.clearDeck();
                victoryCheck();
            }
        }
    }

    public void cleanJPanel(JPanel j){
        j.removeAll();
        j.revalidate();
        j.repaint();
    }
    public void switchTurn(){
        if(turn == 1){
            turn = 2;
            playerOneDeck.removePlayedCards(attackDeck);
            addDeckToPanal(currentStatePanel,currentPlayingDeck);
            addDeckToPanal(yourCardsPanel,playerTwoDeck);
        }else {
            turn = 1;
            playerTwoDeck.removePlayedCards(attackDeck);
            addDeckToPanal(currentStatePanel,currentPlayingDeck);
            addDeckToPanal(yourCardsPanel,playerOneDeck);
        }
    }

    public void passTurn(){
        transitionScreen("<html>You could not beat your opponent's attack:( Hand the device to the other player, and give me a pet (click) once you're done! <html>", passDoggo, "switchplayer");
        currentPlayingDeck.clearDeck();
        attackDeck.clearDeck();
        switchTurn();
    }

    public void victoryCheck(){
        if (playerOneDeck.getDeckComposition().isEmpty()){
            transitionScreen("<html>Congratulations player 1, you've won! You deserve some treats now! Give me a pet (click) when you want to leave, play again soon!<html>", victoryDoggo, "endgame");
        }
        if (playerTwoDeck.getDeckComposition().isEmpty()){
            transitionScreen("<html>Congratulations player 1, you've won! You deserve some treats now! Give me a pet (click) when you want to leave, play again soon!<html>", victoryDoggo,"endgame");
        }
    }


    // sort of like a jump table for button events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("startNewGame")) {
            initializeGame();
            welcomeFrame.dispose();
        } else if (e.getActionCommand().equals ("cardClick")){
            CardButton cardclicked = (CardButton) e.getSource();
            modifyAttackdeck(cardclicked);
        } else if (e.getActionCommand().equals("reset")){
            resetAttack();
        } else if (e.getActionCommand().equals("execute")){
            executeAttack();
        } else if (e.getActionCommand().equals("pass")){
            passTurn();
        }else if (e.getActionCommand().equals("switchplayer")){
            transitionFrame.dispose();
        }else if(e.getActionCommand().equals("endgame")){
            System.exit(0);
        }
    }
}
