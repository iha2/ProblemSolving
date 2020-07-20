{-# LANGUAGE OverloadedStrings #-}

module Chapter1.OnePointThree
  ( stringToMap
  )
where

-- 1.3 Given 2 string, check if one is a permutation of the other
-- normal larmon

import qualified Data.Text                     as T
import qualified Data.Map                      as M
import           Data.Maybe

stringToMap :: M.Map Char Int -> String -> M.Map Char Int
stringToMap stringMap [] = stringMap
stringToMap stringMap (character : characters) =
  if Data.Maybe.isJust $ M.lookup character stringMap
    then stringToMap (M.insertWith (+) character 1 stringMap) characters
    else stringToMap (M.insert character 1 stringMap) characters

stringHash :: String -> Int
stringHash string = foldr (\x y -> (fromEnum (fst x) * snd x) + y) 0 $ M.toList $ stringToMap M.empty string

isPermutation :: String -> String -> Bool
isPermutation firstString secondString = stringHash firstString == stringHash secondString
