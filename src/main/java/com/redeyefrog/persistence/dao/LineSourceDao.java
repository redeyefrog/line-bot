package com.redeyefrog.persistence.dao;

import com.redeyefrog.persistence.entity.LineSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LineSourceDao extends JpaRepository<LineSourceEntity, Long> {

    @Query(value = "SELECT ID FROM LINE_SOURCE WHERE TYPE = ?1 GROUP BY ID", nativeQuery = true)
    List<String> findAllId(String id);

    LineSourceEntity getById(String id);

}
