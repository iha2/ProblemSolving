module Primes where
    
    divList :: (Num a) => [a]
    divList = [2,3,5,7]
    
    -- checks if number is divisible by members of a list
    isDiv :: (Num a, Integral a) => [a] -> a -> Bool
    isDiv [] b = False
    isDiv divisors b = foldr (\x -> (||) (b `mod` x == 0)) False divisors
    
    -- checks if number is divisible by the numbers in divList
    checkDiv = isDiv divList
    
    
    -- uses fermats theorem check for the possibility of a 
    -- prime value
    fermatsPrimalityTest :: Integer -> Integer -> Int
    fermatsPrimalityTest a p = if ( a ^(p-1) ) `mod` p == 1
                      then 1
                      else 2
    
    
    -- does a fermat test a certain number of times
    -- enter a number less than one. that fraction will be used
    -- to get the number. The lower the values the greater the
    -- number of tests
    fermatTester :: Integer -> Double -> Integer -> [Int]
    fermatTester old_base ratio prime_val
     | prime_val `elem` divList    = [1]
     | old_base > 0     = let base = floor (fromIntegral old_base - (fromIntegral prime_val * ratio))
                          in fermatsPrimalityTest (toInteger base) prime_val : fermatTester base ratio prime_val
     | otherwise        = [] 
    
    
    removeByDivisor :: [Integer] -> [Integer]
    removeByDivisor = filter (not . checkDiv)
    
    -- generates list of primes of value lower than the specified number
    isPrime :: Integer -> Bool
    isPrime value
     | value `elem` divList     = True
     | isDiv [2,3,5,7] value    = False
     | maximum (fermatTester value 0.25 value) == 2   = False
     | maximum (fermatTester value 0.25 value) == 1   = not $ isDiv (removeByDivisor [2.. floor $ sqrt $ fromIntegral value]) value