import random

users = ["\'11111111111\'", "\'12345671111\'", "\'12345672222\'"]
contexts = ["\"好极了\"","\"可以吃\"","\"吃了不会死\"","\"我觉得是一道菜\"","\"这菜真是道菜啊\"","\"不评价了\"","\"我语文不好\"","\"啊对对对\""]
businessIds = ["10001","10002","10003","10004","10005","10006","10007","10008","10009"]
words = ""
with open("add_comment.sql", "w", encoding="utf-8") as f:
    f.write("use elm;\n")
    for i in range(1000):
        index_user = random.randint(0, 2)
        user = users[index_user]
        star = random.randint(1, 5)
        index_context = random.randint(0, 7)
        context = contexts[index_context]
        index_business = random.randint(0, 8)
        businessId = businessIds[index_business]
        foodId = 0;
        if businessId == '10001':
            foodId = random.randint(1, 9)
        elif businessId == '10002':
            foodId = random.randint(10, 12)
        elif businessId == '10003':
            foodId = random.randint(13, 17)
        elif businessId == '10004':
            foodId = random.randint(18, 23)
        elif businessId == '10005':
            foodId = random.randint(24, 29)
        elif businessId == '10006':
            foodId = random.randint(30, 35)
        elif businessId == '10007':
            foodId = random.randint(36, 47)
        elif businessId == '10008':
            foodId = random.randint(48, 53)
        elif businessId == '10009':
            foodId = random.randint(54, 59)
        words += "insert into comment values ( " + user + ", " + businessId + ", " + str(foodId) + ", " + context + ", " + "null, " + str(star) + ", null);\n"
    f.write(words)