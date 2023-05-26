package com.willi.twitter.repository;

import com.willi.twitter.model.TweetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<TweetModel, Long> {

    List<TweetModel> findByUserOwnerId(Long userId);
}

