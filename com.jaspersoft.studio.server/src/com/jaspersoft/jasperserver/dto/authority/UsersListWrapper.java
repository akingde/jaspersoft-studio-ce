package com.jaspersoft.jasperserver.dto.authority;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zakhar.Tomchenco
 */

@XmlRootElement(name = "users")
public class UsersListWrapper {
    private List<ClientUser> userList;

    public UsersListWrapper(){}

    public UsersListWrapper(List<ClientUser> users){
        userList = new ArrayList<ClientUser>(users.size());
        for (ClientUser r : users){
            userList.add(r);
        }
    }

    @XmlElement(name = "user")
    public List<ClientUser> getUserList() {
        return userList;
    }

    public void setUserList(List<ClientUser> users) {
        this.userList = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersListWrapper that = (UsersListWrapper) o;

        if (userList != null ? !userList.equals(that.userList) : that.userList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userList != null ? userList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UsersListWrapper{" +
                "userList=" + userList +
                '}';
    }
}
