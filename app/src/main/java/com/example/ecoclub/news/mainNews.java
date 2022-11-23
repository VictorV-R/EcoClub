package com.example.ecoclub.news;

import java.util.ArrayList;

public class mainNews {
    private String status;
    private String totalResults;
    private ArrayList<NewsShowClass> articles;

    public mainNews(String status, String totalResults, ArrayList<NewsShowClass> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<NewsShowClass> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<NewsShowClass> articles) {
        this.articles = articles;
    }
}
