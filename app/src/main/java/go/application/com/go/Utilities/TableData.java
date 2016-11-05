package go.application.com.go.Utilities;

import android.app.Activity;
import android.provider.BaseColumns;

/**
 * Created by epc on 10/20/2016.
 */
public class TableData extends Activity {

    public TableData()
    {

    }

    public static abstract class TableInfo implements BaseColumns
    {
        public static final String USER_NAME = "user_name";
        public static final String USER_PASS = "user_pass";
        public static final String DATABASE_NAME = "user_info";
        public static final String TABLE_NAME = "reg_ingo";

        public static final String IMAGE = "image";
        public static final String IMAGE_NAME = "image_name";
        public static final String IMAGE_TABLE_NAME = "cart";
        public static final String IMAGE_TYPE = "type";
        public static final String IMAGE_RETAILER = "retailer";
        public static final String IMAGE_ADDRESS = "address";
        public static final String IMAGE_CONTACT = "contact";


    }

    public static abstract class TableDataType implements BaseColumns{

        public static final String TEXT = "TEXT";
        public static final String INTEGER = "INTEGER";
        public static final String BLOB = "BLOB";

    }



}
