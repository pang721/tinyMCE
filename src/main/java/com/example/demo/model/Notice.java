package com.example.demo.model;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Notice {
    private Integer announcement_id;
    private String title;
    private String content;
    private String publisher;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date publish_time;

    public Integer getAnnouncement_id() {
        return announcement_id;
    }

    public void setAnnouncement_id(Integer announcement_id) {
        this.announcement_id = announcement_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }
}
