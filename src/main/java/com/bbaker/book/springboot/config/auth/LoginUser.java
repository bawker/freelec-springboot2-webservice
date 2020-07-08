package com.bbaker.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* @Target(ElementType.PARAMETER)
*  - 이 어노테이션이 생성될 수 있는 위치를 지정함.
*  - PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있음.
*
* @Retention(RetentionPolicy.RUNTIME)
*  - 컴파일 이후에도 JVM에 의해서 참조가 가능
*  - CLASS, SOURCE 등 있음
*
* @interface
*  - 이 파일을 어노테이션 클래스로 지정함.
*  - LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면 됨.
*/

/*
* 세션 user값을 가져오려면 컨트롤러 파라미터 값으로 @LoginUser만 사용 하면 됨
*/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
