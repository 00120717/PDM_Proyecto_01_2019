package com.coclearapp.pdm_project.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Room.Dao.*
import com.coclearapp.pdm_project.Room.Entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Patient::class, Rol::class, User::class, UserXPatient::class, Sound::class, Exercise::class],
    version = 3
)

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
                        populateDatabase(
                            database.PatientDao(),
                            database.RolDao(),
                            database.UserDao(),
                            database.UserXPatientDao(),
                            database.SoundDao()
                        )
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(
            patientDao: PatientDao,
            rolDao: RolDao,
            userDao: UserDao,
            userXPatientDao: UserXPatientDao,
            soundDao: SoundDao
        ) {


            var a = Sound(name = "a",sound = R.raw.a,number = 1, level = 1)
            soundDao.insert(a)
            var e = Sound(name = "e",sound = R.raw.e,number = 2, level = 1)
            soundDao.insert(e)
            var i = Sound(name = "i",sound = R.raw.i,number = 3, level = 1)
            soundDao.insert(i)
            var o = Sound(name = "o",sound = R.raw.o,number = 4, level = 1)
            soundDao.insert(o)
            var u = Sound(name = "u",sound = R.raw.u,number = 5, level = 1)
            soundDao.insert(u)
            var ma = Sound(name = "ma",sound = R.raw.ma,number = 6, level = 2)
            soundDao.insert(ma)
            var me = Sound(name = "me",sound = R.raw.me,number = 7, level = 2)
            soundDao.insert(me)
            var mi = Sound(name = "mi",sound = R.raw.mi,number = 8, level = 2)
            soundDao.insert(mi)
            var mo = Sound(name = "mo",sound = R.raw.mo,number = 9, level = 2)
            soundDao.insert(mo)
            var mu = Sound(name = "mu",sound = R.raw.mu,number = 10, level = 2)
            soundDao.insert(mu)
            var ama = Sound(name = "ama",sound = R.raw.ama,number = 11, level = 3)
            soundDao.insert(ama)
            var amo = Sound(name = "amo",sound = R.raw.amo,number = 12, level = 3)
            soundDao.insert(amo)
            var eme = Sound(name = "eme",sound = R.raw.eme,number = 13, level = 3)
            soundDao.insert(eme)
            var ema = Sound(name = "ema",sound = R.raw.ema,number = 14, level = 3)
            soundDao.insert(ema)
            var mimo = Sound(name = "mimo",sound = R.raw.mimo,number = 15, level = 3)
            soundDao.insert(mimo)

        }
    }

}
