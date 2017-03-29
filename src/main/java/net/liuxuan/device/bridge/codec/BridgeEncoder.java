/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.device.bridge.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

/**
 *
 * @author Moses
 */
public class BridgeEncoder implements MessageEncoder {

    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        byte[] pktbyte = (byte[]) message;
        IoBuffer buf = IoBuffer.allocate(pktbyte.length);
        buf.setAutoExpand(true);
        buf.put(pktbyte);
        buf.flip();
        out.write(buf);
    }

}
