package com.bluntpin.blunterest.Repository;

import com.bluntpin.blunterest.Model.Pin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestController
public interface PinRepository extends JpaRepository<Pin, Integer> {
}
