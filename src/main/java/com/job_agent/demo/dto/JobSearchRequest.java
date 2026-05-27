package com.job_agent.demo.dto;

public class JobSearchRequest {

    private String keyword;
    private String location;

    public JobSearchRequest() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}