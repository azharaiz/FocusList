package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.repositories

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Todo
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.TodoDao

class TodoRepository(private val todoDao: TodoDao) {

    val readAllTodos: LiveData<List<Todo>> = todoDao.readAllTodos()

    suspend fun addTodo(todo: Todo) {
        todoDao.addTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    suspend fun deleteAllTodo() {
        todoDao.deleteAllTodo()
    }
}