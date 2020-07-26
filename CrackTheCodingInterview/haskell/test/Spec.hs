import Chapter1.OnePointThree ( isPermutation )
import Test.HUnit


isPalindromeTest =
  TestCase $ assertBool "should be able to catch permutation normal and larmon" $ isPermutation "normal" "larmon"

main :: IO ()
main = do
  runTestTT $ TestList [isPalindromeTest]
  return ()
