# Cơ sở dữ liệu hệ thống quản lý sinh viên

Hệ thống cơ sở dữ liệu này được thiết kế để quản lý thông tin sinh viên, lớp học, khóa học, điểm số và giảng viên trong một khoa của trường đại học.

## Bảng: department

| Tên trường            | Kiểu dữ liệu                    | Mô tả                                     |
|-----------------------|---------------------------------|-------------------------------------------|
| department_id         | INT, AUTO_INCREMENT, PRIMARY KEY| Mã định danh duy nhất cho mỗi khoa        |
| department_name       | VARCHAR(100), NOT NULL          | Tên khoa                                  |

## Bảng: class

| Tên trường            | Kiểu dữ liệu                    | Mô tả                                     |
|-----------------------|---------------------------------|-------------------------------------------|
| class_id              | INT, AUTO_INCREMENT, PRIMARY KEY| Mã định danh duy nhất cho mỗi lớp học     |
| class_name            | VARCHAR(50), NOT NULL           | Tên lớp học                               |
| department_id         | INT                             | Khóa ngoại tham chiếu đến khoa            |

## Bảng: student

| Tên trường            | Kiểu dữ liệu                    | Mô tả                                     |
|-----------------------|---------------------------------|-------------------------------------------|
| student_id            | INT, AUTO_INCREMENT, PRIMARY KEY| Mã định danh duy nhất cho mỗi sinh viên   |
| full_name             | VARCHAR(100), NOT NULL          | Họ tên của sinh viên                      |
| date_of_birth         | DATE                            | Ngày sinh của sinh viên                   |
| gender                | ENUM('Male', 'Female')          | Giới tính của sinh viên                   |
| address               | VARCHAR(255)                    | Địa chỉ của sinh viên                     |
| email                 | VARCHAR(100)                    | Email của sinh viên                       |
| phone_number          | VARCHAR(15)                     | Số điện thoại của sinh viên               |
| class_id              | INT                             | Khóa ngoại tham chiếu đến lớp học         |

## Bảng: subject

| Tên trường            | Kiểu dữ liệu                    | Mô tả                                     |
|-----------------------|---------------------------------|-------------------------------------------|
| subject_id            | INT, AUTO_INCREMENT, PRIMARY KEY| Mã định danh duy nhất cho mỗi môn học     |
| subject_name          | VARCHAR(100), NOT NULL          | Tên môn học                               |
| credit                | INT, NOT NULL                   | Số tín chỉ của môn học                    |
| department_id         | INT                             | Khóa ngoại tham chiếu đến khoa            |

## Bảng: lecturer

| Tên trường            | Kiểu dữ liệu                    | Mô tả                                     |
|-----------------------|---------------------------------|-------------------------------------------|
| lecturer_id           | INT, AUTO_INCREMENT, PRIMARY KEY| Mã định danh duy nhất cho mỗi giảng viên  |
| full_name             | VARCHAR(100), NOT NULL          | Họ tên của giảng viên                     |
| date_of_birth         | DATE                            | Ngày sinh của giảng viên                  |
| gender                | ENUM('Male', 'Female')          | Giới tính của giảng viên                  |
| email                 | VARCHAR(100)                    | Email của giảng viên                      |
| phone_number          | VARCHAR(15)                     | Số điện thoại của giảng viên              |
| department_id         | INT                             | Khóa ngoại tham chiếu đến khoa            |

## Bảng: course

| Tên trường            | Kiểu dữ liệu                    | Mô tả                                     |
|-----------------------|---------------------------------|-------------------------------------------|
| course_id             | INT, AUTO_INCREMENT, PRIMARY KEY| Mã định danh duy nhất cho mỗi khóa học    |
| subject_id            | INT                             | Khóa ngoại tham chiếu đến môn học         |
| lecturer_id           | INT                             | Khóa ngoại tham chiếu đến giảng viên      |
| semester              | VARCHAR(10)                     | Học kỳ mà khóa học được tổ chức           |
| academic_year         | VARCHAR(10)                     | Năm học mà khóa học được tổ chức          |

## Bảng: grade

| Tên trường            | Kiểu dữ liệu                    | Mô tả                                     |
|-----------------------|---------------------------------|-------------------------------------------|
| student_id            | INT                             | Khóa ngoại tham chiếu đến sinh viên       |
| course_id             | INT                             | Khóa ngoại tham chiếu đến khóa học        |
| score                 | FLOAT                           | Điểm số hoặc điểm mà sinh viên nhận được  |
| **Khóa chính**        | **student_id**, **course_id**   | Sự kết hợp của student_id và course_id    |

## Bảng: student_course

| Tên trường            | Kiểu dữ liệu                    | Mô tả                                     |
|-----------------------|---------------------------------|-------------------------------------------|
| student_id            | INT                             | Khóa ngoại tham chiếu đến sinh viên       |
| course_id             | INT                             | Khóa ngoại tham chiếu đến khóa học        |
| **Khóa chính**        | **student_id**, **course_id**   | Sự kết hợp của student_id và course_id    |

## Bảng: subject_lecturer

| Tên trường            | Kiểu dữ liệu                    | Mô tả                                     |
|-----------------------|---------------------------------|-------------------------------------------|
| subject_id            | INT                             | Khóa ngoại tham chiếu đến môn học         |
| lecturer_id           | INT                             | Khóa ngoại tham chiếu đến giảng viên      |
| **Khóa chính**        | **subject_id**, **lecturer_id** | Sự kết hợp của subject_id và lecturer_id  |
