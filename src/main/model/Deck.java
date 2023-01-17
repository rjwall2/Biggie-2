package model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.shuffle;

public class Deck {

    private List<Card> deckComposition;

    //EFFECTS: constructs an empty card deck
    public Deck(){
        deckComposition = new ArrayList<>();
    }

    //EFFECTS: returns the deck composition
    public List<Card> getDeckComposition(){
        return deckComposition;
    }


    //MODIFIES: this
    //EFFECTS: adds a card to a deck
    public void addCard(Card c){
        deckComposition.add(c);
    }


    //REQUIRES: c must be a card already contained in the deck
    //MODIFIES: this
    //EFFECTS: removes a card from a deck
    public void removeCard(Card c){
        deckComposition.remove(c);
    }


    //EFFECTS: returns true if all cards in a deck have the same value, otherwise returns false
    public Boolean checkValueConsistent(){

        Card deckRepresentative = deckComposition.get(0);
        int commonValue = deckRepresentative.getValue();
        Boolean consistent;
        for(Card c:deckComposition){
            consistent = c.getValue() == commonValue;
            if (consistent == false){
                return false;
            }
        }
        return true;
    }

    //EFFECTS: returns the highest suit value present in a deck
    public int getDecksHighestSuit(){
        int highestSuit = 0;
        for (Card c: this.getDeckComposition()){
            if (c.getSuitValue() > highestSuit){
                highestSuit = c.getSuitValue();
            }
        }
        return highestSuit;
    }
//EFFECTS: returns true if two decks are the same size, else returns false
    public Boolean checkDecksSameSize(Deck d){
        if(this.deckComposition.size() == d.deckComposition.size()){
            return true;
        }else{
            return false;
        }
    }

    //EFFECTS: determines which deck wins a dual following the rules of big2
    public Deck fightOutcome (Deck d){
        int deckValue1 = this.deckComposition.get(0).getValuePriority();
        int deckValue2 = d.deckComposition.get(0).getValuePriority();

        if (deckValue1 == deckValue2){
            if(this.getDecksHighestSuit()>d.getDecksHighestSuit()){
                return this;
            }else{
                return d;
            }

        }

        if (deckValue1 > deckValue2){
            return this;
        } else{
            return d;
        }


    }

    public void randomizeDeck(){
        shuffle(this.deckComposition);  //shuffle returns void, but changes the deck
    }

    public List<Card> splitDeck(int numOfPlayers){
        int offset = (numOfPlayers-1)*13;
        return this.deckComposition.subList(0+offset,13+offset);
    }

    public void overhaulDeckComp(List<Card> lc){
        this.deckComposition.addAll(lc);
    }
}
