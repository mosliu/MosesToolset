package net.liuxuan.device.OX2SDU.codec;

import net.liuxuan.device.OX2SDU.packet.OX2SDUInMessage;
import net.liuxuan.device.base.BasePacket;
import net.liuxuan.device.base.BasePacketDecoder;

/**
 * OxygenTransducer's MessageDecoder
 *
 * @version 1.0.0.0, Jul 29, 2011
 * @author Moses
 */
public class OX2SDUMessageDecoder extends BasePacketDecoder {

    public OX2SDUMessageDecoder() {
    }

    @Override
    public void initSamplePkt() {
        this.setSamplePkt(new OX2SDUInMessage());
    }

    @Override
    public void initRtnPkt() {
        this.setRtnPkt(new OX2SDUInMessage());
    }
}
