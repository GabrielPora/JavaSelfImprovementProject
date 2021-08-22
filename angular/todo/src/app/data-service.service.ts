import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TodoClass } from './todo-class';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  constructor(private _http: HttpClient) {}

  todosChanged = new Subject<TodoClass[]>();

  private todos: TodoClass[] = [];

  getToDos()  {
    this._http.get<TodoClass[]>('http://localhost:8080/api/todo').subscribe((res) =>  {
      this.todos = res;
      this.todosChanged.next(this.todos.slice());
    });
  }

  storeToDo(todo: string)  {
    const id = this.todos.length === 0 ? 1 : this.todos[this.todos.length - 1].id + 1;
    const newTodo = new TodoClass(id, todo, false, new Date(), new Date());
    this._http.post('http://localhost:8080/api/todo', newTodo).subscribe((res: TodoClass) =>  {
      this.todos.push(res);
      this.todosChanged.next(this.todos.slice());
    });
  }

  editState(todo: TodoClass)  {
    const url = 'http://localhost:8080/api/todo/' + todo.id;
    todo.state = !todo.state;
    todo.lastUpdated = new Date();
    this._http.put(url, todo).subscribe((res) =>  {
      this.todos[this.todos.indexOf(todo)].state = todo.state;
      this.todosChanged.next(this.todos.slice());
    });
  }

  editTodo(todo: TodoClass) {
    const url = 'http://localhost:8080/api/todo/' + todo.id;
    todo.lastUpdated = new Date();
    this._http.put(url, todo).subscribe((res) =>  {
      this.getToDos();
    });
  }

  deleteTodo(todo: TodoClass) {
    const url = 'http://localhost:8080/api/todo/' + todo.id;
    this._http.delete(url).subscribe((res) => {
      this.todos.splice(this.todos.indexOf(todo), 1);
      this.todosChanged.next(this.todos.slice());
    });
  }

  markAll(todos: TodoClass[]) {
    todos.forEach((todo) => {
      const url = 'http://localhost:8080/api/todo/' + todo.id;
      todo.state = true;
      this._http.put(url, todo).subscribe((res) =>  {
        console.log(res);
        this.todosChanged.next(this.todos.slice());
      });
    });
  }

  deleteAll() {
    this.todos.forEach((todo) =>  {
      this.deleteTodo(todo);
    });
  }
}
