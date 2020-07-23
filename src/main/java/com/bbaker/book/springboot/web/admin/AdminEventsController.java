package com.bbaker.book.springboot.web.admin;

import com.bbaker.book.springboot.config.auth.LoginUser;
import com.bbaker.book.springboot.config.auth.dto.SessionUser;
import com.bbaker.book.springboot.service.events.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/events")
@RequiredArgsConstructor
@Controller
public class AdminEventsController {

    private final EventsService eventsService;
    @GetMapping("")
    public String index(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("session_user", user);
        }

        model.addAttribute("events_list", eventsService.findAll());

        return "/admin/events/events-list";
    }

    @GetMapping("/add")
    public String add(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("session_user", user);
        }

        return "/admin/events/events-add";
    }

}
