package com.example.ripan.map;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by teoc on 15/03/18.
 */

//File Layout for users:
    //Login, User Name, Password, First Name, Last Name, date of birth, email\n
    //Login: Yes = 1. No = 0

public class InternalFile {
    private String filename = "user_info";

    public String getUsername (Context activity ) {
        FileInputStream input;
        String splitLine[];

        try {
            input = activity.getApplicationContext().openFileInput(this.filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            splitLine = reader.readLine().split(",");
            reader.close();
            return splitLine[0];
        }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public int saveUsername (String userName, Context activity){
        FileOutputStream output;
        try {
            File file = new File(activity.getFilesDir() + "/" + this.filename);
            if (file.exists()) { return 2; }

            output = activity.getApplicationContext().openFileOutput(this.filename, Context.MODE_PRIVATE);
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(output));
            write.write(userName + ",,\n");         //Extra Commas for potential password storage
            write.close();
            return 0;
        }
        catch (FileNotFoundException e) {
            //Create file if not found?

            return 1;
        }
        catch (Exception e ) {
            e.printStackTrace();
            return 1;
        }
    }


}
