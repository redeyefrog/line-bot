package com.redeyefrog.controller;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.redeyefrog.handler.FollowEventHandler;
import com.redeyefrog.handler.StickerMessageHandler;
import com.redeyefrog.handler.TextMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@LineMessageHandler
public class WebhookController {

    @Autowired
    private TextMessageHandler text;

    @Autowired
    private StickerMessageHandler sticker;

    @Autowired
    private FollowEventHandler follow;

    @EventMapping
    public Message handlerTextMessageEvent(MessageEvent<TextMessageContent> event) {
        String message = event.getMessage().getText();

        if(message.contains("離開") || message.contains("go away") || message.contains("leave")) {

            return text.leave(event);
        }

        return text.getReplyMessage(event);
    }

    @EventMapping
    public Message handleStickerMessageEvent(MessageEvent<StickerMessageContent> event) {

        return sticker.getRandomSticker();
    }

    @EventMapping
    public Message handleFollowEvent(FollowEvent event) {

        return follow.getReplyMessage(event);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        log.debug("get event: {}", event);
    }

}
