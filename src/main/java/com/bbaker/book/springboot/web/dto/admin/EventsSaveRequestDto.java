package com.bbaker.book.springboot.web.dto.admin;

import com.bbaker.book.springboot.domain.admin.events.Events;
import com.bbaker.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EventsSaveRequestDto {

    private String type;
    private String title;
    private String content;
    private String start_date;
    private String end_date;
    private String start_time;
    private String end_time;
    private User user;

    @Builder
    public EventsSaveRequestDto(String type, String title, String content, String start_date, String end_date, String start_time, String end_time, User user) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.user = user;
    }

    public Events toEntity() {
        return Events.builder()
                .type(type)
                .title(title)
                .content(content)
                .start_date(start_date)
                .end_date(end_date)
                .start_time(start_time)
                .end_time(end_time)
                .user(user)
                .build();
    }

}
