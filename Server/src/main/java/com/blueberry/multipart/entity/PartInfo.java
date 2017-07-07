package com.blueberry.multipart.entity;

/**
 * Created by Administrator on 7/6/2017.
 */
public class PartInfo {
    private String mediaType;
    private long size;
    private String text;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
