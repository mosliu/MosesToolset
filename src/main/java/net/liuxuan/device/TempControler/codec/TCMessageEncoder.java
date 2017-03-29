package net.liuxuan.device.TempControler.codec;

import net.liuxuan.device.TempControler.packet.TCOutMessage;
import net.liuxuan.device.base.BasePacketEncoder;


/**
 * 温控表's MessageEncoder
 *
 * @version        1.0.0.0, Jul 29, 2011
 * @author         Moses
 */
public class TCMessageEncoder<T extends TCOutMessage> extends BasePacketEncoder<T> {

    /**
     * 
     */
    public TCMessageEncoder() {
        //super(CommonParameters.PACKET_HEADER,CommonParameters.PACKET_TAIL);//添加包头
    }
}
