#  KeyPointsJavaCourse
## Description
KeyPointsJavaCourse is a simple program that collects data from input .txt notes (about Java course) and puts structured lists (based on tags) into a new file.
## How it works
1. Program reads each file from provided path byte-to-byte.
2. If it finds a tag at the beginning of the line looking like this:

```[hotkey]```

tag content is in the list of tags to be read and the line view is the following:

```name - explanation```

than the program will save all data after tag until the end of the file and put it into output file in the list with needed tag name.
## Building
1. Prepeare files for processing. *You can take files from ~/src/inputExample as an example.*
2. Make sure your files are in the required folder and it's data does not contain extra '[' & ']' signs (except tags). Relevant tags are:

```definition, keyword, hotkey, func, other```

Also check ' - ' sings between object and it's explanation..

3. Run the program. You will see a menu:

![image](https://github.com/Q847374637/KeyPointsJavaCourse/assets/110888804/21c26b97-b9bb-4c75-9d7b-2169b4f4bc59)

5. If you are satisfied by current settings, than type '1'.
6. Wait until the end of the processing.
7. Type '3' to exit.
8. Now in output folder you have a file of your notes that you can send to your IBA Java course teacher. You have done exellent job!
