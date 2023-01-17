package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private Deck testDeck;
    private Deck testDeck2;
    private Card testCard1;
    private Card testCard2;
    private Card testCard3;
    private Card testCard4;
    private Card testCard5;
    private Card testCard6;

    @BeforeEach
    public void beforeEach (){
        testDeck = new Deck();
        testDeck2 = new Deck();
        testCard1 = new Card (5,3);
        testCard2 = new Card (5,4);
        testCard3 = new Card(5,1);
        testCard4 = new Card (10,1);
        testCard5 = new Card (5,2);
        testCard6 = new Card (10,2);
    }

    @Test
    public void deckConstructorTest(){

        List<Card> emptyComposition = testDeck.getDeckComposition();
        assertEquals(0,emptyComposition.size());
    }

    @Test
    public void addCardTest() {
        testDeck.addCard(testCard1);

        assertEquals(1,testDeck.getDeckComposition().size());
        assertEquals(testCard1, testDeck.getDeckComposition().get(0));
    }

    @Test
    public void removeCardTest() {
        testDeck.addCard(testCard1);
        testDeck.addCard(testCard2);
        testDeck.removeCard(testCard1);

        assertEquals(1,testDeck.getDeckComposition().size());
        assertEquals(testCard2,testDeck.getDeckComposition().get(0));

    }

    @Test
    public void checkValueConsistentTest() {
        testDeck.addCard(testCard1);
        assertTrue(testDeck.checkValueConsistent());

        testDeck.addCard(testCard2);
        assertTrue(testDeck.checkValueConsistent());

        testDeck.addCard(testCard3);
        assertTrue(testDeck.checkValueConsistent());

        testDeck.addCard(testCard4);
        assertFalse(testDeck.checkValueConsistent());

        testDeck.removeCard(testCard4);
        assertTrue(testDeck.checkValueConsistent());


    }

    @Test
    public void getDecksHighestSuitTest(){
        testDeck.addCard(testCard1);
        testDeck.addCard(testCard3);

        assertEquals(3,testDeck.getDecksHighestSuit());

        testDeck.addCard(testCard2);

        assertEquals(4,testDeck.getDecksHighestSuit());
    }

    @Test
    public void checkDecksSameSizeTest(){
        testDeck.addCard(testCard1);
        testDeck2.addCard(testCard2);

        assertTrue(testDeck.checkDecksSameSize(testDeck2));

        testDeck.addCard(testCard3);

        assertFalse(testDeck.checkDecksSameSize(testDeck2));
    }

    @Test
    public void fightOutcomeTest(){
        testDeck.addCard(testCard1);
        testDeck.addCard(testCard3);

        testDeck2.addCard(testCard2);
        testDeck2.addCard(testCard5);

        assertEquals(testDeck2, testDeck.fightOutcome(testDeck2));
        assertEquals(testDeck2, testDeck2.fightOutcome(testDeck));

        testDeck2.removeCard(testCard5);
        testDeck2.removeCard(testCard2);
        testDeck2.addCard(testCard4);
        testDeck2.addCard(testCard6);

        assertEquals(testDeck2, testDeck.fightOutcome(testDeck2));
        assertEquals(testDeck2, testDeck2.fightOutcome(testDeck));
    }
}