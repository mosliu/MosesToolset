package net.liuxuan.device.OX2SDU.codec;

//~--- non-JDK imports --------------------------------------------------------


import net.liuxuan.device.OX2SDU.packet.OX2SDUOutMessage;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * OxygenTransducer's ProtocolCodecFactory
 *
 * @version        1.0.0.0, Jul 29, 2011
 * @author         Moses
 */
public class OX2SDUProtocolCodecFactory extends DemuxingProtocolCodecFactory {

    /**
     * Constructs ...
     *
     */
    public OX2SDUProtocolCodecFactory() {
        super.addMessageDecoder(OX2SDUMessageDecoder.class);
        
        super.addMessageEncoder(OX2SDUOutMessage.class, OX2SDUMessageEncoder.class);
    }
}
