package com.phisafe.audit;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.*;
import org.springframework.stereotype.Service;
/** Append-only log; each entry's hash covers its content + the previous hash (tamper-evident). */
@Service
public class AuditService {
  static final String GENESIS="0".repeat(64);
  private final List<AuditEntry> log=new ArrayList<>();
  public synchronized AuditEntry record(String user,String action,String resource){
    String prev=log.isEmpty()?GENESIS:log.get(log.size()-1).hash(); long i=log.size(); String ts=Instant.now().toString();
    AuditEntry e=new AuditEntry(i,ts,user,action,resource,prev,hash(i,ts,user,action,resource,prev)); log.add(e); return e;
  }
  public synchronized List<AuditEntry> entries(){ return log; }
  public synchronized Map<String,Object> verify(){
    String prev=GENESIS;
    for(AuditEntry e: log){
      String expect=hash(e.index(),e.timestamp(),e.user(),e.action(),e.resource(),e.prevHash());
      if(!e.prevHash().equals(prev)||!e.hash().equals(expect)) return Map.of("valid",false,"brokenAtIndex",e.index(),"entries",log.size());
      prev=e.hash();
    }
    return Map.of("valid",true,"entries",log.size());
  }
  static String hash(long i,String ts,String u,String a,String r,String prev){
    try{ byte[] d=MessageDigest.getInstance("SHA-256").digest((i+"|"+ts+"|"+u+"|"+a+"|"+r+"|"+prev).getBytes(StandardCharsets.UTF_8));
      StringBuilder sb=new StringBuilder(); for(byte b:d) sb.append(String.format("%02x",b)); return sb.toString();
    }catch(Exception ex){ throw new IllegalStateException(ex); }
  }
}