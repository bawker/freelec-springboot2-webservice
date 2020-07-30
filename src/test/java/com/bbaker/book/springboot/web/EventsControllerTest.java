package com.bbaker.book.springboot.web;

import com.bbaker.book.springboot.domain.admin.events.Events;
import com.bbaker.book.springboot.domain.admin.events.EventsRepository;
import com.bbaker.book.springboot.domain.user.Role;
import com.bbaker.book.springboot.domain.user.User;
import com.bbaker.book.springboot.domain.user.UserRepository;
import com.bbaker.book.springboot.web.dto.admin.EventsSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        eventsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Events_등록된다() throws Exception {
        //given
        String title = "title";
        String type = "live";
        String content = "content";
        String start_date = "2019-12-23";
        String end_date = "2019-12-24";
        String start_time = "09:30";
        String end_time = "12:30";

        EventsSaveRequestDto requestDto = EventsSaveRequestDto.builder()
                .title(title)
                .type(type)
                .content(content)
                .start_date(start_date)
                .end_date(end_date)
                .start_time(start_time)
                .end_time(end_time)
                .build();

        String url = "http://localhost:" + port + "/admin/events/add/test";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Events> all = eventsRepository.findAll(Sort.by("id").descending());
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);






    }



}
