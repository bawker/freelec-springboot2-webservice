package com.bbaker.book.springboot.domain.admin.events;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository <Events, Long> {
}
