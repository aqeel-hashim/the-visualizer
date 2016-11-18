package go.application.com.go.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by epc on 10/20/2016.
 */
public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version = 1;
    public String CREATE_QUERY = " CREATE TABLE " + TableData.TableInfo.TABLE_NAME + "(" + TableData.TableInfo.USER_NAME +
            " "+TableData.TableDataType.TEXT+"," + TableData.TableInfo.USER_PASS + " "+TableData.TableDataType.TEXT+");";
    public String CREATE_IMAGE = " CREATE TABLE " + TableData.TableInfo.IMAGE_TABLE_NAME + "(" + TableData.TableInfo.IMAGE + " " + TableData.TableDataType.TEXT + ", " +
            TableData.TableInfo.IMAGE_NAME + " " + TableData.TableDataType.TEXT + ", " +
            TableData.TableInfo.IMAGE_TYPE + " " + TableData.TableDataType.TEXT + ", " +
            TableData.TableInfo.IMAGE_RETAILER + " " + TableData.TableDataType.TEXT + ", " +
            TableData.TableInfo.IMAGE_ADDRESS + " " + TableData.TableDataType.TEXT +  ", " +
            TableData.TableInfo.IMAGE_CONTACT + " " + TableData.TableDataType.TEXT + ");" ;

    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {

        sdb.execSQL(CREATE_QUERY);
        sdb.execSQL(CREATE_IMAGE);
        Log.d("Database operations", "Table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }


    public void putInformation(DatabaseOperations dop, String name, String pass) {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME, name);
        cv.put(TableData.TableInfo.USER_PASS, pass);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);
        Log.d("Database operations", "One row inserted " + k);

    }

    public void addImage(DatabaseOperations dop, String image, String name, String type, String Retailer, String address, String contact){

        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.IMAGE, image);
        cv.put(TableData.TableInfo.IMAGE_NAME, name);
        cv.put(TableData.TableInfo.IMAGE_TYPE, type);
        cv.put(TableData.TableInfo.IMAGE_RETAILER, Retailer);
        cv.put(TableData.TableInfo.IMAGE_ADDRESS, address);
        cv.put(TableData.TableInfo.IMAGE_CONTACT, contact);
        long k = SQ.insert(TableData.TableInfo.IMAGE_TABLE_NAME, null, cv);
        Log.d("DatabaseOperationsImage", "One row inserted return value k="+k);
    }

    public Cursor getInformation(DatabaseOperations dop) {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] coloumns = {TableData.TableInfo.USER_NAME, TableData.TableInfo.USER_PASS};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME, coloumns, null, null, null, null, null);
        return CR;
    }

    public Cursor getImages(DatabaseOperations dop) {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] coloumns = {TableData.TableInfo.IMAGE,TableData.TableInfo.IMAGE_NAME, TableData.TableInfo.IMAGE_TYPE, TableData.TableInfo.IMAGE_RETAILER, TableData.TableInfo.IMAGE_ADDRESS,TableData.TableInfo.IMAGE_CONTACT};
        Cursor CR = SQ.query(TableData.TableInfo.IMAGE_TABLE_NAME, coloumns, null, null, null, null, null);
        return CR;
    }
}
