package cn.showurs.blog.user.exception;

/**
 * Created by CJ on 2018/9/12 15:10.
 */
public class NoDataException extends BusinessException {
    private static final long serialVersionUID = 6616521765804330937L;

    public NoDataException() {
        super(404, "没有找到数据");
    }
}
