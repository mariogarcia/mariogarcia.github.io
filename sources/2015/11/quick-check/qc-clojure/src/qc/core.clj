(ns qc.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn is-digit
  "Checks whether a string of size 1 is a digit or not"
  [st]
  (Character/isDigit (first st)))

(defn to-int
  "Converts a given string to an integer"
  [st]
  (Integer/parseInt st))

;; tag::sumnumbers[]
(defn sum-numbers
  "Adds up all numbers within a CSV line expression"
  [line]
  (if (empty? line) 0 ;; <1>
    (let [elements (str/split line #",")] ;; <2>
      (->> elements
           (filter is-digit) ;; <3>
           (map to-int) ;; <4>
           (reduce +))))) ;; <5>
;; end::sumnumbers[]
