package com.phisafe.audit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class AuditServiceTest {
  @Test void chainVerifiesWhenUntouched(){
    AuditService a=new AuditService(); a.record("doc","READ","n1"); a.record("doc","READ","n2"); a.record("admin","VERIFY","-");
    assertEquals(true,a.verify().get("valid"));
  }
  @Test void detectsTampering(){
    AuditService a=new AuditService(); a.record("doc","READ","n1"); a.record("doc","READ","n2"); a.record("doc","READ","n3");
    AuditEntry e=a.entries().get(1);
    a.entries().set(1,new AuditEntry(e.index(),e.timestamp(),"someone-else",e.action(),e.resource(),e.prevHash(),e.hash()));
    assertEquals(false,a.verify().get("valid")); assertEquals(1L,a.verify().get("brokenAtIndex"));
  }
}