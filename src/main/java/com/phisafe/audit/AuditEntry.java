package com.phisafe.audit;
public record AuditEntry(long index, String timestamp, String user, String action, String resource, String prevHash, String hash) {}