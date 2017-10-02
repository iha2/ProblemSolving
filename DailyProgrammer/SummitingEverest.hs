-- [2017-09-20] Challenge #332 [Intermediate] Training for Summiting Everest


summits :: (Num a) => [a]
summits = [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15]
summits2 = [1, 2, 20, 13, 6, 15, 16, 0, 7, 9, 4, 0, 4, 6, 7, 8, 10, 18, 14, 10, 17, 15, 19, 0, 4, 2, 12, 6, 10, 5, 12, 2, 1, 7, 12, 12, 10, 8, 9, 2, 20, 19, 20, 17, 5, 19, 0, 11, 5, 20]

-- 0 8 4 12 2 10 6 14 1 9 5 13 3 11 7 15

maxSummit :: (Integral a) => [a] -> [a] -> [a]
maxSummit [] (x:xs)                  = maxSummit [x] xs
maxSummit resultSequence []       = resultSequence
maxSummit (lastElem:ys) (current:xs)
            | lastElem > current  = if length lesserConsidered > length lesserIgnored then lesserConsidered else lesserIgnored
            | otherwise           = maxSummit (current:lastElem: ys) xs
            where
                lesserConsidered = maxSummit (current: filter (< current) ys) xs
                lesserIgnored    = maxSummit (lastElem:ys) xs                                                                                                                                            
main :: IO ()
main = print $ maxSummit [] summits2