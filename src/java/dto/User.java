package dto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("user")
@RequestScoped
public class User{
    private int id;
    private int identification;
    private String fullName;
    private String address;
    private Boolean flagDatacredito;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getFlagDatacredito() {
        return flagDatacredito;
    }

    public void setFlagDatacredito(Boolean flagDatacredito) {
        this.flagDatacredito = flagDatacredito;
    }
    
}