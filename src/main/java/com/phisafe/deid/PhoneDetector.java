package com.phisafe.deid;
import org.springframework.stereotype.Component;
@Component
public class PhoneDetector extends RegexDetector { public PhoneDetector(){ super("(?:\\+?1[-. ]?)?\\(?\\d{3}\\)?[-. ]\\d{3}[-. ]\\d{4}","PHONE"); } }