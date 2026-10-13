package com.revers.chatApp.config;

import com.revers.chatApp.chat.ChatMessage;
import com.revers.chatApp.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import static org.springframework.data.util.TypeUtils.type;

@Component
@RequiredArgsConstructor
@Slf4j
public class webSocketEventListener {
    private final SimpMessageSendingOperations messageTemplate ;




    @EventListener
    public void handleWebSocketDisconnectListener(
            SessionDisconnectEvent event
    ){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = headerAccessor.getSessionAttributes().get("username").toString();
        if (username != null){
            log.info("User disconnect : {}" , username);
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVER)
                    .sender(username)
                    .build();
            messageTemplate.convertAndSend("/topic/public",chatMessage);

        }
    }
}
