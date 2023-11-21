package org.aptech.on_Tap.DAOPattern.Const;

public enum UserStatus {
    ACTIVE(1),
    INACTIVE(2),
    DELETED(3),
    BLOCKED(4);
    public final int status;
    private UserStatus(int status){
        this.status = status;
    }
}
