package cn.showurs.blog.common.vo.common;

import java.io.Serializable;

public class GenericValue<ID extends Serializable> {

    protected ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
