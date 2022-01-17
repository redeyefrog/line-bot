package com.redeyefrog.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "LINE_MESSAGE")
@Entity
public class LineMessageEntity implements Serializable {

    @Id
    @Column(name = "keyword")
    private String keyword;

    @Column(name = "reply_message")
    private String replyMessage;

}
