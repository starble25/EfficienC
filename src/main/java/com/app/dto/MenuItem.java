package com.app.dto;

public class MenuItem {
    private String name;
    private String link;
    private boolean active;

    // 기본 생성자
    public MenuItem() {
    }

    // 모든 필드를 초기화하는 생성자
    public MenuItem(String name, String link, boolean active) {
        this.name = name;
        this.link = link;
        this.active = active;
    }

    // Getter
    public String getName() {
        return name;
    }
    public String getLink() {
        return link;
    }
    public boolean isActive() {
        return active;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
