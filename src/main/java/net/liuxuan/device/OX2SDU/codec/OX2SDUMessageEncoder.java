package net.liuxuan.device.OX2SDU.codec;

import net.liuxuan.device.OX2SDU.packet.OX2SDUOutMessage;
import net.liuxuan.device.base.BasePacketEncoder;

/**
 * OxygenTransducer's MessageEncoder
 *
 * @version        
 * @author         Moses
 * @param <T>
 */
public class OX2SDUMessageEncoder<T extends OX2SDUOutMessage> extends BasePacketEncoder<T> {

    /**
     * 
     */
    public OX2SDUMessageEncoder() {
        //super(CommonParameters.PACKET_HEADER,CommonParameters.PACKET_TAIL);//添加包头
    }
    
}
