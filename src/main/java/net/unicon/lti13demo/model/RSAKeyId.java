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

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RSAKeyId implements Serializable {

    @Column(name = "kid")
    private String kid;

    @Column(name = "tool")
    private boolean tool;

    public RSAKeyId() {
    }

    /**
     * @param kid  the key id
     * @param tool  if tool or platform key
     */
    public RSAKeyId(String kid, Boolean tool) {
        this.kid=kid;
        this.tool = tool;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public boolean getTool() {
        return tool;
    }

    public void setTool(boolean tool) {
        this.tool = tool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RSAKeyId that = (RSAKeyId) o;

        return Objects.equals(getKid(), that.getKid()) &&
                Objects.equals(getTool(), that.getTool());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKid(), getTool());
    }

}
