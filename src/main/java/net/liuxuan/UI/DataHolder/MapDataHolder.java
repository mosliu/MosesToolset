/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.UI.DataHolder;

import com.google.common.collect.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import net.liuxuan.utils.FilePlus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Moses
 */
public class MapDataHolder implements DataHolder {

    /**
     * 数据保存
     */
    public HashMap<String, Object> data;

    File destfile;
    private static final Logger log = LoggerFactory.getLogger(MapDataHolder.class);

    /**
     * 初始化函数，初始化Map
     */
    public MapDataHolder() {
        this.data = Maps.newHashMap();
    }

    private void initfile() {
        String path = null;
        String name = null;
        if (destfile != null && destfile.isFile()) {
            return;
        } else {
//            String name = new SimpleDateFormat("yyyyMMdd").format(new Date());
            name = "datasave.dat";
            if (destfile != null && destfile.isDirectory()) {
                path = destfile.getAbsolutePath();
            } else {
                path = "./save";
            }
            destfile = new File(path + "/" + name);
        }
        FilePlus.createFile(destfile);
    }

    @Override
    public File getDestFile() {
        initfile();
        return destfile;
    }

    @Override
    public void save() {
        try {
            ObjectOutputStream out = null;
            out = new ObjectOutputStream(new FileOutputStream(getDestFile()));
            out.writeObject(data);
        } catch (IOException ex) {
            log.error("序列化失败", ex);
        }
    }

    @Override
    public void load() {
        initfile();
        if (destfile== null) {
            log.error("读取失败，{}\n", "empty File", new Exception("null FILE"));
        } else if (destfile.isFile() == false) {
            log.error("读取失败，文件路径{}\n", destfile.getAbsolutePath(), new Exception("ERROR FILE"));
        }
        if(destfile.length()==0){
            log.error("记录文件无内容");
            return;
        }
        try {
            
            if (destfile != null && destfile.exists()) {
                FileInputStream fis = new FileInputStream(destfile);
                ObjectInputStream in = new ObjectInputStream(fis);
                data = (HashMap) in.readObject();
                
            }
        } catch (IOException ex) {
            log.error("读取失败，文件路径{}\n", destfile.getAbsolutePath(), ex);
        } catch (ClassNotFoundException ex) {
            log.error("对象转换失败，文件路径{}\n", destfile.getAbsolutePath(), ex);
        }
    }

    @Override
    public boolean setDestFile(String file) {
        if (file == null) {
            return false;
        }
        destfile = new File(file);
        return true;
    }

    @Override
    public boolean setDestFile(File file) {
        if (file == null) {
            return false;
        }
        destfile = file;
        return true;
    }

    @Override
    public Object get(String key) {
        return data.get(key);
    }

    @Override
    public Object remove(String key) {
        return data.remove(key);

    }

    @Override
    public Object put(String key, Object value) {
        return data.put(key, value);
    }

}
