package com.example.biba.repasitory;

import org.springframework.data.repository.CrudRepository;
import com.example.biba.Model.Tort;

import java.util.List;

public interface TortRepasitory extends CrudRepository <Tort,Integer> {
    List<Tort> findByLabel(String Label);
}

