(ns purnam-angular-examples.login
  (:use [purnam.cljs :only [aset-in aget-in]])
  (:use-macros
   [purnam.js :only [! def.n obj]]
   [purnam.angular :only [def.module def.config def.value def.directive
                           def.controller def.service]]))

(def.module loginDemo [])

(def.directive loginDemo.headingOne []
  (fn [$scope element attrs]
    (let [html (element.html)]
      (element.html (str "<h1>" html "</h1>")))))

(def.value loginDemo.$mustache
  js/Mustache.render)

(def.service loginDemo.Db []
  (atom {:users   {"skywalker" "luke"
                   "trump" "donald"
                   "dylan" "bob"
                   "white" "snow"
                   "winfrey" "oprah"}}))
                   
(def.service loginDemo.App []
  (obj
   :statusMsg {:loggedOut "Please Log In"
               :loggedIn "Logged In, Welcome {{user}}"
               :passwordIncorrect "Password for {{user}} Incorrect. Please Try Again"
               :noUserFound "Username {{user}} not Found. Please Try Again:"}))

(def.directive loginDemo.onFocus [$parse]
  (fn [scope elem attrs]
    (let [f ($parse attrs.onFocus)]
      (elem.bind
       "focus"
       (fn [e]
         (scope.$apply (fn [] (f scope (obj :$event e)))))))))

(def.controller loginDemo.LoginMainCtrl [$scope $mustache App Db]
  (! $scope.app App)
  
  (! $scope.render 
    (fn [msg]
      ($mustache msg $scope)))
  
  (! $scope.status "loggedOut")

  (! $scope.loginText "")

  (! $scope.passwordText "")

  (! $scope.alert
     (fn [msg] (js/alert msg)))

  (! $scope.noInput
     (fn [text] (or (nil? text)
                   (= text.length 0))))
  
  (! $scope.allUsers (-> @Db :users seq clj->js))
  
  (! $scope.logout
     (fn []
       (! $scope.user)
       (! $scope.loginText)
       (! $scope.passwordText)
       (! $scope.status "loggedOut")))
       
  (! $scope.login
     (fn [login pass]
       (! $scope.user login)
       (if-let [rpass (get-in @Db [:users login])]
         (if (= pass rpass)
           (! $scope.status "loggedIn")
           (! $scope.status "passwordIncorrect"))
         (! $scope.status "noUserFound")))))
