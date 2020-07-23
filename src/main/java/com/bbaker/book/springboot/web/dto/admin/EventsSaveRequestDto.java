package com.bbaker.book.springboot.web.dto.admin;

import com.bbaker.book.springboot.domain.admin.events.Events;
import com.bbaker.book.springboot.domain.user.User;
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
