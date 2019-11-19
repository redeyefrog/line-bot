package com.redeyefrog.transaction;

import com.linecorp.bot.model.event.source.GroupSource;
import com.linecorp.bot.model.event.source.RoomSource;
import com.linecorp.bot.model.event.source.Source;
import com.redeyefrog.enums.Sources;
import com.redeyefrog.persistence.dao.LineSourceDao;
import com.redeyefrog.persistence.entity.LineSourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class LineTransaction {

    @Autowired
    private LineSourceDao lineSource;

    @Transactional
    public void saveLineSource(Source source) {
        LineSourceEntity entity = getEntity(source);

        if(entity.getSeq() == null) {
            lineSource.save(entity);
        }
    }

    private LineSourceEntity getEntity(Source source) {
        String id;

        Sources sources;

        if(source instanceof GroupSource) {
            id = ((GroupSource) source).getGroupId();

            sources = Sources.GROUP;
        } else if(source instanceof RoomSource) {
            id = ((RoomSource) source).getRoomId();

            sources = Sources.ROOM;
        } else {
            id = source.getUserId();

            sources = Sources.USER;
        }

        LineSourceEntity entity = lineSource.getById(id);

        if(entity == null) {
            entity = new LineSourceEntity();

            entity.setId(id);

            entity.setType(sources.getCode());
        }

        return entity;
    }

}
