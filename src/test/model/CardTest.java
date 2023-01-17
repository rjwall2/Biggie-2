package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test
    public void cardConstructorTest() {
        Card testCard = new Card(2, 3);

        assertEquals(2, testCard.getValue());
        assertEquals(3, testCard.getSuitValue());
        assertNotNull(testCard.getDisplay());
        assertEquals(15,testCard.getValuePriority());
    }

//    @Test
//    public void isSuitGreaterTest(){
//        Card testCard1 = new Card(10,2);
//        Card testCard2 = new Card (8, 2);
//        Card testCard3 = new Card (4,1);
//        Card testCard4 = new Card (3,4);
//
//        assertFalse(testCard1.isSuitGreater(testCard2));
//        assertTrue(testCard1.isSuitGreater(testCard3));
//        assertFalse(testCard1.isSuitGreater(testCard4));
//
//    }
}