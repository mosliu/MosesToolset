package net.liuxuan.device.TempControler.codec;

import net.liuxuan.device.TempControler.packet.TCInMessage;
import net.liuxuan.device.TempControler.packet.TCOutMessage;
import net.liuxuan.device.base.AbstractPacket;
import net.liuxuan.device.base.BasePacketDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

/**
 * OxygenTransducer's MessageDecoder
 *
 * @version        1.0.0.0, Jul 29, 2011
 * @author         Moses
 */
public class TCMessageDecoder extends BasePacketDecoder {

    public TCMessageDecoder() {
    }

    @Override
    public void initSamplePkt() {
        this.setSamplePkt(new TCInMessage());
    }

    @Override
    public void initRtnPkt() {
        this.setRtnPkt(new TCOutMessage());
    }
}
