package com.bbaker.book.springboot.service.events;

import com.bbaker.book.springboot.config.auth.LoginUser;
import com.bbaker.book.springboot.config.auth.dto.SessionUser;
import com.bbaker.book.springboot.domain.admin.events.Events;
import com.bbaker.book.springboot.domain.admin.events.EventsRepository;
import com.bbaker.book.springboot.domain.user.User;
import com.bbaker.book.springboot.domain.user.UserRepository;
import com.bbaker.book.springboot.web.dto.admin.EventsListResponseDto;
import com.bbaker.book.springboot.web.dto.admin.EventsSaveRequestDto;
import com.bbaker.book.springboot.web.dto.admin.EventsUpdateReqeustDto;
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

    @Transactional
    public Long update(Long id, EventsUpdateReqeustDto reqeustDto) {
        try {
            Events events = eventsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
            events.update(reqeustDto);
            return id;

        } catch (Exception e){
            return null;
        }

    }

    @Transactional(readOnly = true)
    public List<EventsListResponseDto> findAll() {
        return eventsRepository.findAll().stream().map(EventsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EventsListResponseDto findById (Long id) {
        try {
            Events events = eventsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
            return new EventsListResponseDto(events);

        } catch (Exception e){
            return null;
        }


    }

    @Transactional
    public void delete (Long id) {
        eventsRepository.deleteById(id);
    }





    @Transactional
    public Long saveTest(EventsSaveRequestDto requestDto) {
        return eventsRepository.save(requestDto.toEntity()).getId();
    }

}
