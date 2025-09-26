package com.onlinevoting.service;

import com.onlinevoting.entity.AuditLog;
import java.util.List;

public interface AuditLogService {
    void logAction(String action, Long userId, Long electionId);
    List<AuditLog> getAllLogs();
}