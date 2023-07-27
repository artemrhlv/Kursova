package api.models.args;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BodyArgs<T> {  //дженерік

    @Builder.Default
    private String jsonrpc = "2.0"; //статичне
    @Builder.Default
    private int id = 1416806551; //статичне? (повинен мати хоч якийсь параметр)
    public String method; //метод обирається в залежності від реквесту
    public T params; //передаємо параметри в залежності від методу (уніфіковано для всіх значень)

}
