
-- 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

-- What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?


divisors :: [Integer]
divisors = [1 .. 20]


isDivisible :: Integer -> Bool
isDivisible value = foldr ((&&) . (==) 0 . mod value) True divisors
--isDivisible value = foldr (\x -> (&&)  (value `mod` x == 0)) True divisors

findMaxDivisibleUnderTwenty :: Integer
findMaxDivisibleUnderTwenty = 20 + last (takeWhile (not . isDivisible) [20, 40..])

main :: IO()
main = print findMaxDivisibleUnderTwenty

