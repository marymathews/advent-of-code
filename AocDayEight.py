JUMP = "jmp"
ACCUMULATE = "acc"
IGNORE = "nop"

def getInput():
    data = []
    with open('input_eight.txt') as file:
        for line in file.readlines():
            data.append(line.rstrip("\n"))

    return data

def execute(instructions):
    acc = 0
    executed = set()
    idx = 0
    next_idx = 0

    for idx in range(len(instructions)):
        idx = next_idx 

        if idx < 0 or idx >= len(instructions):
            return acc, False

        if idx in executed:
            return acc, True 
        
        executed.add(idx)

        if ACCUMULATE in instructions[idx]:
            acc += int(instructions[idx].split()[1])
        elif JUMP in instructions[idx]:
            next_idx += int(instructions[idx].split()[1]) - 1
        
        next_idx += 1
        
    return acc, False

def iterate(instructions):
    temp = instructions.copy()

    for idx, instr in enumerate(instructions):
        if IGNORE in instr:
            temp[idx] = JUMP + " " + instr.split()[1]
        
        elif JUMP in instr:
            temp[idx] = IGNORE + " " + instr.split()[1]

        res = execute(temp)
        if not res[1]:
            return res[0]
            
        temp[idx] = instr

if __name__ == "__main__":
    instructions = getInput()
    print(execute(instructions)[0])
    print(iterate(instructions))