bags = dict()
original_target = "shinygold"
containers = set()
bag_count = 0 

def readInput() -> list:
    with open('input_seven.txt') as file:
        data = file.readlines()

    return data

def createMap(line : str):
    words = line.split()
    key = words[0] + words[1]

    if words[4] == "no":
        return
    
    bags[key] = dict()
    idx = 5
    while idx < len(words):
        color = words[idx] + words[idx + 1]
        if color not in bags[key]:
            bags[key][color] = 0
        bags[key][color] += int(words[idx - 1])
        idx += 4

def findTarget(bags, target, containers):
    for key, val in bags.items():
        if target in val:
            containers.add(key)
            findTarget(bags, key, containers)

def countBags(bags, target):
    if target not in bags:
        return 0
    
    bag_count = 0
    for bag, count in bags[target].items():
        bag_count += count + count * countBags(bags, bag)
        
    return bag_count
       
if __name__ == "__main__":
    data = readInput()
    for line in data:
        createMap(line)

    findTarget(bags, original_target, containers)
    print(len(containers))
    print(countBags(bags, original_target))
