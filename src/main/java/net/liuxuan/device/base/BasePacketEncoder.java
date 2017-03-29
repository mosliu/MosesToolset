/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.device.base;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Moses
 */
public abstract class BasePacketEncoder<T extends BasePacket>
        implements MessageEncoder<T> {

    /**
     * 使用默认初始化参数
     */
    public BasePacketEncoder() {
        
    }

    public void encode(IoSession session, T message, ProtocolEncoderOutput out)
            throws Exception {
        if(message==null){
            return;
        }
        int len = message.getLength();

        IoBuffer buf = IoBuffer.allocate(len);
        buf.setAutoExpand(true); // Enable auto-expand for easier encoding

        // Encode a bodyc
        encodeBody(session, message, buf);

        buf.flip();
        out.write(buf);
    }

    protected void encodeBody(IoSession session, T message,
            IoBuffer out){
        out.put(message.getPktbs());
    }
}
