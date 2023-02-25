# Đây là đồ án Quản lí bán vé - phần Backend với ngôn ngữ JAVA của nhóm PAIMON

[Link frontend](https://github.com/RioRichard/NhaXePaimonFE)

# Tạo database: 

[Hãy đọc!](/mongoDB)

# Kết nối database:

## Sử dụng launch.json trên VSCode:

Tìm file launch.json, sau đó chỉnh lại đoạn json như sau:

````
{
    "configurations": [
        {
            "type": "java",
            "name": "Spring Boot-QlBanVePaimonApplication<QLBanVePaimon>",
            "request": "launch",
            "cwd": "${workspaceFolder}",
            "mainClass": "com.paimon.QLBanVePaimon.QlBanVePaimonApplication",
            "projectName": "QLBanVePaimon",
            "args": "",
            "envFile": "${workspaceFolder}/.env",
            "env": {
                "MONGODB_URI":"MongoDB uri",
                "DB_NAME":"Database name"
            }
        }
    ]
}
````

Trong đó cần chỉnh sửa 2 trường "MongoDB uri" và "Database name" sao cho phù hợp với mongodb của bản thân.

## Sử dụng .env file

Tạo 1 file mới tên .env file và điền các thông tin sau:

````
DB_NAME = "Tên db"
MONGODB_URI = "URI của mongodb"
````




