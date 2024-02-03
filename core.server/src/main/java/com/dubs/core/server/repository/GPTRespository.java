package com.dubs.core.server.repository;

import com.dubs.core.server.entity.GPTChatHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GPTRespository extends MongoRepository<GPTChatHistory,String> {

    List<GPTChatHistory> findAllByUserId(Integer userId);

}
