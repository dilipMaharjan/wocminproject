/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wocminproject.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author dmaharjan
 */
public class ImageInfo {

    @SerializedName("img")
    private List<Integer> img = null;
    @SerializedName("x_dim")
    private Integer xDim;
    @SerializedName("x_start")
    private Integer xStart;
    @SerializedName("y_dim")
    private Integer yDim;
    @SerializedName("y_start")
    private Integer yStart;

    public ImageInfo() {
    }

    public List<Integer> getImg() {
        return img;
    }

    public void setImg(List<Integer> img) {
        this.img = img;
    }

    public Integer getXDim() {
        return xDim;
    }

    public void setXDim(Integer xDim) {
        this.xDim = xDim;
    }

    public Integer getXStart() {
        return xStart;
    }

    public void setXStart(Integer xStart) {
        this.xStart = xStart;
    }

    public Integer getYDim() {
        return yDim;
    }

    public void setYDim(Integer yDim) {
        this.yDim = yDim;
    }

    public Integer getYStart() {
        return yStart;
    }

    public void setYStart(Integer yStart) {
        this.yStart = yStart;
    }
}
