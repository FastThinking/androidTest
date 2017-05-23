package com.allen.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by allen on 2017/5/18.
 */
@Entity(createInDb = false)
public class Note {
    @Id(autoincrement = true)
    private Long id;
    private String userUuid;
    private String noteTitle;

    @Generated(hash = 475372524)
    public Note(Long id, String userUuid) {
        this.id = id;
        this.userUuid = userUuid;
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
}
