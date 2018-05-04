package com.example.biba.Model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    @Pattern(regexp = "[a-zA-Z]{1}[a-zA-Z\\d\\u002E\\u005F]+@([a-zA-Z]+\\u002E){1,2}((net)|(com)|(org)|(kz))",message = "Error email")
    private String email;

    @ManyToMany
    private List<Mma> mmas;

    public Box(){
        super();
    }

    public Box(int id, String firstName, String lastName, String email, List<Mma> mmas){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.mmas = mmas;
    }
    public Box(String firstName, String lastName, String email, List<Mma> mmas){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.mmas = mmas;
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

    public List<Mma> getMmas() {
        return mmas;
    }
    public void setMma(List<Mma> mmas){this.mmas= mmas;}

    public boolean hasMma(Mma mma){
        for(Mma containedMma: getMmas()) {
            if (containedMma.getId() == mma.getId() ){
                return true;
            }
        }
        return false;
    }
}
