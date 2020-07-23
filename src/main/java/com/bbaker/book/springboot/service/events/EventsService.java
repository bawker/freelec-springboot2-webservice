package com.bbaker.book.springboot.service.events;

import com.bbaker.book.springboot.config.auth.LoginUser;
import com.bbaker.book.springboot.config.auth.dto.SessionUser;
import com.bbaker.book.springboot.domain.admin.events.EventsRepository;
import com.bbaker.book.springboot.domain.user.User;
import com.bbaker.book.springboot.domain.user.UserRepository;
import com.bbaker.book.springboot.web.dto.admin.EventsListResponseDto;
import com.bbaker.book.springboot.web.dto.admin.EventsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventsService {
    private final EventsRepository eventsRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(EventsSaveRequestDto requestDto, @LoginUser SessionUser user) {
        User user_info = userRepository.findUserById(user.getId());

        requestDto.setUser(user_info);

        return eventsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<EventsListResponseDto> findAll() {
        return eventsRepository.findAll().stream().map(EventsListResponseDto::new).collect(Collectors.toList());
    }
}
