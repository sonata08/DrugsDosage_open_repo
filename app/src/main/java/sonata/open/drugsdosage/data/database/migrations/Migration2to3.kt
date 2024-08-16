package sonata.open.drugsdosage.data.database.migrations

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import sonata.open.drugsdosage.Constants.DATE_FORMAT
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class Migration2to3 : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Add a new integer column 'time_new' to store the milliseconds
        db.execSQL("ALTER TABLE times ADD COLUMN time_new INTEGER DEFAULT 0")

        // Retrieve all rows from the 'times' table
        val cursor: Cursor = db.query("SELECT id, time FROM times", emptyArray())

        // Iterate through each row and convert the 'time' string to milliseconds
        while (cursor.moveToNext()) {

            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val timeString = cursor.getString(cursor.getColumnIndexOrThrow("time"))

            try {
                val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                val timeInMillis = dateFormat.parse(timeString)?.time ?: 0
                // Update the 'time_new' column with milliseconds
                val contentValues = ContentValues()
                contentValues.put("time_new", timeInMillis)
                db.update(
                    "times",
                    SQLiteDatabase.CONFLICT_REPLACE,
                    contentValues,
                    "id = ?",
                    arrayOf(id.toString())
                )
            } catch (e: ParseException) {
                db.execSQL("DELETE FROM times")
            }
        }
        cursor.close()

        // Remove the old 'time' column
        db.execSQL("ALTER TABLE times RENAME TO times_old")

        // Create a new 'times' table with the updated schema
        db.execSQL("CREATE TABLE IF NOT EXISTS times (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, time INTEGER NOT NULL)")

        // Copy data from 'times_old' to the new 'times' table
        db.execSQL("INSERT INTO times (id, time) SELECT id, time_new FROM times_old")

        // Drop the old 'times_old' table
        db.execSQL("DROP TABLE times_old")
    }
}