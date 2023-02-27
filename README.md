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

# Format API

## Thiết lập Swagger để dễ dàng quan sát.

Có thể truy cập vào swagger bằng đường link [http://localhost:8080/api/v1/swagger-ui.html](http://localhost:8080/api/v1/swagger-ui.html) sau khi khởi động app.

## Format kiểu trả về cho API.

Cấu trúc chính:

``````
{
  "data": "Dữ liệu trả về",

  "message": "Vài tin nhắn",

  "status": Status code
}
``````

Trong đó dữ liệu trả về có 3 dạng:

1. Kiểu list dữ liệu: cần có cả thông tin phân trang. Thường dùng ở các controller get list. Ví dụ

``````
"pagination": {
      "current": 0,
      "total": 1,
      "total_element": 1
    },
    "Tên entity": [
      {
        "Prop1": "Value1",
        "Prop2": "Value2",
      }
    ]
``````
2. Kiểu đơn giá trị (Thường dùng trong lấy 1 item)

``````
"Tên entity": [
      {
        "Prop1": "Value1",
        "Prop2": "Value2",
      }
``````

3. Kiểu lỗi (Khi có lỗi xảy ra liên quan đến post, put):
``````
"Tên prop bị lỗi": "vài thông tin lỗi" 
``````

Trong trường hợp không có data, hãy để null. Tương tự với trường message.




