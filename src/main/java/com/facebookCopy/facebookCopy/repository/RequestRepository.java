package com.facebookCopy.facebookCopy.repository;

import com.facebookCopy.facebookCopy.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request,Integer> {
}
