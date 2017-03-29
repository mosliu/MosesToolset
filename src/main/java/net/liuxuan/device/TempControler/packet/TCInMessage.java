package net.liuxuan.device.TempControler.packet;

import java.nio.ByteBuffer;
import net.liuxuan.device.base.BasePacket;
import net.liuxuan.utils.BytePlus;

/**
 * OxygenTransducer's PC-51 Message
 *
 * @version 1.0.0.0, Jul 29, 2011
 * @author Moses
 */
public class TCInMessage extends BasePacket {

    private static final int PACKET_LEN = 10;

    public TCInMessage() {
    }

    public TCInMessage(byte[] _content) {
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
        /*
         无论是读还是写，仪表都返回以下数据
         测量值 PV+给定值 SV+输出值 MV 及报警状态+所读/写参数值+CRC 校验码
         其中 PV、SV 及所读参数值均为整数格式，各占 2 个字节，MV 占一个字节，数值范
         围 0-220，报警状态占一个字节，CRC 校验码占 2 个字节，共 10 个字节。
         CRC 校验码为 PV+SV+（报警状态*256+MV）+参数值+ADDR，按整数加法相加后得到的余
         数。
         每 2 个 8 位数据代表一个 16 位整形数，低位字节在前，高位字节在后，各温度值采用
         补码表示，热电偶或热电阻输入时其单位都是 0.1℃，1—5V 或 0—5V 等线性输入时，单位
         都是线性最小单位。因为传递的是 16 位二进制数，所以无法表示小数点，要求用户在上位
         机处理。
         输出值和报警状态各占 1 个字节，报警状态采用二进制代码表示各报警信号，如下：
         位 0 为 0 则上限报警（HIAL）不成立，为 1 为上限报警成立。
         位 1 为 0 则下限报警（LoAL）不成立，为 1 为下限报警成立。
         位 2 为 0 则正偏差报警（dHAL）不成立，为 1 为正偏差报警成立。
         位 3 为 0 则负偏差报警（dLAL）不成立，为 1 为负偏差报警成立。
         位 4 为 0 则输入超量程报警（orAL）不成立，为 1 则输入超量程报警成立。
         位 5 为 0 则事件输出 1 不成立，为 1 则事件输出 1 成立（仅 AI—708P 使用）。
         位 6 为 0 则事件输出 2 不成立，为 1 则事件输出 2 成立（仅 AI—708P 使用）。
         位 7 固定为 0。
         上位机通过分析可得到仪表当前的工作状态。
         */
        if (_content == null) {
            this.pktbs = new byte[PACKET_LEN];
        } else {
            this.pktbs = _content;
        }
    }

    public static void main(String[] args) {
//        byte n0 = (byte) (0x81 - 0x80);
//        byte[] tempbyte = new byte[2];
//        tempbyte[0] = 0x17;
//        tempbyte[1] = 0;
//        short short1 = BytePlus.bytes2short(tempbyte);
//        tempbyte[0] = 0x0;
//        tempbyte[1] = (byte) 0x91;
//        short1 += BytePlus.bytes2short(tempbyte);
//        System.out.println(short1);
//        byte[] bs = BytePlus.short2bytes(short1);
//        System.out.println(BytePlus.byteArray2String(bs));

        byte[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TCInMessage inm = new TCInMessage(a);
        inm.parsepak();
        System.out.println("pv:"+BytePlus.bytes2short(inm.pv));
        System.out.println("sv:"+BytePlus.bytes2short(inm.sv));
        System.out.println("crc:"+BytePlus.bytes2short(inm.crc));

    }

    byte[] pv = new byte[2];
    byte[] sv= new byte[2];
    byte mv;
    byte alertstatus;
    byte[] paramno= new byte[2];
    byte[] crc= new byte[2];

    void parsepak() {
        ByteBuffer bb = ByteBuffer.wrap(pktbs);
        bb.get(pv);
        bb.get(sv);
        mv = bb.get();
        alertstatus = bb.get();
        bb.get(paramno);
        bb.get(crc);
    }
    
    int getDeviceNo(){
        // CRC 校验码为 PV+SV+（报警状态*256+MV）+参数值+ADDR，按整数加法相加后得到的余数。
        byte[] crcs = new byte[2];
        crcs[0] += pv[0]+sv[0]+alertstatus+paramno[0];
        crcs[1] += pv[1]+sv[1]+mv+paramno[1];
        
        return crc[0]-crcs[0];
    }

}
