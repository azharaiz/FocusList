package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.AppDatabase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Todo
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.repositories.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String = "TODO_VIEW_MODEL"
    val readAllTodos: LiveData<List<Todo>>
    private val repository: TodoRepository

    lateinit var todoId: MutableLiveData<Int>
    lateinit var todoTitle: MutableLiveData<String>
    lateinit var todoStatus: MutableLiveData<Boolean>
    lateinit var todoDuration: MutableLiveData<Int>
    var todoDate: MutableLiveData<Date>? = null

    private lateinit var todo: Todo

    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        readAllTodos = repository.readAllTodos
        clearData()
    }

    fun updateData(todo: Todo) {
        todoId.value = todo.id
        todoTitle.value = todo.title
        todoStatus.value = todo.status
        todoDuration.value = todo.duration
    }

    fun clearData() {
        todoId = MutableLiveData(0)
        todoTitle = MutableLiveData("")
        todoStatus = MutableLiveData(false)
        todoDuration = MutableLiveData(0)
        todoDate = MutableLiveData(Date())
    }

    fun create() {
        todo = Todo(
            0,
            todoTitle.value!!,
            todoStatus.value!!,
            todoDuration.value!!,
            todoDate?.value!!
        )
        addTodo(todo)
        clearData()
    }

    fun update() {
        todo = Todo(
            todoId.value!!,
            todoTitle.value!!,
            todoStatus.value!!,
            todoDuration.value!!,
            todoDate?.value!!
        )
        updateTodo(todo)
        clearData()
    }

    fun delete() {
        todo = Todo(
            todoId.value!!,
            todoTitle.value!!,
            todoStatus.value!!,
            todoDuration.value!!,
            todoDate?.value!!
        )
        deleteTodo(todo)
        clearData()
    }

    private fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) { repository.addTodo(todo) }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateTodo(todo) }
    }

    private fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteTodo(todo) }
    }

    fun deleteAllTodo() {
        viewModelScope.launch { repository.deleteAllTodo() }
    }
}