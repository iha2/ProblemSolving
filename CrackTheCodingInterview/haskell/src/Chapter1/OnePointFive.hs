{-# LANGUAGE OverloadedStrings #-}

module Chapter1.OnePointFive
  ( stringCompressor
  )
where

--Implement a method to perform basic string compression of repeated characters
-- return the string if the the compressed string is larger than the original


import qualified Data.Text                     as T
import Debug.Trace
import Data.Char

stringCompressor' :: String -> String -> Char -> Int -> String -> String
stringCompressor' compressedString [] lastCharacter number originalString = if length originalString <= length updatedCompressedString then originalString else updatedCompressedString 
   where updatedCompressedString = lastCharacter:intToDigit number:compressedString
stringCompressor' compressedString (next:nextxs) previousChar number originalString 
 | previousChar == next = stringCompressor' compressedString nextxs next (number + 1) originalString
 | otherwise =  stringCompressor' updatedCompressedString nextxs next 1 originalString 
   where updatedCompressedString = previousChar:intToDigit number:compressedString

stringCompressor string = stringCompressor' "" (tail string)  first 1 string
  where first = head string



