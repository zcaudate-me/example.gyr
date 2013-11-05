(ns purnam-angular-examples.todo
  (:use [purnam.cljs :only [aget-in aset-in]])
  (:require [goog.object :as o])
  (:use-macros [purnam.js :only [obj arr ! def.n]]
               [purnam.angular :only [def.module def.filter
                                      def.factory def.controller]]))

(def.module todoDemo [])

(def.n make-todo [todo]
  (obj :text todo.text :done todo.done))

(def.filter todoDemo.myCount []
  (fn [input]
    (if input
      (count input)
      0)))

(def.factory todoDemo.todoStorage []
  (let [id "todos-angularjs"]
    (obj :get
         (fn []
           (let [s (js/localStorage.getItem id)]
             (if (or (nil? s) (= s "[]"))
               (arr {:text "Learn Clojure" :done false}
                    {:text "Learn Javascript" :done false}
                    {:text "Learn Angular" :done false})
               (js/JSON.parse s))))

         :put
         (fn [todos]
           (js/localStorage.setItem id 
             (js/JSON.stringify (todos.map make-todo)))))))

(def.controller todoDemo.TodoMainCtrl [$scope todoStorage]
  (! $scope.todos (todoStorage.get))
  (! $scope.newTodo "")
  (! $scope.addTodo
       (fn []
         (js/console.log $scope.newTodo)
         (if (< 0 $scope.newTodo.length)
           (do ($scope.todos.push
                (obj :text $scope.newTodo
                     :done false))
               (! $scope.newTodo "")))))

  (! $scope.remainingTodos
       (fn []
         (.-length ($scope.todos.filter
            (fn [v] (not v.done))))))

  (! $scope.editedTodo
       (fn [todo]
         (! $scope.editedTodo todo)))

   (! $scope.doneEditing
        (fn [todo]
          (! $scope.editedTodo nil)
          (if-not todo.title
            ($scope.removeTodo todo))))

   (! $scope.removeTodo
        (fn [todo]
          ($scope.todos.splice ($scope.todos.indexOf todo) 1)))

   (! $scope.clearCompletedTodos
        (fn []
          (! $scope.todos
             ($scope.todos.filter
              (fn [v] (not v.done))))))

    (! $scope.markAll
         (fn [bool]
           ($scope.todos.forEach
            (fn [todo] (! todo.completed bool)))))
            
    ($scope.$watch
      "todos.length"
      (fn [] (todoStorage.put $scope.todos))))
  