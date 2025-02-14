# Module 1

## Refletion 1
Setelah mengevaluasi code saya, saya rasa prinsip-prinsip clean code dan praktek secure coding sudah terdapat pada code saya, biarpun mungkin belum sempurna. Pertama, saya menerapkan prinsip 'meaningful names' yang mana nama-nama yang diberikan terhadap apapun (bisa class, variable, method, function, dll.) di code ini sudah cukup self-explanatory kegunaannya, seperti deleteById, findById, allProduct, productIterator, dsb. 
Kedua, pada code sudah meminimalisir penggunaan comments yang tidak diperlukan (atau excessive), dengan memberikan nama deskriptif pada code saya.
Ketiga, diterapkan Single Responsibility Principle, yang mana setiap class hanya bertanggung jawab terhadap satu tugas utama, seperti ProductController yang mengatasi http request dan response, ProductRepository yang mengatasi akses data (melakukan perubahan pada database).
Keempat, meminimalisir dilakukannya duplicate code terutama pada class-class utama aplikasi. Namun pada aspek ini, sepertinya masih bisa dilakukan pengurangan duplikasi code pada template-template html-nya



## Reflection 2 

1. Saya merasa cukup puas setelah coding unit test untuk app ini. Karena unit test saat ini merupakan sesuatu komponen yang penting ketika mengembangkan suatu program aplikasi untuk memastikan segala komponenen berjalan dengan lancar tanpa kita harus mengecek secara manual. Selain itu, unit test akan secara signifikan membantu kita pada proses debugging dengan memudahkan identifikasi masalah.
  Menurut saya, sepertinya tidak ada angka tepat tentang seberapa banyak unit test yang harus dibuat, karena jika diiterasikan, terdapat tak hingga banyak kemungkinan yang bisa dicek. Maka dari itu, menurut saya sekedar memberikan positive test, negative test, dan bahkan boundary test yang mencakup kasus-kasus berbeda (dan relevan) sudah cukup memberikan gambaran verifikasi pada komponen-komponen terkecil pada kita (unit test)
  Jika mendapatkan 100% code coverage pada unit test, bukan berarti code kita sudah tanpa eror atau bugs, karena 100% adalah relatif terhadap test yang kita buat sehingga bisa saja terdapat unchecked case yang menimbulkan eror. Selain itu terdapat masalah logic error, yang tentu tidak bisa dilihat dari 100% code coverage.
2. Menurut saya, menambahkan suatu test suite yang memverifikasi jumlah item pada product list akan cukup mengurangi kualitas code secara keseluruhan. Dari proses yang dieksekusi, pastinya kita akan melakukan create instance product pada test suite ini (kemungkinan besar lebih dari 1), sedangkan create product juga kita lakukan pada test suite lain. Hal ini berarti terdapat repetasi code dan flow yang bisa disimplifikasi dengan grouping test case ini dalam satu class.
   Belum lagi inisialiasi variabel yang dilakukan seperti baseUrl akan diulang pada 3 functional test yang berbeda. Hal ini bisa kita buat lebih baik dengan melakukan membuat suatu base functional test class, sehingga segala setup dasar yang akan dilakukan untuk functional test apapun sudah ada pada class ini.
