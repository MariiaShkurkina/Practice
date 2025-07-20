package com.example.task5.Repository;

import com.example.task5.Entity.Review;
import com.example.task5.Entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
