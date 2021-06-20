package com.jpa.thom.repositoires;

import com.jpa.thom.models.Kommune;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KommuneRepository extends CrudRepository<Kommune, Long> {

}
