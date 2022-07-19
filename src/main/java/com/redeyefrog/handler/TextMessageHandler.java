package com.redeyefrog.handler;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.TextMessage;
import com.redeyefrog.enums.Symbols;
import com.redeyefrog.persistence.dao.LineSourceDao;
import com.redeyefrog.persistence.entity.LineMessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see <a href="https://developers.line.biz/media/messaging-api/emoji-list.pdf">emoji</a>
 */
@Service
public class TextMessageHandler extends CommonHandler {

    @Autowired
    private LineSourceDao lineSourceDao;

    public TextMessage getReplyMessage(MessageEvent<TextMessageContent> event) {
        if (isReply(event.getSource())) {
            List<LineMessageEntity> entities = findAllReplyMessage();

            return getReplyMessage(composeMessage(event.getMessage().getText(), entities));
        }

        return null;
    }

    private boolean isReply(Source source) {
        String userId = source.getUserId();

        return lineSourceDao.countByIdAndIsReply(userId) > 0;
    }

    private List<LineMessageEntity> findAllReplyMessage() {
        List<LineMessageEntity> entities = new ArrayList<>();

        String equalSymbol = Symbols.EQUAL.getValue();

        lineMessageDao.findAll().stream().forEach(entity -> {
            String keyword = entity.getKeyword();

            if (entity.getKeyword().contains(equalSymbol)) {
                Stream.of(keyword.split(equalSymbol)).forEach(newKeyword -> {
                    LineMessageEntity newEntity = new LineMessageEntity();

                    newEntity.setKeyword(newKeyword);
                    newEntity.setReplyMessage(entity.getReplyMessage());

                    entities.add(newEntity);
                });
            } else {
                entities.add(entity);
            }
        });

        return entities;
    }

    private String composeMessage(String message, List<LineMessageEntity> entities) {
        for (LineMessageEntity entity : entities) {
            String keyword = entity.getKeyword();

            if (message.contains(keyword)) {

                return composeReplyMessage(message, entity.getReplyMessage()).replaceAll("\\\\r\\\\n", System.lineSeparator());
            }
        }

        return MessageFormat.format("{0}Echo: [{1}]", toUnicode("0x100009"), message);
    }

    private String composeReplyMessage(String message, String replyMessage) {
        if (message.contains(Symbols.OPEN_BRACKET.getValue()) && message.contains(Symbols.CLOSE_BRACKET.getValue())) {

            return composeSearchMessage(message, replyMessage);
        } else {

            return composeEmoji(replyMessage);
        }
    }

    private String composeSearchMessage(String message, String replyMessage) {
        String openBracket = Symbols.OPEN_BRACKET.getValue();
        String closeBracket = Symbols.CLOSE_BRACKET.getValue();

        if (message.contains(openBracket) && message.contains(closeBracket)) {
            int beginIdx = message.indexOf(openBracket) + 1;
            int endIdx = message.indexOf(closeBracket);

            return MessageFormat.format(replyMessage, message.substring(beginIdx, endIdx));
        }

        return replyMessage;
    }

}
