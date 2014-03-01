(ns example.gyr.recipes
  (:require [goog.object :as o])
  (:use-macros [purnam.core :only [obj arr ! def.n]]
               [gyr.core :only [def.module def.config def.factory
                                def.controller def.service]]))

(def JSONP (obj))

(! js/window.jsonsearch
   (fn [data]
     (js/console.log "Failed")
     (! JSONP.data data)))

(def.module recipesDemo [])

(def.controller recipesDemo.RecipesMainCtrl [$scope $http]
  (! $scope.recipe "chocolate")
  
  (! $scope.url "http://www.recipepuppy.com/api")

  (! $scope.searchExamples
    (fn [url]
      (->
       ($http.post url)
       (.success (fn [res]
                    (! $scope.data res))))))

  (! $scope.searchRecipes
    (fn [url]
      (->
       ($http (obj :method "JSONP"
                   :params {:q $scope.recipe
                            :callback "jsonsearch"}
                   :url url))
       (.success (fn [res]))
       (.error (fn []
         (js/console.log "Failed")
         (! $scope.data JSONP.data)))))))
