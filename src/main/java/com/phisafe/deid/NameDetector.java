package com.phisafe.deid;
import org.springframework.stereotype.Component;
@Component
public class NameDetector extends RegexDetector { public NameDetector(){ super("\\b(?:Dr\\.|Mr\\.|Mrs\\.|Ms\\.|Patient|Pt\\.?)\\s+[A-Z][a-z]+(?:\\s+[A-Z][a-z]+)?","NAME"); } }