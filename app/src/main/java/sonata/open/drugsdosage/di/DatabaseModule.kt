package sonata.open.drugsdosage.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import sonata.open.drugsdosage.data.DataConstants.DATABASE_NAME
import sonata.open.drugsdosage.data.database.TimeDao
import sonata.open.drugsdosage.data.database.TimeDatabase
import sonata.open.drugsdosage.data.database.migrations.Migration1to2
import sonata.open.drugsdosage.data.database.migrations.Migration2to3
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideTimeDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, TimeDatabase::class.java, DATABASE_NAME)
        .addMigrations(Migration1to2(), Migration2to3())
        .build()

    @Singleton
    @Provides
    fun provideTimeDao(timeDatabase: TimeDatabase): TimeDao {
        return timeDatabase.timeDAO()
    }
}