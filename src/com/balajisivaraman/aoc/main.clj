(ns com.balajisivaraman.aoc.main
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :refer [split, split-lines, trim-newline]]
   [clojure.java.io :refer [reader]]
   [com.balajisivaraman.aoc.year2019 :refer :all]))

(defn- read-input
  [filename]
  (->> filename
       (clojure.java.io/resource)
       (slurp)
       (split-lines)
       (map #(Integer/parseInt %1))))

(defn- read-input-b
  [filename]
  (-> filename
       (clojure.java.io/resource)
       (slurp)
       (trim-newline)
       (split #",")
       (->>
        (map #(Integer/parseInt %1)))
       (vec)))

(defn- read-input-c [filename]
  (-> filename
     (clojure.java.io/resource)
     (reader)
     (line-seq)
     (->>
      (map #(split %1 #","))
      (vec))
     (vec)))

(defn run
  [problem]
  (println
   (match [problem]
          ["day01a"] (day01a (read-input "input/day01"))
          ["day01b"] (day01b (read-input "input/day01"))
          ["day02a"] (day02a (read-input-b "input/day02"))
          ["day02b"] (day02b (read-input-b "input/day02"))
          ["day03a"] (let [parsed (read-input-c "input/day03")
                           wire1-input (first parsed)
                           wire2-input (last parsed)]
                       (day03a wire1-input wire2-input))
          :else "Problem not found")))

(defn -main
  [& args]
  (if (< (count args) 1)
    (println "Usage: adventofcode day01a")
    (run (nth args 0))))
