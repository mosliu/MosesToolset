package net.liuxuan.device.OX2SDU.packet;

import net.liuxuan.device.base.BasePacket;

/**
 * OX2SDU Parser's Sensor Message
 *
 * @version
 * @author Moses
 */
public class OX2SDUInMessage extends BasePacket {

    private static final int PACKET_LEN = 3;

    public OX2SDUInMessage() {
    }

    public OX2SDUInMessage(byte[] _content) {
        super(_content);
    }

    /**
     * 重置为默认包体内容
     *
     * @param _content 重置时的content内容。如传入null则初始化为new
     * byte[CommonParameters.PACKET_BODY_LEN];
     */
    @Override
    protected void defaultPacket(byte[] _content) {
        if (_content == null) {
            this.pktbs = new byte[PACKET_LEN];
        } else {
            this.pktbs = _content;
        }
    }

    

}
