package com.leeleelee3264.nasabot.domain.todayearth.dao;

import com.leeleelee3264.nasabot.domain.model.TEHistory;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface TEHistoryRepository extends CrudRepository<TEHistory, Long> {

    TEHistory findByCreateDate(LocalDate createDate);

    boolean existsByCreateDate(LocalDate createDate);
}
