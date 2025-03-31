package com.kirabium.relayance.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kirabium.relayance.data.DummyData
import com.kirabium.relayance.data.dao.CustomerDao
import com.kirabium.relayance.data.dto.CustomerDto
import java.util.concurrent.Executors

@Database(entities = [CustomerDto::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDAO(): CustomerDao

    companion object {
        private val ROOM_CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Executors.newSingleThreadExecutor().execute {
                    DummyData.customers.forEach { customer ->
                        Log.d("AppDatabase", "Inserting customer: ${customer.name}")
                        db.execSQL("INSERT INTO customers (id, name, email, created_at) VALUES ('${customer.id}', '${customer.name}', '${customer.email}', ${customer.createdAt.time})")
                    }
                }
            }
        }

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