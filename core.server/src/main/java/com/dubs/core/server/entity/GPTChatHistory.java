package com.dubs.core.server.entity;

import com.dubs.core.server.dto.GPTExchange;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("gpt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GPTChatHistory {

    @Id
    private String id;

    @Max(65535)
    @Min(0)
    private Integer userId;

    //TODO: when history gets too large (length > 200), need to break into smaller conversations
//    @Min(0)
//    private Integer conversationId;

    List<GPTExchange> history;

}
