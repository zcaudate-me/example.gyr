(ns purnam-angular-examples.todo
  (:use [purnam.cljs :only [aget-in aset-in]])
  (:require [goog.object :as o])
  (:use-macros [purnam.js :only [obj arr ! def*n]]
               [purnam.angular :only [def.module
                                      def.factory def.controller]]))

(def.module todoDemo [])

(def*n make-todo [todo]
  {:text todo.text
   :done todo.done})

(def.factory todoDemo.todoStorage []
  (let [id "todos-angularjs"]
    (obj :get
         (fn []
           (let [s (js/localStorage.getItem id)]
             (js/console.log s)
             (if (or (nil? s) (= s "[]"))
               (arr {:text "Learn Clojure" :done false}
                    {:text "Learn Javascript" :done false}
                    {:text "Learn Angular" :done false})
               (js/JSON.parse s))))
         :put
         (fn [todos]
           (js/localStorage.setItem id (js/JSON.stringify (todos.map make-todo)))))))

(def*n setTodoMainCtrl [$ todoStorage]
  (o/extend $
    {:todos (todoStorage.get)
     :newTodo ""
     :addTodo
     (fn []
       (if (< 0 $.newTodo.length)
         (do ($.todos.push
              {:text $.newTodo
               :done false})
             (! $.newTodo ""))))

     :remainingTodos
     (fn []
       (.-length ($.todos.filter
          (fn [v] (not v.done)))))

     :editedTodo
     (fn [todo]
       (! $.editedTodo todo))

     :doneEditing
     (fn [todo]
       (! $.editedTodo nil)
       (if-not todo.title
         ($.removeTodo todo)))

     :removeTodo
     (fn [todo]
       ($.todos.splice ($.todos.indexOf todo) 1))

     :clearCompletedTodos
     (fn []
       (js/console.log "Clearing")
       (! $.todos
          ($.todos.filter
           (fn [v] (not v.done)))))

     :markAll
     (fn [bool]
       ($.todos.forEach
        (fn [todo] (! todo.completed bool))))}))

(def.controller todoDemo.TodoMainCtrl [$scope todoStorage]
  (setTodoMainCtrl $scope todoStorage)
  ($scope.$watch
   "todos.length"
   (fn [] (todoStorage.put $scope.todos))))
