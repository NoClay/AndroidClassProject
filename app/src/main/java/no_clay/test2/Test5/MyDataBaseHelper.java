package no_clay.test2.Test5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noclay on 2017/5/17.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static final String CREATE_ACCENT_TABLE = "" +
            "create table accent ( " +
            "number text primary key, " +
            "name text, " +
            "type text )";

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Accent> getAccent() {
        Cursor cursor = getReadableDatabase().rawQuery(
                "select * from accent", null
        );
        List<Accent> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Accent accent = new Accent();
                accent.setName(cursor.getString(cursor.getColumnIndex("name")));
                accent.setNumber(cursor.getString(cursor.getColumnIndex("number")));
                accent.setType(cursor.getString(cursor.getColumnIndex("type")));
                result.add(accent);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public void insertAccent(Accent accent) {
        ContentValues values = new ContentValues();
        values.clear();
        SQLiteDatabase db = getWritableDatabase();
        values.put("number", accent.getNumber());
        values.put("name", accent.getName());
        values.put("type", accent.getType());
        db.insert("accent", null, values);
        db.close();
    }

    public void deleteAccent(String number){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from accent " +
                "where number = " + number);
        db.close();
    }
}
