package cn.bangnongmang.data.domain;

public class FriendshipSpecial {
    private Long id;

    private Long belongto;

    private Long friend_uid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBelongto() {
        return belongto;
    }

    public void setBelongto(Long belongto) {
        this.belongto = belongto;
    }

    public Long getFriend_uid() {
        return friend_uid;
    }

    public void setFriend_uid(Long friend_uid) {
        this.friend_uid = friend_uid;
    }
}