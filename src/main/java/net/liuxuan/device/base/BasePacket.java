/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.device.base;

import java.io.Serializable;
import java.nio.ByteBuffer;
import net.liuxuan.utils.BytePlus;

/**
 *
 * @author Moses
 */
public abstract class BasePacket implements Serializable {

    //是否是拼装的
    boolean isAssemble = false;
    boolean hasHeader = false;
    boolean hasTail = false;
    //头字节
    byte[] headerbs;
    //尾字节
    byte[] tailbs;
    //中间内容
    byte[] content;

    /**
     * 全部的字节
     */
    public byte[] pktbs;

    //-----------------getter & setter-----------------
    public boolean isAssemble() {
        return isAssemble;
    }

    public void setAssemble(boolean isAssemble) {
        this.isAssemble = isAssemble;
        if(content==null){
            content = new byte[0];
        }
    }

    public boolean isHasHeader() {
        return hasHeader;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public boolean isHasTail() {
        return hasTail;
    }

    public void setHasTail(boolean hasTail) {
        this.hasTail = hasTail;
    }

    public byte[] getHeaderbs() {
        return headerbs;
    }

    public void setHeaderbs(byte[] headerbs) {
        this.hasHeader = true;
        this.headerbs = headerbs;
    }

    public byte[] getTailbs() {
        return tailbs;
    }

    public void setTailbs(byte[] tailbs) {
        this.hasTail = true;
        this.tailbs = tailbs;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    /**
     *
     * @return
     */
    public byte[] getPktbs() {
        if (isAssemble) {
            reAssemble();
        }
        return pktbs;
    }

    /**
     *
     * @param pktbs
     */
    public void setPktbs(byte[] pktbs) {
        this.pktbs = pktbs;
    }

    //-----------------构造函数-----------------
    /**
     *
     */
    public BasePacket() {
        isAssemble = false;
//        System.out.println("base()");
        reset();
    }

    /**
     *
     * @param _content
     */
    public BasePacket(byte[] _content) {
        this(_content, false);
//        System.out.println("base([])");
    }

    public BasePacket(byte[] _content, boolean _isAssemble) {
        this.isAssemble = _isAssemble;
        defaultPacket(_content);
    }

    //-----------------方法函数-----------------
    /**
     * 获取整包长度
     *
     * @return
     */
    public int getLength() {
        int count = 0;
        if (isAssemble) {
            if (hasHeader) {
                count += headerbs.length;
            }
            count += content.length;
            if (hasTail) {
                count += tailbs.length;
            }
            return count;
        } else {
            return pktbs.length;
        }
    }

    public void reAssemble() {
        ByteBuffer bb;
        bb = ByteBuffer.allocate(getLength());
        //拷贝头
        if (hasHeader) {
            bb.put(headerbs);
        }
        bb.put(content);
        if (hasTail) {
            bb.put(tailbs);
        }
        bb.flip();
        pktbs = bb.array();

        //拷贝内容
        //拷贝尾
    }

    /**
     *
     */
    public void reset() {
        defaultPacket(null);
    }

    @Override
    public String toString() {
        if (isAssemble) {
            reAssemble();
        }
        
        return "包体:(" + BytePlus.byteArray2String(pktbs) + ')';
    }

    /**
     * 重置为默认包体内容
     *
     * @param _content 重置时的content内容。如传入null则初始化为new byte[PACKET_BODY_LEN];
     */
    protected abstract void defaultPacket(byte[] _content);
}
