package com.example.crynoz.temp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TextBlock {

    /**
     *
     * (Required)
     *
     */
    @SerializedName("height")
    @Expose
    private Integer height;
    /**
     *
     * (Required)
     *
     */
    @SerializedName("left")
    @Expose
    private Integer left;
    /**
     *
     * (Required)
     *
     */
    @SerializedName("text")
    @Expose
    private String text;
    /**
     *
     * (Required)
     *
     */
    @SerializedName("top")
    @Expose
    private Integer top;
    /**
     *
     * (Required)
     *
     */
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("page_num")
    @Expose
    private Integer pageNum;

    /**
     *
     * (Required)
     *
     * @return
     * The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     *
     * (Required)
     *
     * @param height
     * The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     *
     * (Required)
     *
     * @return
     * The left
     */
    public Integer getLeft() {
        return left;
    }

    /**
     *
     * (Required)
     *
     * @param left
     * The left
     */
    public void setLeft(Integer left) {
        this.left = left;
    }

    /**
     *
     * (Required)
     *
     * @return
     * The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * (Required)
     *
     * @param text
     * The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * (Required)
     *
     * @return
     * The top
     */
    public Integer getTop() {
        return top;
    }

    /**
     *
     * (Required)
     *
     * @param top
     * The top
     */
    public void setTop(Integer top) {
        this.top = top;
    }

    /**
     *
     * (Required)
     *
     * @return
     * The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     *
     * (Required)
     *
     * @param width
     * The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     *
     * @return
     * The pageNum
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     *
     * @param pageNum
     * The page_num
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

}
