/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.device.base;

import java.util.Arrays;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Moses
 */
public abstract class BasePacketDecoder<T extends BasePacket> implements MessageDecoder {

    // Return NEED_DATA if the whole header is not read yet.
    private static final Logger log = LoggerFactory.getLogger(BasePacketDecoder.class);
    T samplePkt;
    T rtnPkt;

    //设置T；
    public abstract void initSamplePkt();
    public abstract void initRtnPkt();

    public  void setSamplePkt(T t){
        this.samplePkt = t;
    }
    public  void setRtnPkt(T t){
        this.rtnPkt = t;
    }
        
    
    public BasePacketDecoder(){
        initSamplePkt();
    }
    
    @Override
    public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
        if (in.remaining() < samplePkt.getLength()) {
            return MessageDecoderResult.NEED_DATA;
        }
        if (samplePkt.isAssemble) {
            byte[] judgebs;
            byte[] standard = new byte[0];
            if (samplePkt.hasHeader) {
                standard = samplePkt.headerbs;
            } else if (samplePkt.hasTail) {
                standard = samplePkt.tailbs;
            } else {
                //没头没尾
//                length = 0;
                //TODO 如果没有的话怎么处理？
                throw new UnsupportedOperationException("Not supported yet.");
            }
            judgebs = new byte[standard.length];
            in.get(judgebs);

            while (!Arrays.equals(standard, judgebs)) {
                if (in.hasRemaining()) {
                    for (int i = 0; i < judgebs.length - 1; i++) {
                        judgebs[i] = judgebs[i + 1];
                    }
                    judgebs[judgebs.length - 1] = in.get();
                } else {
                    return NEED_DATA;
                }
            }
            //计算还需要多少字节
            int remaincount;
            if (samplePkt.hasHeader) {
                remaincount = samplePkt.getLength() - judgebs.length;
            } else if (samplePkt.hasTail) {
                remaincount = 0;
            } else {
                remaincount = samplePkt.getLength();
            }
            if (in.remaining() < remaincount) {
                return NEED_DATA;
            } else {
                return OK;
            }

        } else {
            //非拼装的,由继承类自行判断
            if (in.remaining() < samplePkt.getLength()) {
                return NEED_DATA;
            } else {
                return OK;
            }
        }

    }

    @Override
    public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        T pkt = decodeBody(session, in);
//        if (samplePkt.CheckState()) {
        if (pkt != null) {
            //包正确
            out.write(pkt);
            return MessageDecoderResult.OK;
        } else {
            return MessageDecoderResult.NOT_OK;
        }
    }

    private T decodeBody(IoSession session, IoBuffer in) {

        initRtnPkt();
        
        if (in.remaining() < samplePkt.getLength()) {
            return null;
        }

        if (samplePkt.isAssemble) {
            byte[] judgebs;
            byte[] standard = new byte[0];
            if (samplePkt.hasHeader) {
                standard = samplePkt.headerbs;
            } else if (samplePkt.hasTail) {
                standard = samplePkt.tailbs;
            } else {
                //没头没尾
//                length = 0;
                //TODO 如果没有的话怎么处理？
                throw new UnsupportedOperationException("Not supported yet.");
            }
            judgebs = new byte[standard.length];
            in.get(judgebs);

            while (!Arrays.equals(standard, judgebs)) {
                if (in.hasRemaining()) {
                    for (int i = 0; i < judgebs.length - 1; i++) {
                        judgebs[i] = judgebs[i + 1];
                    }
                    judgebs[judgebs.length - 1] = in.get();
                } else {
                    log.debug("ERROR PACKET{}", in.getHexDump());
                    return null;
                }
            }

            if (samplePkt.hasHeader) {
                //查找头，之前的扔掉
                rtnPkt.setHeaderbs(judgebs);
                byte[] temp = rtnPkt.getContent();
                in.get(temp);
                rtnPkt.setContent(temp);

            } else if (samplePkt.hasTail) {
                int k = in.position() - judgebs.length;
                byte[] temp = new byte[k];
                in.flip();
                in.get(temp);
                rtnPkt.setContent(temp);
                rtnPkt.setTailbs(judgebs);
            } else {
                //没头没尾
//                length = 0;
                //TODO 如果没有的话怎么处理？
                throw new UnsupportedOperationException("Not supported yet.");
            }
        } else {
            //非拼装的,由继承类自行判断
            byte[] temp = rtnPkt.getPktbs();
            in.get(temp);
            rtnPkt.setPktbs(temp);
        }

        return rtnPkt;
    }

    @Override
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
    }
}
