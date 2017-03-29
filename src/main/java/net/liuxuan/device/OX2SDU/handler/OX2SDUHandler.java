package net.liuxuan.device.OX2SDU.handler;

//~--- non-JDK imports --------------------------------------------------------

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

//~--- JDK imports ------------------------------------------------------------

import net.liuxuan.device.OX2SDU.packet.OX2SDUInMessage;
import net.liuxuan.utils.BytePlus;

/**
 * OxygenTransducer's 信号处理
 *
 * @version        1.0.0.0, 2011/07/29
 * @author         Moses
 */
public class OX2SDUHandler extends IoHandlerAdapter {

    /**
     * 构造函数
     *
     */
    public OX2SDUHandler() {
        
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(session, cause);
    }


    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        OX2SDUInMessage msg = (OX2SDUInMessage) message;
        byte[] incontent = msg.getPktbs();
        System.out.println(BytePlus.byteArray2String(incontent));
        
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("session closed");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("session idled:" + status.toString());
    }

}
