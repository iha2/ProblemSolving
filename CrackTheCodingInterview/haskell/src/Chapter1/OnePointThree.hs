{-# LANGUAGE OverloadedStrings #-}

module Chapter1.OnePointThree
  ( stringToMap
  , isPermutation
  )
where

-- 1.3 Given 2 string, check if one is a permutation of the other
-- normal larmon

import qualified Data.Text                     as T
import qualified Data.Map                      as M
import           Data.Maybe
import           Data.Char


stringHash :: String -> Int
stringHash  =
  foldl (\hashValue character -> ord character + hashValue) 0 


isPermutation :: String -> String -> Bool
isPermutation firstString secondString = stringHash firstString == stringHash secondString


