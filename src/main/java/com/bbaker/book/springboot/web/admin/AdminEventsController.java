package com.bbaker.book.springboot.web.admin;

import com.bbaker.book.springboot.config.auth.LoginUser;
import com.bbaker.book.springboot.config.auth.dto.SessionUser;
import com.bbaker.book.springboot.service.events.EventsService;
import com.bbaker.book.springboot.web.dto.admin.EventsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String eventsAdd(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("session_user", user);
        }

        return "/admin/events/events-add";
    }

    @GetMapping("/update/{id}")
    public String eventsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {

        EventsListResponseDto dto = eventsService.findById(id);

        if(dto == null) {
            return "redirect:/admin/events";
        }

        model.addAttribute("events", dto);

        if(user != null) {
            model.addAttribute("session_user", user);
        }

        return "/admin/events/events-update";
    }

}
