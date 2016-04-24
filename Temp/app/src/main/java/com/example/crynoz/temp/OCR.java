package com.example.crynoz.temp;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OCR {

    /**
     *
     * (Required)
     *
     */
    @SerializedName("text_block")
    @Expose
    private List<TextBlock> textBlock = new ArrayList<TextBlock>();
    @SerializedName("page_count")
    @Expose
    private Integer pageCount;

    /**
     *
     * (Required)
     *
     * @return
     * The textBlock
     */
    public List<TextBlock> getTextBlock() {
        return textBlock;
    }

    /**
     *
     * (Required)
     *
     * @param textBlock
     * The text_block
     */
    public void setTextBlock(List<TextBlock> textBlock) {
        this.textBlock = textBlock;
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