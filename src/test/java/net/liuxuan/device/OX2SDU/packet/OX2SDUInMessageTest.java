/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.liuxuan.device.OX2SDU.packet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Moses
 */
public class OX2SDUInMessageTest {
    
    public OX2SDUInMessageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of defaultPacket method, of class OX2SDUInMessage.
     */
    @Test
    public void testDefaultPacket() {
        System.out.println("defaultPacket");
        byte[] _content = null;
        OX2SDUInMessage instance = new OX2SDUInMessage();
        instance.defaultPacket(_content);
        // TODO review the generated test code and remove the default call to fail.
        byte[] header = {20, 20};
        byte[] content = {21, 21, 21, 21};
        byte[] tail = {22, 22};
        byte[] all = {20, 20, 21, 21, 21, 21, 22, 22};
        OX2SDUInMessage p = new OX2SDUInMessage(all);
        System.out.println(p);
        p = new OX2SDUInMessage();
        p.setAssemble(true);
        System.out.println(p);
        p.setContent(content);
        System.out.println(p);
        p.setHeaderbs(header);
        System.out.println(p);
        p.setTailbs(tail);
        System.out.println(p);
    }
    
}
