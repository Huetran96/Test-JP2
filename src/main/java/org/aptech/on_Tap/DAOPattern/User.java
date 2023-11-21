package org.aptech.on_Tap.DAOPattern;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder

public class User {
    private int id;
    private String username;
    private String password;
    private int status;
    private int loginFail;
    private Date createAt;
    private Date updateAt;
    private String createBy;
    private String updateBy;

}
