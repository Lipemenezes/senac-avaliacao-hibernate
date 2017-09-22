/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Felipe Menezes
 */
@Entity
@Table(name = "legal_person")
@PrimaryKeyJoinColumn
public class LegalPerson extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cnpj; 
    private String stateRegistration;

    public LegalPerson() {
    
    }

    public LegalPerson(String cnpj, String stateRegistration, Long id, String name, String phone, String email) {
        super(id, name, phone, email);
        this.cnpj = cnpj;
        this.stateRegistration = stateRegistration;
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getStateRegistration() {
        return stateRegistration;
    }

    public void setStateRegistration(String stateRegistration) {
        this.stateRegistration = stateRegistration;
    }

    @Override
    public String toString() {
        return "LegalPerson{" + "cnpj=" + cnpj + ", stateRegistration=" + stateRegistration + '}';
    }
    
}
