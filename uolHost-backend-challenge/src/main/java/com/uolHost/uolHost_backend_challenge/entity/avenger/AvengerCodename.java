package com.uolHost.uolHost_backend_challenge.entity.avenger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvengerCodename {

    @JsonProperty("codinome")
    private String codename;

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

}
