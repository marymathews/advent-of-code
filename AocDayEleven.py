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

with open('input_eleven.txt') as file:
    data = file.readlines()
    for line in data:
        matrix.append(line.rstrip("\n"))

old_matrix = matrix.copy()
new_matrix = matrix.copy()

while True:
    
    old_matrix = new_matrix.copy()

    for r, row in enumerate(old_matrix):
        for c, col in enumerate(row):

            if col == 'L':
                occupied = False 

                for offset in offsets:
                    pos_r = r + offset[0]
                    pos_c = c + offset[1]

                    if pos_r >= len(matrix) or pos_r < 0 or pos_c >= len(row) or pos_c < 0:
                        continue

                    if old_matrix[pos_r][pos_c] == '#':
                        occupied = True
                        break
                    
                if not occupied:
                    update = list(new_matrix[r])
                    update[c] = '#'
                    new_matrix[r] = ''.join(update)
            
            elif col == '#':
                occ_count = 0

                for offset in offsets:
                    pos_r = r + offset[0]
                    pos_c = c + offset[1]

                    if pos_r >= len(matrix) or pos_r < 0 or pos_c >= len(row) or pos_c < 0:
                        continue

                    if old_matrix[pos_r][pos_c] == '#':
                        occ_count += 1
                
                if occ_count >= 4:
                    update = list(new_matrix[r])
                    update[c] = 'L'
                    new_matrix[r] = ''.join(update)
    
    if new_matrix == old_matrix:
        break

occupied_seats = 0

for r in new_matrix:
    for c in r:
        if c == '#':
            occupied_seats += 1

print(occupied_seats)