package com.redeyefrog.handler;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.source.GroupSource;
import com.linecorp.bot.model.event.source.RoomSource;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.redeyefrog.persistence.dao.LineMessageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public abstract class CommonHandler {

    protected static Logger log = LoggerFactory.getLogger(CommonHandler.class);

    @Autowired
    protected LineMessagingClient client;

    @Autowired
    protected LineMessageDao dao;

    public Message leave(Event event) {
        Source source = event.getSource();

        String message = "";

        try {
            if (source instanceof GroupSource) {
                client.leaveGroup(((GroupSource) source).getGroupId()).get();
            } else if (source instanceof RoomSource) {
                client.leaveRoom(((RoomSource) source).getRoomId()).get();
            } else {
                message = dao.getByKeyword("not leave").getReplyMessage();
            }
        } catch(Exception e) {
            log.error("leave error: {}", e.getMessage(), e);
        }

        return getReplyMessage(composeEmoji(message));
    }

    protected TextMessage getReplyMessage(String message) {

        return TextMessage.builder().text(message).build();
    }

    protected String composeEmoji(String message) {
        String emojiSymbol = "0x";

        if(message.contains(emojiSymbol)) {
            if(message.length() == 8) {
                return toUnicode(message);
            }

            Set<String> emojis = new HashSet<>();

            String temp = message;

            int idx = -1;

            while((idx = temp.indexOf(emojiSymbol)) >= 0) {
                emojis.add(temp.substring(idx, idx + 8));

                temp = temp.substring(idx + emojiSymbol.length());
            }

            for(String emoji : emojis) {
                message = message.replaceAll(emoji, toUnicode(emoji));
            }
        }

        return message;
    }

    protected String toUnicode(String emoji) {

        return String.valueOf(Character.toChars(Integer.decode(emoji)));
    }

}
