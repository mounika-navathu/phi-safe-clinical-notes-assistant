package com.phisafe;
import static org.junit.jupiter.api.Assertions.*;
import com.phisafe.deid.*;
import java.util.List;
import org.junit.jupiter.api.Test;
class DeidentificationServiceTest {
  private final DeidentificationService svc=new DeidentificationService(List.of(new NameDetector(),new DateDetector(),new PhoneDetector(),new IdDetector()));
  @Test void masksAllIdentifierTypes(){
    String out=svc.mask("Patient John Smith, DOB 03/14/1975, phone 555-123-4567, MRN: 884213, SSN 123-45-6789.");
    for(String leaked: List.of("John","Smith","03/14/1975","555-123-4567","884213","123-45-6789")) assertFalse(out.contains(leaked),leaked);
    assertTrue(out.contains("[NAME]")&&out.contains("[DATE]")&&out.contains("[PHONE]")&&out.contains("[ID]"));
  }
  @Test void keepsClinicalContent(){ assertEquals("Started lisinopril for hypertension.",svc.mask("Started lisinopril for hypertension.")); }
}