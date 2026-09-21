package com.phisafe.deid;
import java.util.*;
import java.util.regex.*;
public abstract class RegexDetector implements PhiDetector {
  private final Pattern pattern; private final String label;
  protected RegexDetector(String regex, String label){ this.pattern=Pattern.compile(regex); this.label=label; }
  @Override public List<PhiSpan> detect(String text){
    List<PhiSpan> out=new ArrayList<>(); Matcher m=pattern.matcher(text);
    while(m.find()) out.add(new PhiSpan(m.start(), m.end(), label));
    return out;
  }
}