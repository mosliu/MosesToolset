/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.UI.DataHolder;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Moses
 */
public interface DataHolder extends Serializable {

    /**
     * 设置存盘文件
     *
     * @param file
     * @return
     */
    public boolean setDestFile(String file);

    /**
     * 设置存盘文件
     *
     * @param file
     * @return
     */
    public boolean setDestFile(File file);

    /**
     * 得到存盘文件
     *
     * @return
     */
    public File getDestFile();

    /**
     * 存盘
     */
    public void save();

    /**
     * 读盘
     */
    public void load();
    
    /**
     * 放数据
     */
    public Object put(String key,Object value);
    /**
     * 取数据
     */
    public Object get(String key);

    /**
     * 删数据
     */
    public Object remove(String key);
    
}
