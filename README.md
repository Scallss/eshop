Nama: Pascal Hafidz Fajri

NPM: 2306222746

Link Deployment: https://recent-kimberlyn-scallss-44111cf0.koyeb.app/product/list


<details>
    <summary><h2>Module 1</h2></summary>

### Reflection 1
Setelah mengevaluasi code saya, saya rasa prinsip-prinsip clean code dan praktek secure coding sudah terdapat pada code saya, biarpun mungkin belum sempurna. Pertama, saya menerapkan prinsip 'meaningful names' yang mana nama-nama yang diberikan terhadap apapun (bisa class, variable, method, function, dll.) di code ini sudah cukup self-explanatory kegunaannya, seperti deleteById, findById, allProduct, productIterator, dsb. 
Kedua, pada code sudah meminimalisir penggunaan comments yang tidak diperlukan (atau excessive), dengan memberikan nama deskriptif pada code saya.
Ketiga, diterapkan Single Responsibility Principle, yang mana setiap class hanya bertanggung jawab terhadap satu tugas utama, seperti ProductController yang mengatasi http request dan response, ProductRepository yang mengatasi akses data (melakukan perubahan pada database).
Keempat, meminimalisir dilakukannya duplicate code terutama pada class-class utama aplikasi. Namun pada aspek ini, sepertinya masih bisa dilakukan pengurangan duplikasi code pada template-template html-nya



### Reflection 2 

1. Saya merasa cukup puas setelah coding unit test untuk app ini. Karena unit test saat ini merupakan sesuatu komponen yang penting ketika mengembangkan suatu program aplikasi untuk memastikan segala komponenen berjalan dengan lancar tanpa kita harus mengecek secara manual. Selain itu, unit test akan secara signifikan membantu kita pada proses debugging dengan memudahkan identifikasi masalah.
  Menurut saya, sepertinya tidak ada angka tepat tentang seberapa banyak unit test yang harus dibuat, karena jika diiterasikan, terdapat tak hingga banyak kemungkinan yang bisa dicek. Maka dari itu, menurut saya sekedar memberikan positive test, negative test, dan bahkan boundary test yang mencakup kasus-kasus berbeda (dan relevan) sudah cukup memberikan gambaran verifikasi pada komponen-komponen terkecil pada kita (unit test)
  Jika mendapatkan 100% code coverage pada unit test, bukan berarti code kita sudah tanpa eror atau bugs, karena 100% adalah relatif terhadap test yang kita buat sehingga bisa saja terdapat unchecked case yang menimbulkan eror. Selain itu terdapat masalah logic error, yang tentu tidak bisa dilihat dari 100% code coverage.
2. Menurut saya, menambahkan suatu test suite yang memverifikasi jumlah item pada product list akan cukup mengurangi kualitas code secara keseluruhan. Dari proses yang dieksekusi, pastinya kita akan melakukan create instance product pada test suite ini (kemungkinan besar lebih dari 1), sedangkan create product juga kita lakukan pada test suite lain. Hal ini berarti terdapat repetasi code dan flow yang bisa disimplifikasi dengan grouping test case ini dalam satu class.
   Belum lagi inisialiasi variabel yang dilakukan seperti baseUrl akan diulang pada 3 functional test yang berbeda. Hal ini bisa kita buat lebih baik dengan melakukan membuat suatu base functional test class, sehingga segala setup dasar yang akan dilakukan untuk functional test apapun sudah ada pada class ini.

</details>

<details>
    <summary><h2>Module 2</h2></summary>

### Reflection 1 
Isu Code Quality yang diperbaiki:
1. Terdapat isu code duplication, terutama pada bagian controller yang mana terdapat beberapa rerouting ke product list page. Strategi penyelesaiannya adalah dengan membuat constant untuk path tersebut, sehingga jika terdapat perubahan path, kita hanya perlu mengubah satu file saja.
2. Isu setup block yang tidak digunakan pada unit test. Strateginya adalah proses setup dihilangkan untuk mempercepat proses testing.
3. Isu grouping dependancies yang tidak best practice, banyak dependancies pada fungsi yang sama namun lokasinya berjauhan satu sama lain sehingga lebih sulit membacanya. Strateginya adalah dengan menuliskan dependancies yang memiliki fungsi yang sama secara berurutan.
4. Isu import yang tidak digunakan dan exception yang tidak mungkin terpanggil. Strateginya adalah dengan menghapus import dan exception tersebut.
5. Isu field injection suatu class pada unit test yang berbeda, yang mana bukanlah praktik terbaik. Strateginya adalah dengan melakukan constructor injection pada class tersebut.


### Reflection 2
Saya rasa implementasi sekarang sudah cukup memenuhi CI/CD pipeline. Sudah terdapat proses build, test, dan deploy pada pipeline/workflow project ini. Bahkan juga sudah menambahkan code scanning tools dari scorecard, dan sonarcloud. Selain itu, sudah terdapat proses deployment pada koyeb yang dilakukann continuous deployment yang menghubungkan koyeb dengan github repo saya. Sehingga setiap terdapat perubahan pada main branch, akan langsung terdeploy ke koyeb bahkan tanpa perlu menulis workflow baru. Oleh karena itu, saya rasa implementasi CI/CD sudah cukup baik, setidaknya untuk project pribadi ini. Mungkin saja untuk project yang lebih besar, perlu ditambahkan beberapa proses lain seperti security scanning, performance testing, dll.
</details>