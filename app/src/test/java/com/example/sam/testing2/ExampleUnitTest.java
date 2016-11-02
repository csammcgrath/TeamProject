package com.example.sam.testing2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}

class checkPassword {
    public void isInteger(String password) throws Exception {
        assertEquals(password, Integer.parseInt(password));
    }
}

class checkZip {
    public void isInteger(String zip) throws Exception {
        assertEquals(zip, Integer.parseInt(zip));
    }
}

class pointValidator {
    @Test
    public Boolean pointNegative(int point) {
        if (point < 0) {
            return false;
        } else {
            return true;
        }
    }

    @Test
    public Boolean isPointInteger(int point) {
        if (point == Math.floor(point)) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public Boolean isPointResetProperly(int point) {
        if (point == 0) {
            return true;
        } else {
            return false;
        }
    }
}