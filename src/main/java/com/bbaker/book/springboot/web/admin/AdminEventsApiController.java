package com.bbaker.book.springboot.web.admin;

import com.bbaker.book.springboot.config.auth.LoginUser;
import com.bbaker.book.springboot.config.auth.dto.SessionUser;
import com.bbaker.book.springboot.service.events.EventsService;
import com.bbaker.book.springboot.web.dto.admin.EventsSaveRequestDto;
import com.bbaker.book.springboot.web.dto.admin.EventsUpdateReqeustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/events")
@RequiredArgsConstructor
@RestController
public class AdminEventsApiController {

    private final EventsService eventsService;

    @PostMapping("/add")
    public Long save(@RequestBody EventsSaveRequestDto requestDto, @LoginUser SessionUser user) {
        return eventsService.save(requestDto, user);
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody EventsUpdateReqeustDto reqeustDto) {
        return eventsService.update(id, reqeustDto);
    }

    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        eventsService.delete(id);
        return id;
    }
}
