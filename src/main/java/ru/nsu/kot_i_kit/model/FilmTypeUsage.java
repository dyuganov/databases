package ru.nsu.kot_i_kit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmTypeUsage{
    private String name;
    private Integer iso;
    private String usage;
}
