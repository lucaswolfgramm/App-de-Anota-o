package info.androidhive.gpluslogin.database;

/**
 * Created by Douglas on 04/10/2016.
 */
public class ScriptSQL {

    public static String getCreateContato(){

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE IF NOT EXISTS ANOTACAO ( ");
        sqlBuilder.append("id   INTEGER NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("TEXTO             VARCHAR (255) ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    };

}
