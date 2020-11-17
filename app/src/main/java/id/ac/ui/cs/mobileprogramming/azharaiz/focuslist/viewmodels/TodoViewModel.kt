package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Todo
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.TodoDatabase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.repositories.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    val readAllTodos: LiveData<List<Todo>>
    private val repository: TodoRepository

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        readAllTodos = repository.readAllTodos
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) { repository.addTodo(todo) }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateTodo(todo) }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteTodo(todo) }
    }

    fun deleteAllTodo() {
        viewModelScope.launch { repository.deleteAllTodo() }
    }
}