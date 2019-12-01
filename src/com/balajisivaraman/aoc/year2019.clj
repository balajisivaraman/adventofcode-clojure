(ns com.balajisivaraman.aoc.year2019)

(defn- calc-fuel
  [input]
  (-> input
      (/ 3)
      (Math/floor)
      (- 2)))

(defn day01a
  [input]
  (->> input
       (map calc-fuel)
       (reduce + 0)))

(defn- calc-additional-fuel
  [input]
  (loop [required-fuel (calc-fuel input)
         acc 0]
    (if (< required-fuel 0)
      acc
      (recur (calc-fuel required-fuel) (+ acc required-fuel)))))

(defn day01b
  [input]
  (->> input
       (map calc-additional-fuel)
       (reduce + 0)))
