package com.example.hideoutshop.repository.member;

import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {


     Boolean existsByEmail(String email);

    Optional<Member> findByUserId(String email);

    Optional<Member> findAllByEmail(String email);
}
