package com.xolary.overwatchaimtrainigresults

import androidx.lifecycle.*
import com.xolary.overwatchaimtrainigresults.data.Item
import com.xolary.overwatchaimtrainigresults.data.ItemDao
import kotlinx.coroutines.launch

class ResultsViewModel(private val itemDao: ItemDao) : ViewModel() {
    // Получение всех записей в качестве LiveData списка
    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    /**
     * Обращение к DAO для добавления объекта в БД
     *
     * @param item добавляемый объект
     */
    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    /**
     * Получение новой записи
     *
     * @param numberOfDay порядковый номер дня тренировки
     * @param difficulty сложность ботов
     * @param characterClass класс персонажа
     * @param name имя персонажа
     * @param spentTime время, затраченное на фраги
     * @param kills количество фрагов
     * @param accuracy процент точности
     *
     * @return Экземпляр класса [Item] с заданными параметрами
     */
    private fun getNewItemEntry(
        numberOfDay: String,
        difficulty: String,
        characterClass: String,
        name: String,
        spentTime: String,
        kills: String,
        accuracy: String
    ): Item {
        return Item(
            numberOfDay = numberOfDay.toInt(),
            opponentDifficulty = difficulty,
            characterClass = characterClass,
            characterName = name,
            spentTime = spentTime.toInt(),
            kills = kills.toInt(),
            accuracy = accuracy.toInt()
        )
    }

    /**
     * Передача экземпляра класса в функцию добавления в БД
     *
     * @param numberOfDay порядковый номер дня тренировки
     * @param difficulty сложность ботов
     * @param characterClass класс персонажа
     * @param name имя персонажа
     * @param spentTime время, затраченное на фраги
     * @param kills количество фрагов
     * @param accuracy процент точности
     */
    fun addNewItem(
        numberOfDay: String,
        difficulty: String,
        characterClass: String,
        name: String,
        spentTime: String,
        kills: String,
        accuracy: String
    ) {
        val newItem = getNewItemEntry(
            numberOfDay,
            difficulty,
            characterClass,
            name,
            spentTime,
            kills,
            accuracy
        )
        insertItem(newItem)
    }

    /**
     * Проверка на заполенность полей
     *
     * @param numberOfDay порядковый номер дня тренировки
     * @param difficulty сложность ботов
     * @param characterClass класс персонажа
     * @param name имя персонажа
     * @param spentTime время, затраченное на фраги
     * @param kills количество фрагов
     * @param accuracy процент точности
     *
     * @return true если все поля заполнены, иначе false
     */
    fun isEntryValid(
        numberOfDay: String,
        difficulty: String,
        characterClass: String,
        name: String,
        spentTime: String,
        kills: String,
        accuracy: String
    ): Boolean {
        if (numberOfDay.isBlank() || difficulty.isBlank() || characterClass.isBlank() ||
            name.isBlank() || spentTime.isBlank() || kills.isBlank() || accuracy.isBlank()
        ) {
            return false
        }
        return true
    }

    /**
     * Получение записи по ID
     *
     * @param id ID записи
     * @return сведения о записи в виде LiveData объекта
     */
    fun retrieveItem(id: Int): LiveData<Item> {
        return itemDao.getItem(id).asLiveData()
    }

    /**
     * Вызов запроса на обновление
     *
     * @param item обновляемый объект
     */
    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }

    /**
     * Вызов запроса на удаление записи из БД
     *
     * @param item удаляемая запись
     */
    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    /**
     *  Получение новго экземпляра класса Item
     *
     * @param itemId ID записи
     * @param numberOfDay порядковый номер дня тренировки
     * @param difficulty сложность ботов
     * @param characterClass класс персонажа
     * @param name имя персонажа
     * @param spentTime время, затраченное на фраги
     * @param kills количество фрагов
     * @param accuracy процент точности
     *
     * @return экземпляр класса [Item] с заданными параметрами
     */
    private fun getUpdatedItemEntry(
        itemId: Int,
        numberOfDay: String,
        difficulty: String,
        characterClass: String,
        name: String,
        spentTime: String,
        kills: String,
        accuracy: String
    ): Item {
        return Item(
            Id = itemId,
            numberOfDay = numberOfDay.toInt(),
            opponentDifficulty = difficulty,
            characterClass = characterClass,
            characterName = name,
            spentTime = spentTime.toInt(),
            kills = kills.toInt(),
            accuracy = accuracy.toInt()
        )
    }

    /**
     * Вызов функции с запросом на обновление товара
     *
     * @param itemId ID записи
     * @param numberOfDay порядковый номер дня тренировки
     * @param difficulty сложность ботов
     * @param characterClass класс персонажа
     * @param name имя персонажа
     * @param spentTime время, затраченное на фраги
     * @param kills количество фрагов
     * @param accuracy процент точности
     */
    fun updateItem(
        itemId: Int,
        numberOfDay: String,
        difficulty: String,
        characterClass: String,
        name: String,
        spentTime: String,
        kills: String,
        accuracy: String
    ) {
        val updatedItem = getUpdatedItemEntry(
            itemId,
            numberOfDay,
            difficulty,
            characterClass,
            name,
            spentTime,
            kills,
            accuracy
        )
        updateItem(updatedItem)
    }
}

class ResultsViewModelFactory(private val itemDao: ItemDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        /*
         * Проверяем modelClass, если совпадает с классом InventoryViewModel,
         * возвращаем его экземпляр. Иначе создаём исключение
         */
        if (modelClass.isAssignableFrom(ResultsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResultsViewModel(itemDao) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}