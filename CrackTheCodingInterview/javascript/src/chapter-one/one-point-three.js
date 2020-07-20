// 1.3 Given 2 string, check if one is a permutation of the other
// normal larmon
//

const checkPermutation = (firstString, secondString) => {
  const firstStringCharacters = firstString.split("");
  const secondStringCharacters = secondString.split("");

  if (secondStringCharacters.length !== firstStringCharacters.length) {
    return false;
  } else {
    const resultMap = firstStringCharacters.reduce((result, character) => {
      if (result[character]) {
        return { ...result, [character]: result[character] + 1 };
      } else {
        return { ...result, [character]: 1 };
      }
    }, {});

    const nextResultMap = secondStringCharacters.reduce((result, character) => {
      if (result[character]) {
        return { ...result, [character]: result[character] + 1 };
      } else {
        return { ...result, [character]: 1 };
      }
    }, {});

    return Object.keys(nextResultMap).reduce((result, key) => {
      return result && !!nextResultMap[key] && !!resultMap[key];
    }, true);
  }
};

console.log(checkPermutation("larmon", "normal"));
console.log(checkPermutation("wweerrtt", "wewertrt"));
