package com.expensecalculator.shared.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse implements Serializable {
    private String errorMessage;
    private String requestDescription;
}
