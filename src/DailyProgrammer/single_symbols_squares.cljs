(ns DailyProgrammer.single_symbols_squares)

; Description
; Given a grid size N, find an NxN layout of X's and O's such that no axis-aligned square (2x2 or larger)
; within the grid has the same symbol at each of its four corners. That is, if four cells of the grid form a square,
; they must not be either all X's or all O's.

; For instance, given N = 5, the following would not be a valid output:

; O O O X X
; X X O O O
; X O X O X
; O X O O X
; X O X X O
; because there's a 3x3 square whose four corners are all X's:

; . . . . .
; . . . . .
; X . X . .
; . . . . .
; X . X . .
; Example input
; 5
; Example output
; O O O X X
; X X O O O
; O O X O X
; O X O O X
; X O X X O
; Run time
; To qualify as a solution to this challenge, you must actually run your program through to completion for N = 6.
; It's not enough to write a program that will eventually complete. Post your solution along with your code.

; (If you find this too hard, try to at least complete N = 4.)

; Optional Bonus 1
; Find a solution for N = 10.

; Optional Bonus 2
; (Let's consider this to be this week's Hard problem.)

; For N = 32, generate an output with as few single-symbol squares as possible. (I have no idea what's a good score here, or if 0 is even possible.)

; Here's some Python that will tell you the number of single-symbol squares for a grid formatted like the example:

; import sys
; grid = [line.strip().split() for line in sys.stdin if line.strip()]
; N = len(grid)
; assert all(len(line) == N for line in grid)
; # For the square with upper-left corner (x, y) with side length d+1,
; # are the four corners of the square the same?
; def square_is_single(x, y, d):
;     corners = [grid[x+a][y+b] for a in (0, d) for b in (0, d)]
;     return len(set(corners)) == 1
; def squares():
;     for x in range(N):
;         for y in range(N):
;             for d in range(1, N):
;                 if x + d < N and y + d < N:
;                     yield x, y, d
; print(sum(square_is_single(x, y, d) for x, y, d in squares()))


(def invalid-grid
  [["O" "O" "O" "X" "X"]
   ["X" "X" "O" "O" "O"]
   ["X" "O" "X" "O" "X"]
   ["O" "X" "O" "O" "X"]
   ["X" "O" "X" "X" "O"]])


(def valid-grid [["O" "O" "O" "X" "X"]
                 ["X" "X" "O" "O" "O"]
                 ["O" "O" "X" "O" "X"]
                 ["O" "X" "O" "O" "X"]
                 ["X" "O" "X" "X" "O"]])

(def threexthree [["O" "O" "O"]
                  ["X" "X" "O"]
                  ["O" "O" "X"]])

(defn get-xy [grid y x]
  (if (or (>= x (count (nth grid 0))) (>= x (count grid)))
    nil
    (nth (nth grid y) x)))

(defn grid-last-index [grid] (->> grid first count dec))


;; VALIDATION LOGIC
(defn valid-heterogenous-square? [grid]
  (let [top-left (get-xy grid 0 0)
        top-right (get-xy grid 0 (grid-last-index grid))
        bottom-left (get-xy grid (grid-last-index grid) 0)
        bottom-right (get-xy grid (grid-last-index grid) (grid-last-index grid))]
    (let [XO-sameness (reduce
                       #(if (= %1 %2) %2 (reduced :not-homogenious))
                       '(top-left top-right bottom-left bottom-right))]
      (if (= :not-homogenious XO-sameness) true false))))

(defn square-exists? [grid square-size index-x index-y]
  (if (nil? (get-xy grid (+ index-y (dec square-size)) (+ index-x (dec square-size))))
    false
    true))

(defn get-square-by-index [grid square-size index-x index-y]
  (if (square-exists? grid square-size index-x index-y)
    (vec (map #(subvec %1 index-x (+ index-x square-size)) grid))
    nil))

(defn get-all-squares-at-index [grid index-x index-y]
  (loop [result '()
         square-size 2]
    (if (square-exists? grid square-size index-x index-y)
      (let [square (get-square-by-index grid square-size index-x index-y)]
        (recur (conj result square) (inc square-size)))
      result)))



(defn main [& cli-args]
  (println (get-all-squares-at-index invalid-grid 1 1)))










