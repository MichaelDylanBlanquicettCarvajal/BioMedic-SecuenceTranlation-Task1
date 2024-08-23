import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Req1_Seq_Rep {

    public HashMap<String, String> remove_duplicates(HashMap<String, String> dictP){

        for (String s : new ConcurrentHashMap<>(dictP).keySet()) {
                String value = dictP.get(s);
                for (Map.Entry<String, String> ss : new ConcurrentHashMap<>(dictP)
                        .entrySet()) {
                    if (!s.equals(ss.getKey()) && value.equals(ss.getValue())) {
                        dictP.remove(ss.getKey());
                    }
                }
            }
        return dictP;


    }
}
