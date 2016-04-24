package com.example.crynoz.temp;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Face {

    /**
     *
     * (Required)
     *
     */
    @SerializedName("face")
    @Expose
    private List<Face_> face = new ArrayList<Face_>();
    @SerializedName("page_count")
    @Expose
    private Integer pageCount;

    /**
     *
     * (Required)
     *
     * @return
     * The face
     */
    public List<Face_> getFace() {
        return face;
    }

    /**
     *
     * (Required)
     *
     * @param face
     * The face
     */
    public void setFace(List<Face_> face) {
        this.face = face;
    }

    /**
     *
     * @return
     * The pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     *
     * @param pageCount
     * The page_count
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

}