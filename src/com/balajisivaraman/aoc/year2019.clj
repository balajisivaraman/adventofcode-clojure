(ns com.balajisivaraman.aoc.year2019
  (:require [clojure.core.match :refer [match]]
            [clojure.math.combinatorics :as combo]))

(defn- calc-fuel [input]
  (-> input
      (/ 3)
      (Math/floor)
      (- 2)))

(defn day01a [input]
  (->> input
       (map calc-fuel)
       (reduce + 0)))

(defn day01b [input]
  (let [calc-additional-fuel
        (fn [input]
          (loop [required-fuel (calc-fuel input)
                 acc 0]
            (if (< required-fuel 0)
              acc
              (recur (calc-fuel required-fuel) (+ acc required-fuel)))))]
    (->> input
         (map calc-additional-fuel)
         (reduce + 0))))

(defn day02a [input]
  (loop [intcode input
         index 0]
    (let [opcode (intcode index)]
      (if (= 99 opcode)
        intcode
        (let [v1 (intcode (intcode (+ index 1)))
              v2 (intcode (intcode (+ index 2)))
              target-position (intcode (+ index 3))
              op (match [opcode]
                        [1] (partial +)
                        [2] (partial *))]
          (recur (assoc intcode target-position (op v1 v2)) (+ index 4)))))))

(defn day02b [input]
  (let [combos (combo/permuted-combinations (range 0 100) 2)
        map-fn (fn [combo]
                 (let [pair (vec combo)
                       noun (pair 0)
                       verb (pair 1)
                       intcode (assoc (assoc input 1 noun) 2 verb)
                       result (day02a intcode)]
                   {:result (result 0) :noun noun :verb verb}))
        reduce-fn (fn [ignored result]
                    (when (= 19690720 (result :result))
                      (reduced (+ (result :verb) (* 100 (result :noun))))))]
    (reduce reduce-fn {:result nil} (map map-fn combos))))
