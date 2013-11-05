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
   :inject [todoStorage]}

  (it "Default Values"
      (is (todoStorage.get) 
          (arr {:text "Learn Clojure" :done false}
               {:text "Learn Javascript" :done false}
               {:text "Learn Angular" :done false})))

   (it "Putting"
       (todoStorage.put (arr {:text "Do Chores" :done false}))
       (is (todoStorage.get)
           (arr {:text "Do Chores" :done false})
            )))