package com.app.dto.calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDTO {
    private int id;
    private String title;
    private Timestamp startDate;
    private Timestamp endDate;
    private String category;
}
