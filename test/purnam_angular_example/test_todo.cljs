(ns purnam-angular-examples.test-todo
  (:require [purnam-angular-examples.todo :as app])
  (:use [purnam.cljs :only [aset-in aget-in]])
  (:use-macros
   [purnam.js :only [obj arr !]]
   [purnam.test :only [init describe it is]]
   [purnam.test.angular :only [describe.ng describe.controller 
                               it-uses it-compiles]]))

(init)

(describe.ng
  {:doc  "Testing Storage"
   :module todoDemo
   :inject [todoStorage]
   :globals [default-value
             (arr {:text "Learn Clojure" :done false}
                  {:text "Learn Javascript" :done false}
                  {:text "Learn Angular" :done false})]}

  (it "Default Values"
      (is (todoStorage.get) default-value))

   (it "Putting Arrays"
       (todoStorage.put (arr {:text "Do Chores" :done false}))
       (is (todoStorage.get)
           (arr {:text "Do Chores" :done false}))


       (todoStorage.put (arr))
       (is (todoStorage.get) default-value)))

(describe.controller
  {:doc "Testing Main Controller"
   :module todoDemo
   :controller TodoMainCtrl}

  (it "should have 3 todos"
    (is (count $scope.todos) 3))

  (it "should clear completed todos"
    (! $scope.todos.0.done true)
    (! $scope.todos.1.done true)
    ($scope.clearCompletedTodos)
    (is (count $scope.todos) 1))

  (it "should add another todo to list"
    ($scope.addTodo)
    (is (count $scope.todos) 4)))