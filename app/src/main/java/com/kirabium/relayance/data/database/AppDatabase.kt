package com.kirabium.relayance.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kirabium.relayance.data.DummyData
import com.kirabium.relayance.data.dao.CustomerDao
import com.kirabium.relayance.data.dto.CustomerDto
import java.util.concurrent.Executors

/**
 * The main database class for the application, using Room as the persistence library.
 * This database stores customer data and includes necessary type converters.
 */
@Database(entities = [CustomerDto::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides access to the CustomerDao for database operations.
     *
     * @return The DAO for customer-related database operations.
     */
    abstract fun customerDAO(): CustomerDao

    companion object {

        /**
         * Callback executed when the database is created.
         * It pre-populates the database with dummy customer data.
         */
        private val ROOM_CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Executors.newSingleThreadExecutor().execute {
                    DummyData.customers.forEach { customer ->
                        db.execSQL("INSERT INTO customers (id, name, email, created_at) VALUES ('${customer.id}', '${customer.name}', '${customer.email}', ${customer.createdAt.time})")
                    }
                }
            }
        }

        /**
         * Gets an instance of the database.
         *
         * @param context The application context.
         * @return A singleton instance of AppDatabase.
         */
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "customer_database"
            ).addCallback(ROOM_CALLBACK)
                .build()
        }
    }
}