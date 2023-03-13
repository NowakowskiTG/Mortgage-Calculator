package com.project.service;

import com.project.model.Rate;
import com.project.model.Summary;

import java.util.List;

public interface SummaryService {

    Summary calculate(List<Rate> rates);
}
