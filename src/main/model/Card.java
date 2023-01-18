package model;


import javax.swing.*;



//represents a playing card, containing the numeric value associated, and it's suit
public class Card {

    private final Integer value;
    private final Integer suitValue;
    private ImageIcon display;
    private final Integer valuePriority;

    //REQUIRES: no parameters may be left empty, value must be between 1-13, suit value must be between 1-4
    //EFFECTS: new playing card is made with value and suitvalue set respectively, corresponding display is found and stored
    public Card (int value, int suitvalue){

        this.value = value;
        this.suitValue = suitvalue;
        if (value == 2 || value == 1){
            valuePriority = 13+value;
        }else{
            valuePriority = value;
        }

        String filePath = "./data/card_"+value+"_"+suitvalue+".png";

        display = new ImageIcon(filePath);


    }


    //EFFECTS: returns a cards value
    public Integer getValue(){
        return value;
    }


    //EFFECTS: returns a cards suit
    public Integer getSuitValue(){
        return suitValue;
    }

    //EFFECTS: returns the display image of the card
    public ImageIcon getDisplay(){
        return display;
    }

    //EFFECTS: returns the valuePriority
    public Integer getValuePriority(){
        return valuePriority;
    }

    //override allows child class to provide a particular implementation of a method declared by the parents
    @Override
    public boolean equals(Object o){
        if (this == o){ //case if both objects are same instance
            return true;
        }
        if( o == null || this == null){ //case if either objects are null
            return false;
        }
        if (this.getClass() != o.getClass()){ //case if objects are different classes
            return false;
        }
        Card object = (Card) o;

        if(this.value == object.value && this.suitValue == object.suitValue){ // case where both objects are clones
            return true;
        }else {
            return false;
        }
    }


//    //EFFECTS: returns true if card is stronger suit than c, else false
//    public Boolean isSuitGreater(Card c){
//        return suitValue>c.getSuitValue();
//    }

//    //EFFECTS: used to determine outcome when only one
//    public Boolean singlesOutcome(Card c){
//        if(isSuitGreater(c) && this.valuePriority == c.valuePriority){
//            return true;
//        } else if (this.valuePriority > c.valuePriority){
//            return true;
//        }else{
//            return false;
//        }
//    }
}
