package com.bbaker.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

/*
* assertThat
*  - assertj라는 테스트 검증 라이브러리의 검증 메소드.
*  - 검증하고 싶은 대상을 메소드 인자로 받음.
*  - 메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있음
*
* isEqualTo
*  - assertj의 동등 비교 메소드.
*  - assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공.
*
* Junit의 기본 assertThat이 아닌 assertj의 assertThat을 사용한 이유는 CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않음.
*
*/
public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {

        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
