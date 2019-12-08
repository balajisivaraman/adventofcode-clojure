(ns com.balajisivaraman.aoc.year2019
  (:require [clojure.core.match :refer [match]]
            [clojure.math.combinatorics :as combo]
            [geo.poly :refer [edge-intersection, edges-intersect?]]))

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

(defn- wire-path [input]
  (loop [input input
         current-position [0 0]
         path []]
    (if (empty? input)
      path
      (let [movement (first input)
            direction (.charAt movement 0)
            steps (Integer/parseInt (subs movement 1))
            new-position (match [direction]
                                [\R] [(+ (first current-position) steps) (last current-position)]
                                [\U] [(first current-position) (+ (last current-position) steps)]
                                [\L] [(- (first current-position) steps) (last current-position)]
                                [\D] [(first current-position) (- (last current-position) steps)])
            new-path (conj path [current-position new-position])]
        (recur (rest input) new-position new-path)))))

(defn day03a [wire1-input wire2-input]
  (let [wire1-path (wire-path wire1-input)
        wire2-path (wire-path wire2-input)
        path-overlaps (for [wire1-edge wire1-path
                            wire2-edge wire2-path
                            :when (edges-intersect? wire1-edge wire2-edge)]
                        (edge-intersection wire1-edge wire2-edge))]
    (apply min (map #(+ (Math/abs (first %1)) (Math/abs (last %1))) (vec path-overlaps)))))
