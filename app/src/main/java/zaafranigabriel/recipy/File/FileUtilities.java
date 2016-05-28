package zaafranigabriel.recipy.File;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by zaafranigabriel on 21/05/2016.
 */
public class FileUtilities  {

    private String fileName;

    public FileUtilities(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }



}
