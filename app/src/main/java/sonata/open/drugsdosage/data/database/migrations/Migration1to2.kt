package sonata.open.drugsdosage.data.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1to2: Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Create the new table
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS times_temp (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `time` TEXT )"
        )

        // Copy the data
        db.execSQL(
            "INSERT INTO times_temp (id, time) SELECT id, time FROM times ")

        // Remove the old table
        db.execSQL("DROP TABLE times")

        // Change the table name to the correct one
        db.execSQL("ALTER TABLE times_temp RENAME TO times")
    }
}