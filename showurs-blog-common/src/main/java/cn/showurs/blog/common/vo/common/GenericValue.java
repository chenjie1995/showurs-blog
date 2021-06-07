package cn.showurs.blog.common.vo.common;

public class GenericValue<ID> {

    protected ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
