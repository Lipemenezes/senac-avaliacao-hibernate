/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Felipe Menezes
 */
@Entity
@Table(name = "natural_person")
@PrimaryKeyJoinColumn
public class NaturalPerson extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String cpf;
    private String rg;

    public NaturalPerson() {
    }

    public NaturalPerson(String cpf, String rg, Long id, String name, String phone, String email) {
        super(id, name, phone, email);
        this.cpf = cpf;
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @Override
    public String toString() {
        return "NaturalPerson{" + "cpf=" + cpf + ", rg=" + rg + '}';
    }
    
    
    
}
