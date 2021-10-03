bags = dict()
original_target = "shinygold"
containers = set()

def readInput() -> list:
    with open('input_seven.txt') as file:
        data = file.readlines()

    return data

def createMap(line : str):
    words = line.split()
    key = words[0] + words[1]

    if words[4] == "no":
        return
    
    bags[key] = set()
    idx = 5
    while idx < len(words):
        bags[key].add(words[idx] + words[idx + 1])
        idx += 4

def findTarget(bags, target, containers):
    for key, val in bags.items():
        if target in val:
            containers.add(key)
            findTarget(bags, key, containers)

if __name__ == "__main__":
    data = readInput()
    for line in data:
        createMap(line)

    findTarget(bags, original_target, containers)
    print(len(containers))
