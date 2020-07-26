import Chapter1.OnePointThree ( isPermutation )
import Chapter1.OnePointFive ( stringCompressor )
import Test.HUnit


isPalindromeTest =
  TestCase $ assertBool "should be able to catch permutation normal and larmon" $ isPermutation "normal" "larmon"

shouldCompressAString = TestCase $ assertEqual "should produce a compressed string" (stringCompressor "qweqweeqweeeeertgggggggggggggggggggggfdsa") "a1s1d1f1g21t1r1e5w1q1e2w1q1e1w1q1" 

shouldReturnTheSameString = TestCase $ assertEqual "should return the same string" (stringCompressor "qweqweeqweeeeertgggggggggggggfdsa") "qweqweeqweeeeertgggggggggggggfdsa"

main :: IO ()
main = do
  runTestTT $ TestList [isPalindromeTest, shouldCompressAString, shouldReturnTheSameString]
  return ()

