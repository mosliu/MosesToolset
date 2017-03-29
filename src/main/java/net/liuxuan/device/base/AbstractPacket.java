package net.liuxuan.device.base;

import net.liuxuan.utils.BytePlus;

/**
 * A base Packet for protocol messages.
 *
 * @author Moses
 */
@Deprecated
public abstract class AbstractPacket extends BasePacket {

    public short header = 0;
    public short tail = 0;
    public int length = 0;
    public byte[] content;

    public AbstractPacket() {
        reset();

    }

    public AbstractPacket(byte[] _content) {
        defaultPacket(_content);
    }

    public short getHeader() {
        return header;
    }

    public void setHeader(short header) {
        this.header = header;
    }

    public short getTail() {
        return tail;
    }

    public void setTail(short tail) {
        this.tail = tail;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
        resetState();
    }

    public void resetState() {
        content[content.length - 1] = caculateCheckValue();
    }

    public byte caculateCheckValue() {
        int count = 0;
        for (int i = 0; i < content.length - 1; i++) {
            count += content[i];
        }
        count = count & 0xff;
        return (byte) count;
    }

    public boolean CheckState() {
        byte old = content[content.length - 1];
        byte now = caculateCheckValue();
        if (old == now) {
            return true;
        } else {
            return false;
        }
    }

    public void reset() {
        defaultPacket(null);
    }

    

    @Override
    public String toString() {
        return "包体:(" + BytePlus.byteArray2String(content) + ')';
    }

    @Override
    public void setPktbs(byte[] pktbs) {
        super.setPktbs(pktbs); 
    }

    @Override
    public byte[] getPktbs() {
        return super.getPktbs(); 
    }

    
    
}
