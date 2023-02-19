package com.willi.twitter.repository;

import com.willi.twitter.model.TwitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TwitRepository extends JpaRepository<TwitModel, Long> {

}
