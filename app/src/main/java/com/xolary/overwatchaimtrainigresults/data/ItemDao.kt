package com.xolary.overwatchaimtrainigresults.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    /**
     * Извлечение из БД объектов с указанным ID
     *
     * @param id идентификатор извлекаемого объекта
     */
    @Query("SELECT * FROM item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    /**
     * Извлечение всех объектов из БД.
     * Сортировка по дню тренировки в порядке возрастания
     */
    @Query("SELECT * FROM item ORDER BY day ASC")
    fun getItems():Flow<List<Item>>

    /**
     * Добавление объекта в БД
     *
     * @param item добавляемая запись
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /**
     * Обновление данных записи в БД
     *
     * @param item обновляемая запись
     */
    @Update
    suspend fun update(item: Item)

    /**
     * Функция удаления объекта из БД
     *
     * @param item добавляемый предмет
     */
    @Delete
    suspend fun delete(item: Item)
}