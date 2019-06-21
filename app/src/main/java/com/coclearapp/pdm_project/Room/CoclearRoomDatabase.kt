package com.coclearapp.pdm_project.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.coclearapp.pdm_project.Room.Dao.*
import com.coclearapp.pdm_project.Room.Entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Patient::class, Rol::class,User::class,UserXPatient::class, Sound::class,Exercise::class ], version = 3)

abstract class CoclearRoomDatabase : RoomDatabase() {

    abstract fun PatientDao(): PatientDao
    abstract fun RolDao(): RolDao
    abstract fun UserDao(): UserDao
    abstract fun UserXPatientDao(): UserXPatientDao
    abstract fun SoundDao(): SoundDao
    abstract fun ExerciseDao(): ExerciseDao



    companion object {
        @Volatile
        private var INSTANCE: CoclearRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): CoclearRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CoclearRoomDatabase::class.java,
                        "CoclearAPP_database"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(Companion.BookDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class BookDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.PatientDao(),database.RolDao(),database.UserDao(),database.UserXPatientDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(patientDao: PatientDao, rolDao: RolDao, userDao: UserDao, userXPatientDao: UserXPatientDao) {




        }
    }

}
