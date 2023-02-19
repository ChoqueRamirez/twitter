package com.willi.twitter.repository;

import com.willi.twitter.business.User;
import com.willi.twitter.model.TwitModel;
import com.willi.twitter.services.TwitService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public interface TwitRepository extends JpaRepository<TwitModel, Long> {

}
