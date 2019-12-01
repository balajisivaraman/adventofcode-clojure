(ns com.balajisivaraman.aoc.main
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :refer [split-lines]]
   [com.balajisivaraman.aoc.year2019 :refer :all]))

(defn- read-input
  [filename]
  (split-lines (slurp (clojure.java.io/resource filename))))

(defn run
  [problem]
  (println
   (match [problem]
          ["day01a"] (day01a (read-input "input/day01a"))
          :else "Problem not found")))

(defn -main
  [& args]
  (if (< (count args) 1)
    (println "Usage: adventofcode day01a")
    (run (nth args 0))))
