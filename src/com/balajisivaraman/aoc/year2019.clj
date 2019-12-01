(ns com.balajisivaraman.aoc.year2019)

(defn day01a
  [input]
  (let [calc-fuel
        (fn [i]
          (-> i
              (/ 3)
              (Math/floor)
              (- 2)))]
    (->> input
         (map calc-fuel)
         (reduce + 0))))
