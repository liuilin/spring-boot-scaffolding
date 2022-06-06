package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * configs
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Config extends BaseEntity {
    private String name;

    private String value;

    private String description;
}