/*
package com.example.crapsgame.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CrapsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bankroll: Bankroll)

    @Update
    suspend fun update(bankroll: Bankroll)

    @Delete
    suspend fun delete(bankroll: Bankroll)

    @Query("SELECT * from bankrolls WHERE id = :id")
    fun getBankroll(id: Int): Flow<Bankroll>

    @Query("SELECT * from bankrolls ORDER BY amount ASC")
    fun getAllBankrolls(): Flow<List<Bankroll>>
}*/
