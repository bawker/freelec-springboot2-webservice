package com.bbaker.book.springboot.web.dto.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventsUpdateReqeustDto {
    private String title;
    private String type;
    private String content;
    private String start_date;
    private String end_date;
    private String start_time;
    private String end_time;

}
