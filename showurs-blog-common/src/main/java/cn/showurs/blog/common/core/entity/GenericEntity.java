package cn.showurs.blog.common.core.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class GenericEntity<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;

    protected ID createId;

    protected LocalDateTime createTime;

    protected ID updateId;

    protected LocalDateTime updateTime;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getCreateId() {
        return createId;
    }

    public void setCreateId(ID createId) {
        this.createId = createId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public ID getUpdateId() {
        return updateId;
    }

    public void setUpdateId(ID updateId) {
        this.updateId = updateId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
