package no_clay.test2.UtilPaskage;

import android.os.Environment;

/**
 * Created by noclay on 2017/5/17.
 */

public class MyConstants {
    public static final String ROOT_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/TestProject/";
    public static final String TASK_FILE_PATH =
            ROOT_PATH + "taskFilePath.txt";

    public static final String LOCAL_DATABASE_NAME = "local.db";
    public static final int DATABASE_VERSION = 1;
}
