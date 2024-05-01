import core.Data;
import core.State;
import core.Value;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public static void main (String[] args) throws Exception {
    Data data = new Data();
    Scanner in = new Scanner(System.in);
    String tempString = "";
    int tempInt;
    while (true) {
        System.out.println(String.format("Welcome, %s\n\nCurrent settings:\n%s\n\nChoose wanted option by typing a needed number on your keyboard.\n\n1. Start extracting\n2. Edit path\n3. Exit\n", System.getProperty("user.name"), data.toString()));
        tempInt = in.nextInt();
        switch(tempInt)
        {
            case 1 :
                Data.setFileList();
                for (Path file : Data.getFileList())
                {
                    FileInputStream fileInputStream = new FileInputStream(file.toString());
                    Extract(tempInt,tempString,fileInputStream);
                }
                Value.writeToFile(Data.getOutputPath(), Data.getOutputFileName());
                break;
            case 2:
                System.out.println("1. Input\n2. Output\n");
                tempInt = in.nextInt();
                System.out.println("Type path value:\n");
                tempString = in.nextLine();
                if(tempInt == 1 || tempInt == 2) {
                    if(tempInt == 1)
                        Data.setInputPath(Paths.get(tempString));
                  else
                      Data.setOutputPath(Paths.get(tempString));
                }
                else
                    throw new Exception("Incorrect value!");
                break;
            case 3:
                return;
            default:
                throw new Exception("Incorrect value!");

        }
    }
}
static void Extract(int tempInt, String tempString, FileInputStream fileInputStream) throws Exception {
    while ((tempInt = fileInputStream.read()) != -1)
    {
        switch (State.getState()) {
            case "inWaiting":
                if (tempInt == 91) // '['
                {
                    State.switchState("isReadingTag");
                }
                break;
            case "isReadingTag":
                if (tempInt == 93) // ']'
                {
                    State.switchState("isReadingKey");
                    new Value(tempString);
                    tempString = "";
                } else
                    tempString += (char) tempInt;
                break;
            case "isReadingKey":
                if (tempInt == 45) // '-'
                {
                    State.switchState("isReadingDefinition");
                    Value.getLastInstance().setKey(tempString.trim());
                    tempString = "";
                } else
                    tempString += (char) tempInt;
                break;
            case "isReadingDefinition":
                if (tempInt == 13) // '\r'
                {
                    State.switchState("inWaiting");
                    Value.getLastInstance().setDefinition(tempString.trim());
                    tempString = "";
                } else
                    tempString += (char) tempInt;
                break;
        }
    }
}


