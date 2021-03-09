package com.example.rentateam.jsonModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("per_page")
    @Expose
    private int per_page;

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("total_pages")
    @Expose
    private int total_pages;

    @SerializedName("data")
    @Expose
    private JsonUser[] data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public JsonUser[] getData() {
        return data;
    }

    public void setData(JsonUser[] data) {
        this.data = data;
    }
}
