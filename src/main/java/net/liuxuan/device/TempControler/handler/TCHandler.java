package net.liuxuan.device.TempControler.handler;

//~--- non-JDK imports --------------------------------------------------------
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

//~--- JDK imports ------------------------------------------------------------

import net.liuxuan.device.OX2SDU.packet.OX2SDUInMessage;
import net.liuxuan.utils.BytePlus;

/**
 * 温控表 信号处理
 *
 * @version        1.0.0.0, 2011/07/29
 * @author         Moses
 */
public class TCHandler extends IoHandlerAdapter {

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

    private int getUnsignedByte(int k) {
        k = k < 0 ? k + 256 : k;
        return k;
    }

    private int getUnsignedLittleEndianByteShort(int below,int high) {
        return getUnsignedByte(below)+getUnsignedByte(high)*256;
//        below = below < 0 ? below + 256 : below;
//        return below;
    }
//  byte[] k = BytePlus.int2bytes(r.nextInt(0x77777777));
//
//              // 传感器数据
//              System.arraycopy(k, 0, outmsgcontent, 6, 4);
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("session closed");
//        saveData();
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("session idled:" + status.toString());
    }

//    /**
//     * 保存VirtualDevice机器数据
//     */
//    public void saveData() {
//        try {
//            ObjectOutputStream out = null;
//
//            out = new ObjectOutputStream(new FileOutputStream(STORE_FILE));
//            out.writeObject(device);
//        } catch (IOException ex) {
//            Logger.getLogger(TCHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void loadData() {
//        try {
//            File f = new File(STORE_FILE);
//
//            if (f.exists()) {
//                FileInputStream fis = new FileInputStream(f);
//                ObjectInputStream in = new ObjectInputStream(fis);
//                device = (OxygenTransducer) in.readObject();
////                device.setCellpresure_high(waittime);
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(TCHandler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(TCHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
