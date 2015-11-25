(ns qc.core-test
  (:require [clojure.test :refer :all]
            [qc.core :refer :all]
            [clojure.string :as str]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct :refer (defspec)]))

(defn join-chars
  [chars]
  (str/join "," chars))

(defspec check-adding-up-numbers-from-line
  100 ;; <1>
  (prop/for-all [v (gen/vector gen/char-alphanumeric)] ;; <2>
                (let [line (join-chars v)
                      reversed-line (join-chars (reverse v))]
                  (= (sum-numbers line) ;; <3>
                     (sum-numbers reversed-line))))) ;; <4>
