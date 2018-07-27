package com.react.example.cardatabase.domain;

import com.react.example.cardatabase.domain.entity.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
