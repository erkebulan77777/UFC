package com.example.biba.Model;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Samsa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    @Pattern(regexp = "[a-zA-Z]{1}[a-zA-Z\\d\\u002E\\u005F]+@([a-zA-Z]+\\u002E){1,2}((net)|(com)|(org)|(kz))",message = "Error email")
    private String email;

    @ManyToMany
    private List<Tort> torts;

    public Samsa(){
        super();
    }

    public Samsa(int id, String firstName, String lastName, String email, List<Tort> torts){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.torts = torts;
    }
    public Samsa(String firstName, String lastName, String email, List<Tort> torts){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.torts = torts;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public List<Tort> getTorts() {
        return torts;
    }
    public void setTort(List<Tort> torts){
        this.torts = torts;
    }

    public boolean hasTort(Tort tort){
        for(Tort containedTort : getTorts()) {
            if (containedTort.getId() == tort.getId() ){
                return true;
            }
        }
        return false;
    }
}