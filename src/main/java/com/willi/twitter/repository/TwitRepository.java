package com.willi.twitter.repository;

import com.willi.twitter.model.TweetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitRepository extends JpaRepository<TweetModel, Long> {

}
