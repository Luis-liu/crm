package entry;

import javafx.beans.property.SimpleStringProperty;
import util.DateUtil;

import java.time.format.DateTimeFormatter;

public class Member {

    private Integer userId;
    private SimpleStringProperty name;
    private SimpleStringProperty phone;
    private SimpleStringProperty createDate;

    public Member() {

    }

    public Member(String name, String phone) {
        this.name.set(name);
        this.phone.set(phone);
        this.createDate.set(DateUtil.getNowString());
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getCreateDate() {
        return createDate.get();
    }

    public void setCreateDate(String createDate) {
        this.createDate.set(createDate);
    }
}
