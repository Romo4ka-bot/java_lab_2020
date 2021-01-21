package ru.itis;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Roman Leontev
 * 19:15 12.01.2021
 * group 11-905
 */

@Data
@AllArgsConstructor
public class Input implements Serializable {
    private String type;
    private String name;
    private String placeholder;
}
