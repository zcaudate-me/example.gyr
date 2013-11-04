(ns purnam-angular-examples.recipes
  (:use [purnam.cljs :only [aget-in aset-in]])
  (:require [goog.object :as o])
  (:use-macros [purnam.js :only [obj arr ! def.n]]
               [purnam.angular :only [def.module def.config def.factory
                                      def.controller def.service]]))

(def JSONP (obj))

(! js/window.jsonsearch
   (fn [data]
     (! JSONP.data data)))

(def.module recipesDemo [])

(def.controller recipesDemo.RecipesMainCtrl [$scope $http]
  (! $scope.recipe "chocolate")
  
  (! $scope.searchRecipes
    (fn []
      (->
       ($http (obj :method "JSONP"
                   :params {:q $scope.recipe
                            :callback "jsonsearch"}
                   :url "http://www.recipepuppy.com/api"))
       (.success (fn [res]))
       (.error (fn []
         (! $scope.data JSONP.data))))))
  (if-not $scope.data
    ($scope.searchRecipes)))
