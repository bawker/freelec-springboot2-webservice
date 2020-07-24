package com.bbaker.book.springboot.domain.admin.events;

import com.bbaker.book.springboot.domain.BaseTimeEntity;
import com.bbaker.book.springboot.domain.user.User;
import com.bbaker.book.springboot.web.dto.admin.EventsUpdateReqeustDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Events extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String type;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String start_date;
    private String end_date;
    private String start_time;
    private String end_time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Events(String type, String title, String content, String start_date, String end_date, String start_time, String end_time, User user) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.user = user;
    }

    public void update(EventsUpdateReqeustDto events) {
        this.type = events.getType();
        this.title = events.getTitle();
        this.content = events.getContent();
        this.start_date = events.getStart_date();
        this.end_date = events.getEnd_date();
        this.start_time = events.getStart_time();
        this.end_time = events.getEnd_time();
    }

}
