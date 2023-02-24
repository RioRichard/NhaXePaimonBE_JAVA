# ĐÂY CHỈ LÀ DỮ LIỆU TẠO HÌNH, KHÔNG HỀ CHÍNH XÁC

Mục đích của những dữ liệu này là để biết collection nào sẽ có những mục gì.

Vì thế, các dữ liệu sẽ không chính xác. **VÀ CÁC DỮ LIỆU SẼ KHÔNG JOIN ĐƯỢC VỚI NHAU**

Do đó, sau khi kết nối với mongodb trong BE, nếu chưa có dữ liệu tự tạo thì chỉ nên query 1 collection, không nên query join các collection với nhau

# Cách sử dụng.

Vào MongoDB Compass, tiến hành tạo database mới

Tiếp đến, tạo các collection dựa trên tên file json (Chẳng hạn bases.json thì tạo colections tên bases).

Cuối cùng, nhấn nút Add data => Import file => Chọn file và chọn loại file là Json => Import.

Làm lần lượt hết tất cả các file Json là được.

### Update by Rio :>

