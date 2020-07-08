package com.bbaker.book.springboot.web;

import com.bbaker.book.springboot.service.posts.PostsService;
import com.bbaker.book.springboot.web.dto.PostsResponseDto;
import com.bbaker.book.springboot.web.dto.PostsSaveRequestDto;
import com.bbaker.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/*
* RequestBody 와 RequestParam 의 차이
*  - RequestBody : Body 자체를 가져오므로 POST에서만 사용 가능함, 주로 객체 단위로 받아 사용.
*  - ReqeustParam : GET에서 넘긴 파라미터를 메서드의 인자로 매칭하는 식으로 사용.
*/
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

}
