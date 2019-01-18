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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "lti_result")
public class LtiResultEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "result_id", nullable = false, insertable = true, updatable = true)
    private long resultId;
    @Basic
    @Column(name = "sourcedid", nullable = false, insertable = true, updatable = true, length = 4096)
    private String sourcedid;
    @Basic
    @Column(name = "grade", nullable = true, insertable = true, updatable = true, precision = 0)
    private Float grade;
    @Basic
    @Column(name = "note", nullable = true, insertable = true, updatable = true, length = 4096)
    private String note;
    @Basic
    @Column(name = "server_grade", nullable = true, insertable = true, updatable = true, precision = 0)
    private Float serverGrade;
    @Basic
    @Column(name = "json", nullable = true, insertable = true, updatable = true, length = 65535)
    private String json;
    @Basic
    @Column(name = "retrieved_at", nullable = false, insertable = true, updatable = true)
    private Timestamp retrievedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "link_id")
    private LtiLinkEntity link;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private LtiUserEntity user;

    protected LtiResultEntity() {
    }

    /**
     * @param sourcedid   the external key sourcedid
     * @param user        the user for this grade result
     * @param link        the link which this is a grade for
     * @param retrievedAt the date the grade was retrieved (null indicates now)
     * @param grade       [OPTIONAL] the grade value
     */
    public LtiResultEntity(String sourcedid, LtiUserEntity user, LtiLinkEntity link, Date retrievedAt, Float grade) {
        if (!StringUtils.isNotBlank(sourcedid)) throw new AssertionError();
        if (user == null) throw new AssertionError();
        if (link == null) throw new AssertionError();
        if (retrievedAt == null) {
            retrievedAt = new Date();
        }
        this.sourcedid = sourcedid;
        this.retrievedAt = new Timestamp(retrievedAt.getTime());
        this.user = user;
        this.link = link;
        this.grade = grade;
    }

    public long getResultId() {
        return resultId;
    }

    public void setResultId(long resultId) {
        this.resultId = resultId;
    }

    public String getSourcedid() {
        return sourcedid;
    }

    public void setSourcedid(String sourcedid) {
        this.sourcedid = sourcedid;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Float getServerGrade() {
        return serverGrade;
    }

    public void setServerGrade(Float serverGrade) {
        this.serverGrade = serverGrade;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Timestamp getRetrievedAt() {
        return retrievedAt;
    }

    public void setRetrievedAt(Timestamp retrievedAt) {
        this.retrievedAt = retrievedAt;
    }

    public LtiLinkEntity getLink() {
        return link;
    }

    public void setLink(LtiLinkEntity link) {
        this.link = link;
    }

    public LtiUserEntity getUser() {
        return user;
    }

    public void setUser(LtiUserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LtiResultEntity that = (LtiResultEntity) o;

        if (resultId != that.resultId) return false;
        return sourcedid != null ? sourcedid.equals(that.sourcedid) : that.sourcedid == null;
    }

    @Override
    public int hashCode() {
        int result = (int) resultId;
        return 31 * result + (sourcedid != null ? sourcedid.hashCode() : 0);
    }

}
