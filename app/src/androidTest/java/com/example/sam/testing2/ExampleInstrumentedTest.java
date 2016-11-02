package com.example.sam.testing2;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.sam.testing2", appContext.getPackageName());
    }
}

class SplashScreenTest {
    @Test
    public void splashScreenLoadingTime() throws Exception {
        //assertEquals(SplashScreen.getAnimationTime(), 3000);
    }
}

/*class pointValidator {
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
}*/
