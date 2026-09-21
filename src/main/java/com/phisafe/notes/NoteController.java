package com.phisafe.notes;
import com.phisafe.audit.AuditService;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/notes")
public class NoteController {
  private final NoteService notes; private final AuditService audit;
  public NoteController(NoteService n, AuditService a){ notes=n; audit=a; }
  @PostMapping public Map<String,String> create(@RequestBody Map<String,String> body, Authentication a){
    String id=notes.add(body.getOrDefault("text","")); audit.record(a.getName(),"CREATE_NOTE",id); return Map.of("id",id);
  }
  @GetMapping("/{id}") public ResponseEntity<Map<String,String>> raw(@PathVariable String id, Authentication a){
    audit.record(a.getName(),"READ_RAW_NOTE",id); String t=notes.raw(id);
    return t==null?ResponseEntity.notFound().build():ResponseEntity.ok(Map.of("id",id,"text",t));
  }
  @GetMapping("/{id}/deidentified") public ResponseEntity<Map<String,String>> masked(@PathVariable String id, Authentication a){
    audit.record(a.getName(),"READ_DEID_NOTE",id); String t=notes.deidentified(id);
    return t==null?ResponseEntity.notFound().build():ResponseEntity.ok(Map.of("id",id,"text",t));
  }
}