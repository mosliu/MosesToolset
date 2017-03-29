package net.liuxuan.device.TempControler.packet;

import com.google.common.primitives.UnsignedBytes;
import net.liuxuan.device.base.BasePacket;
import net.liuxuan.utils.BytePlus;

/**
 * OxygenTransducer's 51-PC Message
 *
 * @version 1.0.0.0, Jul 29, 2011
 * @author Moses
 */
public class TCOutMessage extends BasePacket {

    private static final int PACKET_LEN = 8; //{(byte) 0x81, (byte) 0x81, 0x52, 0x1a, 0, 0, 0x53, 0x1a}

    public TCOutMessage() {
//        System.out.println("son()");
    }

    public TCOutMessage(byte[] _content) {
        super(_content);
//        System.out.println("son([])");
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

    /**
     * 按规则计算校验和
     * @return 
     */
    public byte[] calcCheckSum() {
        //{(byte) 0x81, (byte) 0x81, 0x52, 0x1a, 0, 0, 0x53, 0x1a}
        //读： 地址代号+52H（82）+要读参数的代号+0+0+CRC 校验码
        //写： 地址代号+43H（67）+要写参数的代号+写入数低字节+写入数高字节+CRC 校验码        
        //读指令的 CRC 校验码为：要读参数的代号*256+82+ADDR
        //写指令的 CRC 校验码则为：要写的参数代号*256+６７+要写的参数值+ADDR。
        //
        byte addr = (byte) (pktbs[0] - 0x80);  //ADDR ，减去0x80；
        byte funcid = pktbs[2];
        byte readid = pktbs[3];
        byte writenoLow = pktbs[5];
        byte writenoHigh = pktbs[4];
        System.out.println(UnsignedBytes.toInt(writenoHigh));
        System.out.println(writenoHigh);
        short crc = (short) (addr+funcid+256*readid+256*writenoLow+writenoHigh);
        return BytePlus.short2bytes(crc);
        
        
        
//        byte[] tempbyte = new byte[2];
//        tempbyte[0] = pktbs[3];
//        tempbyte[1] = 0;
//        short tempshort = BytePlus.bytes2short(tempbyte);
//        tempshort += 0x43;
//        tempbyte[0] = pktbs[4];
//        tempbyte[1] = pktbs[5];
//        
//        tempshort += BytePlus.bytes2short(tempbyte);
//        return BytePlus.short2bytes(tempshort);
    }

    /**
     * 重新计算校验并设置。
     */
    public void resetState() {
        byte bytes[] = calcCheckSum();
        pktbs[pktbs.length - 2] = bytes[0];
        pktbs[pktbs.length - 1] = bytes[1];
    }

    /**检查当前尾部校验和是否正确
     * 
     * @return 
     */
    public boolean CheckState1() {
        byte old[] = new byte[2];
        old[0] = pktbs[pktbs.length - 2];
        old[1] = pktbs[pktbs.length - 1];
        byte now[] = calcCheckSum();
        if (old[0] == now[0] && old[1] == now[1]) {
            return true;
        } else {
            return false;
        }
    }
    public static void main(String[] args) {
//        byte[] a = {(byte) 0x81, (byte) 0x81, 0x52, 0x1a, 0, 0, 0x53, 0x1a};
        byte[] a = {(byte) 0x81, (byte) 0x81, 67, 0, (byte)232, 3, 44, 4};
        TCOutMessage t = new TCOutMessage(a);
        byte[] b= t.calcCheckSum();
        System.out.println(BytePlus.byteArray2String(b));
        System.out.println(b[0]);
        System.out.println(b[1]);
                
    }

}
