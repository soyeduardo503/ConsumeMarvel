package sv.com.marvel.json.character; 
import java.util.List;



@lombok.Data
public class DataResponse{
    private  String offset;
    private String limit;
    private String total;
    private String count;
    private List<Result> results;
    
}
