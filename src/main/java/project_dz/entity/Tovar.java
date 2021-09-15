package project_dz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Tovar {
    private Long id;
    private String name;
    private String description;
    private Date create_date;
    private Double place_storage;
    private Boolean reserved;
}