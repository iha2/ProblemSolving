
-- If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

-- Find the sum of all the multiples of 3 or 5 below 1000.


isMultiple :: (Integral a) => [a] -> a -> Bool
isMultiple [] _         = False
isMultiple divisors value   = foldr ((||) . (==) 0 . mod value) False divisors

sumOfMultiples :: (Integral a) => a -> [a] -> a
sumOfMultiples n multiplesColl = sum $ filter (isMultiple multiplesColl) [1..(n-1)]

main :: IO ()
main = do 
    let isFive = sumOfMultiples 1000 [3,5]
    print isFive