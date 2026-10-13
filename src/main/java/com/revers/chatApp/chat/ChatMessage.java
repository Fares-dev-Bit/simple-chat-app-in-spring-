package com.revers.chatApp.chat;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    private String content ;
    private String sender  ;
    private MessageType type ;
}
