package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.AppDatabase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Todo
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.repositories.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String = "TODO_VIEW_MODEL"
    val readAllTodos: LiveData<List<Todo>>
    private val repository: TodoRepository

    lateinit var todoId: MutableLiveData<Int>
    lateinit var todoTitle: MutableLiveData<String>
    lateinit var todoStatus: MutableLiveData<Boolean>

    private lateinit var todo: Todo

    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        readAllTodos = repository.readAllTodos
        Log.i(TAG, "Start")
        clearData()
    }

    fun updateData(todo: Todo) {
        todoId.value = todo.id
        todoTitle.value = todo.title
        todoStatus.value = todo.status
        logForm("update_data")
    }

    fun clearData() {
        todoId = MutableLiveData(0)
        todoTitle = MutableLiveData("")
        todoStatus = MutableLiveData(false)
        Log.i(TAG, "Clear data")
    }

    fun create() {
        todo = Todo(0, todoTitle.value!!, todoStatus.value!!)
        addTodo(todo)
        clearData()
    }

    fun update() {
        logForm("update")
        todo = Todo(todoId.value!!, todoTitle.value!!, todoStatus.value!!)
        updateTodo(todo)
        clearData()
    }

    fun delete() {
        todo = Todo(todoId.value!!, todoTitle.value!!, todoStatus.value!!)
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

    fun logForm(status: String) {
        Log.i(TAG, "${status} ${todoId.value!!} ${todoTitle.value!!} ${todoStatus.value!!}")
    }
}