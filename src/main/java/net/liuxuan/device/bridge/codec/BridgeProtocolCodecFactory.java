/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.liuxuan.device.bridge.codec;

import net.liuxuan.device.Fluke45.codec.Fluke45MessageDecoder;
import net.liuxuan.device.Fluke45.codec.Fluke45MessageEncoder;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 *
 * @author Moses
 */
public class BridgeProtocolCodecFactory extends DemuxingProtocolCodecFactory{
    /**
     * Constructs ...
     *
     */
    public BridgeProtocolCodecFactory() {
        super.addMessageDecoder(BridgeDecoder.class);
        super.addMessageEncoder(byte[].class, BridgeEncoder.class);
    }
}
