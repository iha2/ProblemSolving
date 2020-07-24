// Implement a method to perform basic string compression using the counts of
// repeated characters. rg. aabcccccaaa will become a2b1c5a3
// if the compressed string isn't less ni length than the original, return the
// original string

export const stringCompressor = (string) => {
  const characters = string.split("");

  const characterMap = characters.reduce((charMap, character) => {
    return charMap[character]
      ? { ...charMap, [character]: charMap[character] + 1 }
      : { ...charMap, [character]: 1 };
  }, {});

  const result = Object.keys(characterMap).reduce(
    (compressedString, character) => {
      return `${compressedString}${character}${characterMap[character]}`;
    },
    ""
  );

  return result.length >= string.length ? string : result;
};

