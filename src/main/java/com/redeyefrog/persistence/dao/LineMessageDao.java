package com.redeyefrog.persistence.dao;

import com.redeyefrog.persistence.entity.LineMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineMessageDao extends JpaRepository<LineMessageEntity, String> {

    LineMessageEntity getByKeyword(String keyword);

}
