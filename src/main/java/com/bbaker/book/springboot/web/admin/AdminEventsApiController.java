package com.bbaker.book.springboot.web.admin;

import com.bbaker.book.springboot.config.auth.LoginUser;
import com.bbaker.book.springboot.config.auth.dto.SessionUser;
import com.bbaker.book.springboot.service.events.EventsService;
import com.bbaker.book.springboot.web.dto.admin.EventsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/events")
@RequiredArgsConstructor
@RestController
public class AdminEventsApiController {

    private final EventsService eventsService;

    @PostMapping("/add")
    public Long save(@RequestBody EventsSaveRequestDto requestDto, @LoginUser SessionUser user) {
        return eventsService.save(requestDto, user);
    }
}
