import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Comparator;
import java.nio.file.Path;
import java.util.ArrayList;
public class Value {
    Type valueType;
    String key;
    String definition;
    static ArrayList<Value> instances = new ArrayList<>();
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Type getValueType() {
        return valueType;
    }

    public void setValueType(Type valueType) {
        this.valueType = valueType;
    }
    public static ArrayList<Value> getInstances() {
        return instances;
    }

    public static void setInstances(ArrayList<Value> instances) {
        Value.instances = instances;
    }
    public static Value getLastInstance() {
        return instances.getLast();
    }

    public Value() {
        instances.add(this);
    }
    public Value(String type) {
        this.valueType = Type.valueOf(type);
        instances.add(this);
    }

    public Value(String key, String definition, String type) {
        this.valueType = Type.valueOf(type);
        this.key = key;
        this.definition = definition;
        instances.add(this);
    }
    public static void writeToFile(Path outputPath, String getOutputFileName) throws IOException {
        Type currentValueType = null;
        sortInstances();
        File dir = new File(outputPath.toString(), getOutputFileName);
        if(dir.exists())
            dir.delete();
        dir.createNewFile();
        FileWriter writer = new FileWriter(outputPath.toString() + getOutputFileName, false);
        writer.write("--Java course notes--\r\n");
        writer.flush();
        for(Value value : getInstances())
        {
            if(currentValueType != value.getValueType())
            {
                currentValueType = value.getValueType();
                writer.write(String.format("\r\n%s:\r\n%s\r\n", value.valueType.name(), value.toString()));
            }
            else
            {
                writer.write(value.toString()+"\r\n");
            }
            writer.flush();
        }
        writer.close();
    }
    private static void sortInstances() {
        Collections.sort(instances, new Comparator<Value>() {
            @Override
            public int compare(Value o1, Value o2) {
                return o1.getValueType().compareTo(o2.getValueType());
            }
        });
    }
    String outputValues(String outputPath) throws FileNotFoundException {
        return outputPath;
    }
    @Override
    public String toString() {
        return String.format("%s - %s\n",key, definition);
    }
}
enum Type
{
    keyword,
    hotkey,
    func,
    other
}
