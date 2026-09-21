package com.phisafe.deid;
import java.util.*;
import org.springframework.stereotype.Service;
/** Runs every detector strategy, resolves overlaps, and masks identifiers. */
@Service
public class DeidentificationService {
  private final List<PhiDetector> detectors;
  public DeidentificationService(List<PhiDetector> detectors){ this.detectors=detectors; }
  public String mask(String text){
    List<PhiSpan> spans=new ArrayList<>();
    for(PhiDetector d: detectors) spans.addAll(d.detect(text));
    spans.sort(Comparator.comparingInt(PhiSpan::start).thenComparing(s->-(s.end()-s.start())));
    StringBuilder sb=new StringBuilder(); int pos=0;
    for(PhiSpan s: spans){
      if(s.start()<pos) continue;
      sb.append(text, pos, s.start()).append('[').append(s.label()).append(']'); pos=s.end();
    }
    return sb.append(text.substring(pos)).toString();
  }
}