

isMultipleFromCollection :: (Integral a) => [a] -> a -> Bool
isMultipleFromCollection [] value         = False
isMultipleFromCollection divisors value   =  foldr (\x -> (||) (value `mod` x == 0)) False divisors

sumOfMultiples :: (Integral a) => a -> [a] -> a
sumOfMultiples n multiplesColl = sum $ filter (isMultipleFromCollection multiplesColl) [1..(n-1)]

main = do 
    let isFive = sumOfMultiples 1000 [3,5]
    print isFive

