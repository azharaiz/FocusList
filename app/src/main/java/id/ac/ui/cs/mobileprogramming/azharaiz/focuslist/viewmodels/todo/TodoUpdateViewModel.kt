package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.AppDatabase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Todo
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.repositories.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoUpdateViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: TodoRepository

    lateinit var todoId: MutableLiveData<Int>
    lateinit var todoTitle: MutableLiveData<String>
    lateinit var todoStatus: MutableLiveData<Boolean>

    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        resetData()
    }

    private fun updateTodo(todo: Todo) =
        viewModelScope.launch(Dispatchers.IO) { repository.updateTodo(todo) }

    private fun deleteTodo(todo: Todo) =
        viewModelScope.launch(Dispatchers.IO) { repository.deleteTodo(todo) }

    fun resetData() {
        todoId = MutableLiveData(0)
        todoTitle = MutableLiveData("")
        todoStatus = MutableLiveData(false)
    }

    fun save() {
        val id = todoId.value!!
        val title = todoTitle.value!!
        val status = todoStatus.value!!

        val todo = Todo(id, title, status)

        updateTodo(todo)
        resetData()
    }

    fun delete() {
        val id = todoId.value!!
        val title = todoTitle.value!!
        val status = todoStatus.value!!

        val todo = Todo(id, title, status)

        deleteTodo(todo)
        resetData()
    }
}