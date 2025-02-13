package com.app.dto.calendar;

import java.sql.Timestamp;

public class CalendarDTO {
    private int id;
    private String title;
    private Timestamp startDate;
    private Timestamp endDate;
    private String category;
    private String userEmail; // 📌 사용자 이메일 필드 추가

    // 기본 생성자
    public CalendarDTO() {}

    // 모든 필드를 포함하는 생성자 추가
    public CalendarDTO(int id, String title, Timestamp startDate, Timestamp endDate, String category, String userEmail) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.userEmail = userEmail;
    }

    // 📌 Getter & Setter 추가
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
