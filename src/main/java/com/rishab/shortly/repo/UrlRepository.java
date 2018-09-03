package com.rishab.shortly.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishab.shortly.pojo.UrlDto;

public interface UrlRepository extends JpaRepository<UrlDto, Long> {
}
