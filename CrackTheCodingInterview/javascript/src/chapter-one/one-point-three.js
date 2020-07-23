// Given 2 string check if one is a permutation of the other
// normal larmon

export const stringToValue = (string) => {
  const stringCharacters = string.split("");
  return stringCharacters.reduce((stringValue, character) => {
    return stringValue + character.charCodeAt(0);
  }, 0);
};

export const checkPermutation = (firstString, secondString) => {
  if (firstString.length !== secondString.length) {
    return false;
  } else {
    const firstStringValue = stringToValue(firstString);
    const secondStringValue = stringToValue(secondString);
    return firstStringValue === secondStringValue;
  }
};

