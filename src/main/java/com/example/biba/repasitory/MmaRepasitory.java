package com.example.biba.repasitory;

import com.example.biba.Model.Mma;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MmaRepasitory extends CrudRepository <Mma,Integer> {
    List<Mma> findByLabel(String Label);
}

