package ru.itis;

import lombok.Data;

import java.util.List;

/**
 * @author Roman Leontev
 * 19:15 12.01.2021
 * group 11-905
 */

@Data
public class Form {

    private List<Input> inputs;
    private String method;
    private String action;
}
