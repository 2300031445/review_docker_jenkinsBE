package com.onlinevoting.service.impl;

import com.onlinevoting.entity.AuditLog;
import com.onlinevoting.repository.AuditLogRepository;
import com.onlinevoting.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public void logAction(String action, Long userId, Long electionId) {
        AuditLog log = AuditLog.builder()
                .action(action)
                .timestamp(LocalDateTime.now())
                .build();
        // Set user and election if needed
        auditLogRepository.save(log);
    }

    @Override
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }
}
