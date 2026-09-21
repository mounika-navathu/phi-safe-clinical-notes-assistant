package com.phisafe.deid;
import org.springframework.stereotype.Component;
@Component
public class IdDetector extends RegexDetector { public IdDetector(){ super("\\b(?:MRN|ID)[:# ]*\\d{4,}\\b|\\b\\d{3}-\\d{2}-\\d{4}\\b","ID"); } }