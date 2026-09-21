package com.phisafe.rag;
import com.phisafe.audit.AuditService;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/assistant")
public class AssistantController {
  private final RagService rag; private final AuditService audit;
  public AssistantController(RagService r,AuditService a){ rag=r; audit=a; }
  @PostMapping("/ask") public RagService.Answer ask(@RequestBody Map<String,String> b,Authentication a){
    audit.record(a.getName(),"ASSISTANT_QUERY","-"); return rag.ask(b.getOrDefault("question",""));
  }
}