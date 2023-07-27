package api.models.args.tasks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseTask {

    private int id;
    private String title;
    private String description;
    private String date_creation;
    private String color_id;
    private int project_id;
    private int column_id;
    private int owner_id;
    private int position;
    private int is_active;
}
