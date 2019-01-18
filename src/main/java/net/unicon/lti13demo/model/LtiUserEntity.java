/**
 * Copyright 2019 Unicon (R)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.unicon.lti13demo.model;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "lti_user")
public class LtiUserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    private long userId;
    @Basic
    @Column(name = "user_key", nullable = false, insertable = true, updatable = true, length = 4096)
    private String userKey;
    @Basic
    @Column(name = "displayname", nullable = true, insertable = true, updatable = true, length = 4096)
    private String displayName;
    /**
     * Actual max for emails is 254 chars
     */
    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 255)
    private String email;
    @Basic
    @Column(name = "locale", nullable = true, insertable = true, updatable = true, length = 63)
    private String locale;
    @Basic
    @Column(name = "subscribe", nullable = true, insertable = true, updatable = true)
    private Short subscribe;
    @Basic
    @Column(name = "json", nullable = true, insertable = true, updatable = true, length = 65535)
    private String json;
    @Basic
    @Column(name = "login_at", nullable = false, insertable = true, updatable = true)
    private Timestamp loginAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "profile_id", nullable = true)
    private ProfileEntity profile;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<LtiResultEntity> results;

    protected LtiUserEntity() {
    }

    /**
     * @param userKey user identifier
     * @param loginAt date of user login
     */
    public LtiUserEntity(String userKey, Date loginAt) {
        if (!StringUtils.isNotBlank(userKey)) throw new AssertionError();
        if (loginAt == null) {
            loginAt = new Date();
        }
        this.userKey = userKey;
        this.loginAt = new Timestamp(loginAt.getTime());
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Short getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Short subscribe) {
        this.subscribe = subscribe;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Timestamp getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Timestamp loginAt) {
        this.loginAt = loginAt;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    public Set<LtiResultEntity> getResults() {
        return results;
    }

    public void setResults(Set<LtiResultEntity> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LtiUserEntity that = (LtiUserEntity) o;

        if (userId != that.userId) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return userKey != null ? userKey.equals(that.userKey) : that.userKey == null;
    }

    @Override
    public int hashCode() {
        int result = (int) userId;
        result = 31 * result + (userKey != null ? userKey.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

}
