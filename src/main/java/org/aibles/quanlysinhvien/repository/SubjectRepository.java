package org.aibles.quanlysinhvien.repository;


import org.aibles.quanlysinhvien.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}

