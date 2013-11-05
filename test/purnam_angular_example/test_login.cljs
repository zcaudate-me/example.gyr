(ns purnam-angular-examples.test-login
  (:require [purnam-angular-examples.login :as app])
  (:use [purnam.cljs :only [aset-in aget-in]])
  (:use-macros
   [purnam.js :only [obj arr !]]
   [purnam.test :only [init describe it is]]
   [purnam.test.angular :only [describe.ng describe.controller 
                               it-uses it-compiles]]))

(init)

(describe.ng
  {:doc  "Testing Db"
   :module loginDemo
   :inject [Db]}

  (it "Is just an atom"
      (is Db #(instance? Atom %)))
      
  (it "Has five users"
      (is (-> Db deref :users count) 5))
  
  (it "Contains user: dylan, pass: bob"    
      (is (get-in @Db [:users "dylan"]) "bob")))


(describe.ng
  {:doc  "Testing Alternate Db"
   :module loginDemo}

  (it-uses [Db] "Injecting Db"
      (is Db #(instance? Atom %))))

(describe.ng
 {:doc  "Testing Heading-One Directive"
  :module loginDemo}

 (it-compiles [ele "<div heading-one>User</div heading-one>"]
  (is (ele.html) "<h1>User</h1>")))

(describe.ng
  {:doc  "Testing $mustache Service"
   :module loginDemo
   :inject [$mustache]}

  (it "Can compile strings"
    (is ($mustache "Hello {{thing}}!" (obj :thing "World"))
        "Hello World!")))

(describe.ng
  {:doc  "Testing Db"
   :module loginDemo
   :inject [App]}
  
  (it "Has statusMsg"
    (is App.statusMsg 
        (obj :loggedOut "Please Log In"
             :loggedIn "Logged In, Welcome {{user}}"
             :passwordIncorrect "Password for {{user}} Incorrect. Please Try Again"
             :noUserFound "Username {{user}} not Found. Please Try Again:"))))
             
(describe.controller
  {:doc "Testing LoginMainCtrl"
   :module loginDemo
   :controller LoginMainCtrl}

  (it "should set a message within the $scope"
    (is $scope.loginText "")  ;; The $scope is automatically registered for us
    (is $scope.passwordText ""))
    
  (it "should render status messages"
    (is ($scope.render "{{app.statusMsg.loggedOut}}") 
        "Please Log In")
        
    (! $scope.user "chris")
    (is ($scope.render "Logged In, Welcome {{user}}")
        "Logged In, Welcome chris"))
    
  (it "should be able to login and logout"
    ($scope.login "dylan" "bob")
    (is $scope.status "loggedIn")
    (is $scope.user   "dylan")
    
    ($scope.logout)
    (is $scope.status "loggedOut")
    (is $scope.user   nil?))
    
  (it "should set status to noUserFound if no user is found"
    ($scope.login "presley" "elvis")
    (is $scope.status "noUserFound")
    (is $scope.user   "presley"))
    
  (it "should set status to passwordIncorrect if no user is found"
    ($scope.login "dylan" "cam")
    (is $scope.status "passwordIncorrect")
    (is $scope.user   "dylan")))