package com.bbaker.book.springboot.web.dto.admin;

import com.bbaker.book.springboot.domain.admin.events.Events;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventsListResponseDto {
    private Long id;
    private String type;
    private String title;
    private String content;
    private String start_date;
    private String end_date;
    private String start_time;
    private String end_time;
    private LocalDateTime modifiedDate;

    public EventsListResponseDto(Events events) {
        this.id = events.getId();
        this.type = events.getType();
        this.title = events.getTitle();
        this.content = events.getContent();
        this.start_date = events.getStart_date();
        this.end_date = events.getEnd_date();
        this.start_time = events.getStart_time();
        this.end_time = events.getEnd_time();
        this.modifiedDate = events.getModifiedDate();
    }


}
