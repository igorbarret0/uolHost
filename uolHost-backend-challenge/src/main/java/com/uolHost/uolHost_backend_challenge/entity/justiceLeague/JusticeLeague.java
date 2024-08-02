package com.uolHost.uolHost_backend_challenge.entity.justiceLeague;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "liga_da_justica")
@XmlType(propOrder = {"codenames"})
public class JusticeLeague {

    private Codenames codenames;

    @XmlElement(name = "codinomes")
    public Codenames getCodenames() {
        return codenames;
    }

    public void setCodenames(Codenames codenames) {
        this.codenames = codenames;
    }

    @XmlType(propOrder = {"codename"})
    public static class Codenames {
        private List<String> codename;

        @XmlElement(name = "codinome")
        public List<String> getCodename() {
            return codename;
        }

        public void setCodename(List<String> codename) {
            this.codename = codename;
        }
    }
}
