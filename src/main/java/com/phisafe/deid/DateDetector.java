package com.phisafe.deid;
import org.springframework.stereotype.Component;
@Component
public class DateDetector extends RegexDetector { public DateDetector(){ super("\\b(?:\\d{1,2}/\\d{1,2}/\\d{2,4}|\\d{4}-\\d{2}-\\d{2})\\b","DATE"); } }