package com.cg.repository;

import com.cg.entity.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
    @Query("""
        select st from SupportTicket st
        where st.user.userId=:userId
        """)
    List<SupportTicket> findByUserId(@Param("userId") Long userId);
}
