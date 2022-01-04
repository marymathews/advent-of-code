matrix = list()
offsets = [
    [-1, 0],
    [-1, 1],
    [0, 1],
    [1, 1],
    [1, 0],
    [1, -1],
    [0, -1],
    [-1, -1]
]

def read_input():
    with open('input_eleven.txt') as file:
        data = file.readlines()
        for line in data:
            matrix.append(line.rstrip("\n"))

def count_occupied_seats(matrix):
    occupied_seats = 0

    for r in matrix:
        for c in r:
            if c == '#':
                occupied_seats += 1
    
    return occupied_seats

def get_adjacent_occupied_count(r, c, row, old_matrix):
    count = 0

    for offset in offsets:
        pos_r = r + offset[0]
        pos_c = c + offset[1]

        if pos_r >= len(matrix) or pos_r < 0 or pos_c >= len(row) or pos_c < 0:
            continue

        if old_matrix[pos_r][pos_c] == '#':
            count += 1
        
    return count

def update_matrix():
    old_matrix = matrix.copy()
    new_matrix = matrix.copy()  

    while True:
        old_matrix = new_matrix.copy()

        for r, row in enumerate(old_matrix):
            for c, col in enumerate(row):
                
                update_char = ''
                adj_occ_count = get_adjacent_occupied_count(r, c, row, old_matrix)

                if col == 'L':
                    if adj_occ_count == 0:
                        update_char = '#'
                
                elif col == '#':
                    if adj_occ_count >= 4:
                        update_char = 'L'

                if update_char != '':
                    update = list(new_matrix[r])
                    update[c] = update_char
                    new_matrix[r] = ''.join(update)
        
        if new_matrix == old_matrix:
            break

    return new_matrix

read_input()
new_matrix = update_matrix()
print(count_occupied_seats(new_matrix))