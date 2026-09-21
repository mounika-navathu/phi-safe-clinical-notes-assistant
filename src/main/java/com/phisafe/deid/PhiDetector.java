package com.phisafe.deid;
import java.util.List;
/** Strategy: each implementation detects one kind of identifier. */
public interface PhiDetector { List<PhiSpan> detect(String text); }