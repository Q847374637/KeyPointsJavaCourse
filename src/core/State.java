package core;

import java.util.HashMap;
import java.util.Map;

public class State {

    private static Map<String, Boolean> _stateMap = new HashMap<>();

    static {
        _stateMap.put("isReadingTag", false);
        _stateMap.put("isReadingKey", false);
        _stateMap.put("isReadingDefinition", false);
        _stateMap.put("inWaiting", true);
    }
    public static Map<String, Boolean> getStateMap() {
        return _stateMap;
    }
    public static void setStateMap(Map<String, Boolean> stateMap) {
        _stateMap = stateMap;
    }

    public static synchronized void switchState(String state) {
        for (Map.Entry<String, Boolean> entry : _stateMap.entrySet()) {
            if(entry.getKey().equals(state))
            entry.setValue(true);
            else
                entry.setValue(false);
        }
    }
    public static synchronized String getState() throws Exception {
        String stateActive = "";
        for (Map.Entry<String, Boolean> entry : _stateMap.entrySet()) {
            if(entry.getValue())
                stateActive = entry.getKey();
        }
        if (stateActive.isEmpty())
            throw new Exception("No active states.");
        else
            return stateActive;
    }
    public static synchronized Map<String, Boolean> get_stateMap() throws Exception {
        return _stateMap;
    }
}