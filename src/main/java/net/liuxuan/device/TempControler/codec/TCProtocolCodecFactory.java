package net.liuxuan.device.TempControler.codec;

//~--- non-JDK imports --------------------------------------------------------


import net.liuxuan.device.TempControler.packet.TCOutMessage;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * OxygenTransducer's ProtocolCodecFactory
 *
 * @version        1.0.0.0, Jul 29, 2011
 * @author         Moses
 */
public class TCProtocolCodecFactory extends DemuxingProtocolCodecFactory {

    /**
     * Constructs ...
     *
     */
    public TCProtocolCodecFactory() {
        super.addMessageDecoder(TCMessageDecoder.class);
        
        super.addMessageEncoder(TCOutMessage.class, TCMessageEncoder.class);
    }
}
