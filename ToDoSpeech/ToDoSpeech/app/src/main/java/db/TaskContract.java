package db; // Make sure this matches your apps package

import android.provider.BaseColumns;

public class TaskContract {
    public static final String DB_NAME = "com.example.TodoSpeech.db.tasks";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "events";

    public class Columns {
        public static final String TASK = "event";
        public static final String _ID = BaseColumns._ID;
    }
}