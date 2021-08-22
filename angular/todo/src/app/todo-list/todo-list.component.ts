import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { DataServiceService } from '../data-service.service';
import { TodoClass } from '../todo-class';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {

  @ViewChild('newTodo') newTodo: ElementRef;
  @ViewChild('editTodoInput') editTodoInput: ElementRef;
  private todos: TodoClass[] = [];
  private currentTodo: TodoClass;
  private numTodos: number;
  private edit = false;
  private subscription: Subscription;

  constructor(private _data: DataServiceService ) { }

  ngOnInit() {
    // this._data.staticTodo();
    this._data.getToDos();
    this.subscription = this._data.todosChanged.subscribe((res) => {
      this.todos = res;
      this.incompleteTodos();
    });
  }

  addTodo() {
    if (this.newTodo.nativeElement.value.length > 0)  {
      this._data.storeToDo(this.newTodo.nativeElement.value);
      this.newTodo.nativeElement.value = '';
    }
    this.incompleteTodos();
  }

  deleteTodo(todo: TodoClass) {
    this._data.deleteTodo(todo);
    this.todos.splice(this.todos.indexOf(todo), 1);
    this.incompleteTodos();
  }

  editState(todo: TodoClass)  {
    this._data.editState(todo);
  }

  editableTodo(todo: TodoClass) {
    this.edit = !this.edit;
    this.editTodoInput.nativeElement.value = todo.title;
    this.currentTodo = todo;
  }

  editTodo()  {
    if (this.editTodoInput.nativeElement.value.length)  {
      this.currentTodo.title = this.editTodoInput.nativeElement.value;
      this._data.editTodo(this.currentTodo);
    }
    this.closeEdit();
  }

  closeEdit() {
    this.edit = !this.edit;
    this.editTodoInput.nativeElement.value = '';
    this.currentTodo = null;
  }

  markAll() {
    this._data.markAll(this.todos);
    this.incompleteTodos();
  }

  deleteAll() {
    this._data.deleteAll();
    this.incompleteTodos();
  }

  incompleteTodos() {
    this.numTodos = 0;
    this.todos.forEach((todo) =>  {
      this.numTodos += todo.state ? 0 : 1;
    });
  }
}
