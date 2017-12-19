package com.ysy15350.ysyutils.model;

import java.io.Serializable;

/**
 * Created by yangshiyou on 2017/11/29.
 */

public class FileInfo implements Serializable {

    private String name;

    private String path;

    private long length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
