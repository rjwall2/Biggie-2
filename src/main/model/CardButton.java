package model;

import javax.swing.*;

public class CardButton extends JButton {

    private Card card;
    private JButton button;

    public CardButton(){
        super(); //calls the super class constructor (in this case JButton constructor
    }

    public CardButton(Card c){
        super();
        card = c;
    }

    public Card getButtonsCard(){
        return this.card;
    }

}
