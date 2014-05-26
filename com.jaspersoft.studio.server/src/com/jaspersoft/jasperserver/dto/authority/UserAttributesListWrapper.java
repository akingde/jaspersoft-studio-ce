package com.jaspersoft.jasperserver.dto.authority;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zakhar.Tomchenco
 */
@XmlRootElement(name = "attributes")
public class UserAttributesListWrapper {
    private List<ClientUserAttribute> profileAttributes;

    public UserAttributesListWrapper(){}

    public UserAttributesListWrapper(List<ClientUserAttribute> attributes){
        profileAttributes = new ArrayList<ClientUserAttribute>(attributes.size());
        for (ClientUserAttribute r : attributes){
            profileAttributes.add((ClientUserAttribute)r);
        }
    }

    @XmlElement(name = "attribute")
    public List<ClientUserAttribute> getProfileAttributes() {
        return profileAttributes;
    }

    public void setProfileAttributes(List<ClientUserAttribute> profileAttributes) {
        this.profileAttributes = profileAttributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAttributesListWrapper that = (UserAttributesListWrapper) o;

        if (profileAttributes != null ? !profileAttributes.equals(that.profileAttributes) : that.profileAttributes != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return profileAttributes != null ? profileAttributes.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserAttributesListWrapper{" +
                "profileAttributes=" + profileAttributes +
                '}';
    }
}
