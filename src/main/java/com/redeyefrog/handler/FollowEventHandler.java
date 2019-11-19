package com.redeyefrog.handler;

import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.source.GroupSource;
import com.linecorp.bot.model.event.source.RoomSource;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.redeyefrog.transaction.LineTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowEventHandler extends CommonHandler {

    @Autowired
    private LineTransaction transaction;

    public Message getReplyMessage(FollowEvent event) {
        StringBuilder sb = new StringBuilder();

        Source source = event.getSource();

        try {
            UserProfileResponse profile;

            if(source instanceof GroupSource) {
                String id = ((GroupSource) source).getGroupId();

                profile = client.getGroupMemberProfile(id, source.getUserId()).get();
            } else if(source instanceof RoomSource) {
                String id = ((RoomSource) source).getRoomId();

                profile = client.getRoomMemberProfile(id, source.getUserId()).get();
            } else {
                String id = source.getUserId();

                profile = client.getProfile(id).get();
            }

            transaction.saveLineSource(source);

            sb.append(profile.getDisplayName()).append("感謝您將本帳號加入好友").appendCodePoint(Integer.decode("0x100001")).append(System.lineSeparator()).append("Happy New Year!!").appendCodePoint(Integer.decode("0x10002D"));
        } catch(Exception e) {
            sb.append("有錯誤!!").appendCodePoint(Integer.decode("0x10007D")).append(System.lineSeparator()).append(e.getMessage());
        }

        return getReplyMessage(sb.toString());
    }

}
