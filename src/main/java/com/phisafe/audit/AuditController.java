package com.phisafe.audit;
import java.util.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/audit")
public class AuditController {
  private final AuditService audit; public AuditController(AuditService a){ audit=a; }
  @GetMapping("/verify") public Map<String,Object> verify(){ return audit.verify(); }
  @GetMapping public List<AuditEntry> all(){ return List.copyOf(audit.entries()); }
}