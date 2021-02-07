def get_average_number_of_vowels(words_List):
    average = 0

    if words_List:
        total = 0
        vowelList = []

        for i in range(len(words_List)):
            for letter in words_List[i]:
                if letter in ['a', 'e', 'i', 'o', 'u']:
                    total += 1
                    if letter not in vowelList:
                        vowelList.append(letter)

        variety = len(vowelList)
        if variety != 0:
            average = round(total / variety, 1)

    else:
        average = 0

    return average

words = ['hello', 'whyaeiou']
print(get_average_number_of_vowels(words))
