# def print_inverted_mirrored_right_angle_triangle(symbols, index, number_of_rows):
#     if index < len(symbols):
#         for i in range(number_of_rows):
#             for j in range(0, i):
#                 print(" ", end = "")
#             for j in range(i, number_of_rows):
#                 print(symbols[index], end = "")
#             print()
#     else:
#         for i in range(number_of_rows):
#             add = 0
#             for j in range(0, i):
#                 print(" ", end = "")
#             for j in range(i, number_of_rows):
#                 print(index + add, end = "")
#                 add += 1
#             print()
#
# print_inverted_mirrored_right_angle_triangle(['%', '$', '#'], 3, 5)





def print_inverted_mirrored_right_angle_triangle(symbols, index, number_of_rows):
    for i in range(number_of_rows):
        for j in range(0, i):
            print(" ", end="")

        if index < len(symbols):
            for j in range(i, number_of_rows):
                print(symbols[index], end = "")
            print()
        else:
            add = 0
            for j in range(i, number_of_rows):
                print(index + add, end="")
                add += 1
            print()

print_inverted_mirrored_right_angle_triangle(['%', '$', '#'], 2, 5)
