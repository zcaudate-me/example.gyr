(ns purnam-angular-examples.test-recipes
  (:require [purnam-angular-examples.recipes :as app])
  (:use [purnam.cljs :only [aset-in aget-in]])
  (:use-macros
   [purnam.js :only [obj arr !]]
   [purnam.test :only [init describe it is]]
   [purnam.test.angular :only [describe.ng describe.controller 
                               it-uses it-compiles]]))

(init)

(describe.controller
 {:doc "Testing"
  :module recipesDemo
  :controller RecipesMainCtrl
  :inject [[$httpBackend
            ([$httpBackend]
               (do (-> $httpBackend
                       (.when "POST" "/examples")
                       (.respond 200 
                            (js/JSON.stringify 
                              (arr {:title "Chocolate Cake"}
                                   {:title "Citrus Pudding"}
                                   {:title "Lemon Tart"}))))
                   $httpBackend))]]
  :vars [o (range 20)]}

  (it "should have a recipe search for choclate"
    (is $scope.recipe "chocolate"))

  (it "should filled up $scope.data"
    ($scope.searchExamples "/examples")
    ($httpBackend.flush)
    (is $scope.data (arr {:title "Chocolate Cake"}
                         {:title "Citrus Pudding"}
                         {:title "Lemon Tart"})))
  )