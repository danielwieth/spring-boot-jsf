package de.yaskor.desk.entity;

import de.yaskor.desk.enums.SituationType;
import lombok.Data;

/**
 *
 * @author saka
 */
@Data
public class Situation {
    
    private String id;
    private String customer;
    private String project;
    private SituationType type;
    private Integer priority;
    private User assignee;
    private User reporter;
    private String summary;
    private String description;
    
}
