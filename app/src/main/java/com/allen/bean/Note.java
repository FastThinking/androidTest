package com.allen.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by allen on 2017/5/18.
 */
@Entity(createInDb = true)
public class Note {
    @Id(autoincrement = true)
    private Long id;
    private String userUuid;
    private String noteTitle;

    @Generated(hash = 535473277)
    public Note(Long id, String userUuid, String noteTitle) {
        this.id = id;
        this.userUuid = userUuid;
        this.noteTitle = noteTitle;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserUuid() {
        return this.userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getNoteTitle() {
        return this.noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }
}
