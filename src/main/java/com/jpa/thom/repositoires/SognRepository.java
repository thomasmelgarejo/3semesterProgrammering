package com.jpa.thom.repositoires;

import com.jpa.thom.models.Sogn;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SognRepository extends CrudRepository<Sogn, Long> {

}
