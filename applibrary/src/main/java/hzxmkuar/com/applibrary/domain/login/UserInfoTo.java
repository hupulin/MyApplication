package hzxmkuar.com.applibrary.domain.login;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Administrator on 2018/9/11.
 */
@Data
public class UserInfoTo implements Serializable {
    private int oauth_id;
    private int is_store;
    private int is_member;
    private int uid;
    private int face;
    private String hashid;

    private String mobile;
    private String face_url;
    private String userName="昵称";
    private String userImg="http://118.190.201.28:8080/img/timg.jpg";



}
