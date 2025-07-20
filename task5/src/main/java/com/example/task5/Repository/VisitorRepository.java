package com.example.task5.Repository;

import com.example.task5.Entity.Visitor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class VisitorRepository {
    private final List<Visitor> visitors = new ArrayList<>();

    public void save(Visitor visitor) {
        visitors.add(visitor);
    }

    public Optional<Visitor> findById(Long id) {
        return visitors.stream()
                .filter(v -> Objects.equals(v.getId(), id))
                .findFirst();
    }

    public void remove(Visitor visitor) {
        visitors.remove(visitor);
    }


    public List<Visitor> findAll() {
        return Collections.unmodifiableList(visitors); // безопасный список только для чтения
    }


}
