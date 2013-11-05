(ns purnam-angular-examples.test-login
  (:require [purnam-angular-examples.login :as app])
  (:use [purnam.cljs :only [aset-in aget-in]])
  (:use-macros
   [purnam.js :only [obj arr !]]
   [purnam.test :only [init describe it is]]
   [purnam.test.angular :only [describe.ng describe.controller 
                               it-uses it-compiles]]))

(describe.controller
 {:doc "Testing"
  :module recipiesDemo
  :controller RecipesMainCtrl
  :inject [[$httpBackend
            ([$httpBackend]
               (do (-> $httpBackend
                       (.when "POST" "/examples")
                       (.respond 200 (js/JSON.stringify 
                                       (arr {:title "Chocolate Cake"}
                                            {:title "Citrus Pudding"}
                                            {:title "Lemon Tart"}))))
                   $httpBackend))]]
  :vars [o (range 20)]}

  (it "should filled up $scope.data"
    ($scope.searchRecipes $scope.url)
    (is $scope.data (arr {:title "Chocolate Cake"}
                         {:title "Citrus Pudding"}
                         {:title "Lemon Tart"})))
  )