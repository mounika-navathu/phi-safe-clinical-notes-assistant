package com.phisafe.rag;
import com.phisafe.notes.NoteService;
import java.util.*;
import java.util.stream.*;
import org.springframework.stereotype.Service;
/** Lightweight retrieval (keyword overlap). Only ever reads de-identified text; swap in an LLM for generation. */
@Service
public class RagService {
  public record Answer(String answer, List<String> sources) {}
  private final NoteService notes; public RagService(NoteService n){ notes=n; }
  static Set<String> tokens(String s){ return Arrays.stream(s.toLowerCase().split("\W+")).filter(w->w.length()>3).collect(Collectors.toSet()); }
  public Answer ask(String question){
    Set<String> q=tokens(question); Map<String,String> docs=notes.allDeidentified();
    List<Map.Entry<String,String>> top=docs.entrySet().stream()
      .filter(e->tokens(e.getValue()).stream().anyMatch(q::contains))
      .sorted(Comparator.comparingLong((Map.Entry<String,String> e)->-tokens(e.getValue()).stream().filter(q::contains).count()))
      .limit(2).toList();
    if(top.isEmpty()) return new Answer("No relevant information found in de-identified notes.",List.of());
    return new Answer("Based on de-identified notes:
"+top.stream().map(e->"- "+e.getValue()).collect(Collectors.joining("
")),
      top.stream().map(Map.Entry::getKey).toList());
  }
}