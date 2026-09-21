package com.phisafe;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import com.phisafe.notes.NoteService;
import com.phisafe.rag.RagService;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class RagServiceTest {
  @Mock NoteService notes; @InjectMocks RagService rag;
  @Test void answersFromDeidentifiedNotesOnly(){
    when(notes.allDeidentified()).thenReturn(Map.of("n1","[NAME] has hypertension, lisinopril started.","n2","[NAME] has asthma."));
    RagService.Answer a=rag.ask("Who has hypertension?");
    assertTrue(a.sources().contains("n1")); assertFalse(a.sources().contains("n2"));
    verify(notes,never()).raw(any());
  }
  @Test void handlesNoMatch(){ when(notes.allDeidentified()).thenReturn(Map.of()); assertTrue(rag.ask("fracture").sources().isEmpty()); }
}