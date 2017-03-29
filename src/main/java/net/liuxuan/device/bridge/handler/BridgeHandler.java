/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.device.bridge.handler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.liuxuan.device.base.RS232Connector;
import net.liuxuan.utils.BytePlus;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author Moses
 */
public class BridgeHandler extends IoHandlerAdapter {
    private static long max = 0;
    private static long sendtime;
    private static long recerivetime;
    
    
    public String name;
    public File outfile;
    private FileWriter fw;
    private RS232Connector dest=null;
    
    public void initfilelog() {
        try {
            fw = new FileWriter(outfile, true);

        } catch (IOException ex) {
            Logger.getLogger(BridgeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void teminatefilelog() {
        if (fw != null) {
            try {
//                FileWriter fw = new FileWriter(outfile, true);
                fw.flush();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(BridgeHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        // Empty handler
//        System.out.println(String.format("收到的时间戳%d",System.currentTimeMillis()));
        recerivetime = System.currentTimeMillis();
        byte[] bs = (byte[])message;
        String logstr = BytePlus.byteArray2String(bs);
        if (fw != null) {
            fw.append(logstr);
        }
        System.out.println(logstr);
        if(dest!=null){
            WriteFuture wf = dest.getReceiverSession().write(message);
            wf.awaitUninterruptibly();
        }
    }

    public void messageSent(IoSession session, Object message) throws Exception {
        sendtime = System.currentTimeMillis();
        long temp = sendtime-recerivetime;
        max = max>temp?max:temp;
//        System.out.println(String.format("发出的时间戳%d",System.currentTimeMillis()));
        // Empty handler
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("MAX:"+max);
        System.out.println("session closed");
//        saveData();
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("session idled:" + status.toString());
    }

    public RS232Connector getDest() {
        return dest;
    }

    public void setDest(RS232Connector dest) {
        this.dest = dest;
    }
}
