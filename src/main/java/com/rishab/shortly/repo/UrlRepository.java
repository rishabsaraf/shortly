package com.rishab.shortly.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishab.shortly.pojo.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
}
