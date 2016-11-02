package com.example.sam.testing2;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PointUnitTest {
    @Test
    public void addPointsCorrectly() throws Exception {
        UserInfo user = new UserInfo("yes", "yes", "yes", "yes", 0);
        user.addPoints(5);
        Assert.assertEquals(5, user.getPoint());
        Assert.assertTrue(user.getPoint() >= 0);
    }
}