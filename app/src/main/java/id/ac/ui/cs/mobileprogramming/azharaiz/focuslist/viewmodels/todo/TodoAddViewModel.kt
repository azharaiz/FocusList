package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.AppDatabase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Todo
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.repositories.TodoRepository
import kotlinx.coroutines.launch

class TodoAddViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: TodoRepository

    lateinit var todoTitle: MutableLiveData<String>
    lateinit var todoStatus: MutableLiveData<Boolean>

    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        resetData()
    }

    private fun insertTodo(todo: Todo) = viewModelScope.launch { repository.addTodo(todo) }

    private fun resetData() {
        todoTitle = MutableLiveData("")
        todoStatus = MutableLiveData(false)
    }

    fun save() {
        val title = todoTitle.value!!
        val status = todoStatus.value!!

        val todo = Todo(0, title, status)

        insertTodo(todo)

        resetData()
    }
}