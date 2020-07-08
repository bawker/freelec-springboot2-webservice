package com.bbaker.book.springboot.service.posts;

import com.bbaker.book.springboot.domain.posts.Posts;
import com.bbaker.book.springboot.domain.posts.PostsRepository;
import com.bbaker.book.springboot.web.dto.PostsListResponseDto;
import com.bbaker.book.springboot.web.dto.PostsResponseDto;
import com.bbaker.book.springboot.web.dto.PostsSaveRequestDto;
import com.bbaker.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*
* @Service
*  - Repository를 통해 데이터베이스에서 데이터를 가져온 후 컨트롤러에게 전달해 주는 클래스임을 명시.
*
* update 기능에서 데이터베이스에 쿼리를 사용하는 부분이 없는데 가능한이유는?
*  - JPA의 영속성 컨텍스트 때문
*  - 영속성 컨텍스트란 엔티티를 영구 저장하는 환경. (JPA의 핵심내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐)
*  - JPA의 엔티티 매니저가 활성화된 상태로(Spring Data Jpa를 사용한다면 기본 옵션) 트랜잭션 안에서 데이터베이스에서
*    데이터를 가져오면 이 테이터는 영속성 컨텍스트가 유지된 상태임.
*    이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영.
*    즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요 없음 이개념이 더티 체킹.
*/

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        //.map(PostsListResponseDto::new) 는 .map(posts -> new PostsListResponseDto(posts)) 와 같다
        // postsRepository 결과로 넘어온 Posts의 stream을 map을 통해 PostsListResponseDto로 변환 -> List로 변환

        //postsRepository.findAllDesc().stream().filter(a -> a.getId() % 2 == 0).map(PostsListResponseDto::new).map(PostsListResponseDto::getAuthor).forEach(System.out::println);

        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
        //postsRepository.deleteById(id); (이렇게 사용가능)
    }
}
