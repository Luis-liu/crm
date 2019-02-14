package entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.DateUtil;

import java.util.Objects;

public class Member {

    private IntegerProperty userId = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty createDate = new SimpleStringProperty();

    public Member() {

    }

    public Member(String name, String phone) {
        this.userId = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.createDate = new SimpleStringProperty(DateUtil.getNowString());
    }

    public Member(int id, String name, String phone) {
        this.userId = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.createDate = new SimpleStringProperty(DateUtil.getNowString());
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty createDateProperty() {
        return createDate;
    }

    public Integer getUserId() {
        return userId.get();
    }

    public void setUserId(Integer userId) {
        this.userId.set(userId);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Member)) {
            return false;
        }
        if (((Member) o).name == null) {
            return false;
        }
        Member member = (Member) o;
        return Objects.equals(name.toString(), member.name.toString());
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
