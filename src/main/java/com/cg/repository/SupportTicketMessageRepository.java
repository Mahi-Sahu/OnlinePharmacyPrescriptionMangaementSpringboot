package com.cg.repository;

import com.cg.entity.SupportTicketMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportTicketMessageRepository extends JpaRepository<SupportTicketMessage,Long> {
}
