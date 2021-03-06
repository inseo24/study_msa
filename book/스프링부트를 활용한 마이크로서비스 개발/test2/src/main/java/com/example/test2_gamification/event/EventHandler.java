package com.example.test2_gamification.event;

import com.example.test2_gamification.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 이벤트를 받고 연관된 비즈니스 로직을 동작시킴
 * */
@Slf4j
@Component
public class EventHandler {

    private GameService gameService;

    public EventHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @RabbitListener(queues = "${multiplication.queue}")
    void handleMultiplicationSolved(final MultiplicationSolvedEvent event){
        log.info("Multiplication Solved Event 수신 : {}", event.getMultiplicationResultAttemptId());
        try {
            gameService.newAttemptForUser(event.getUserId(), event.getMultiplicationResultAttemptId(), event.isCorrect());

        } catch (final Exception e){
            log.error("MultiplicationSolvedEvent 처리 시 에러", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
