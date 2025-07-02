package com.boardv4admin.repository;

import com.boardv4admin.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberRepository {
    Optional<Member> findByUsername(String username);

    Optional<Member> findById(Long id);

    Optional<Member> findAdminByUsername(String username);
}
