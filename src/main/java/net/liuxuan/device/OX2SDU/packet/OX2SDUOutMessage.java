package net.liuxuan.device.OX2SDU.packet;

import net.liuxuan.device.base.BasePacket;

/**
 * OX2SDU Parser's Send To Sensor Message
 *
 * @version 
 * @author Moses
 */
public class OX2SDUOutMessage extends BasePacket {

    private static final int PACKET_LEN = 1;
    
    public OX2SDUOutMessage() {
    }

    public OX2SDUOutMessage(byte[] _content) {
        super(_content);
    }

    /**
     * 重置为默认包体内容
     *
     * @param _content 重置时的content内容。如传入null则初始化为new
     * byte[CommonParameters.PACKET_BODY_LEN];
     */
    protected void defaultPacket(byte[] _content) {
        if (_content == null) {
            this.pktbs = new byte[PACKET_LEN];
        } else {
            this.pktbs = _content;
        }
    }

}
