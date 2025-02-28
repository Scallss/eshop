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

<details>
    <summary><h2>Module 3</h2></summary>

### Reflection 1 (Prinsip SOLID yang Diterapkan)

Prinsip-prinsip yang saya terapkan dalam proyek saya meliputi Single Responsibility Principle (SRP), Open-Closed Principle (OCP), Liskov Substitution Principle (LSP), Interface Segregation Principle (ISP), dan Dependency Inversion Principle (DIP).

Dengan menerapkan SRP, saya memastikan setiap kelas memiliki satu tanggung jawab spesifik tanpa mencampur tugasnya dengan kelas lain. Contohnya, ProductController hanya menangani pemetaan untuk Product, sedangkan CarController khusus untuk Car.

Dalam OCP, saya merancang kelas dan fungsi agar dapat diperluas tanpa perlu mengubah kode yang sudah ada. Misalnya, saya menggunakan interface ProductService sebagai dasar untuk ProductServiceImpl, yang mengharuskan implementasi metode tertentu tetapi tetap memungkinkan penambahan metode tambahan sebagai pendukung.

Untuk LSP, saya memastikan bahwa penggunaan superclass tidak merusak kode yang ada. Sebagai contoh, saya mengganti tipe CarServiceImpl menjadi CarService, sehingga kode tetap berjalan dengan baik tanpa mengalami kendala.

Dengan ISP, saya membagi antarmuka menjadi bagian-bagian yang hanya berisi fungsionalitas yang benar-benar diperlukan, seperti pembuatan, penyimpanan, pengeditan, penghapusan, dan pencarian objek untuk Product dan Car.

Terakhir, melalui DIP, saya menghindari instansiasi langsung objek dalam kelas yang membutuhkannya. Sebagai gantinya, saya menggunakan dependency injection melalui anotasi @Autowired milik Spring, misalnya saat Service membutuhkan Repository.


### Reflection 2 (Keuntungan Menerapkan Prinsip SOLID)

Dengan menerapkan prinsip SOLID, kita dapat meningkatkan manageability kode, membuatnya lebih mudah diuji, dibaca, dan diperluas tanpa merusak bagian lain dari kode.

- **Kemudahan Pengujian**

  Ketika sebuah metode terlalu panjang dan melakukan banyak hal sekaligus, mengidentifikasi sumber kesalahan menjadi sulit. Dengan memecah metode menjadi unit-unit kecil yang lebih spesifik, kita bisa menguji setiap bagian secara terpisah, sehingga lebih mudah menemukan dan memperbaiki bug. Selain itu, jika sebuah kelas memiliki ketergantungan tinggi pada kelas lain, pengujian menjadi sulit karena kita harus mempertimbangkan hubungan antar kelas dan apakah kita perlu melakukan mocking terhadap dependency tersebut.


- **Peningkatan Readability**

  Kode yang lebih modular juga lebih mudah dibaca dan dipahami. Jika sebuah metode terlalu panjang dan kompleks, orang lain yang membaca kode akan kesulitan memahami cara kerjanya. Namun, dengan membaginya menjadi metode yang lebih kecil, tujuan dan alur kerja kode menjadi lebih jelas. Kode yang lebih pendek lebih mudah dipahami dibandingkan kode panjang dan rumit.


- **Kemudahan Menambah Fitur**

  Prinsip Open-Closed Principle (OCP) memastikan bahwa kode dapat diperluas tanpa harus mengubah bagian yang sudah ada. Ketika kita ingin menambahkan fitur baru, kita cukup menambahkan kode baru tanpa mengubah fitur yang sudah berjalan. Misalnya, jika saya ingin menambahkan mapping untuk Product, saya hanya perlu mengedit ProductController.java, karena kelas ini bertanggung jawab atas pemetaan Product.


- **Mengurangi Risiko Kerusakan Kode**

  Prinsip Dependency Inversion Principle (DIP) membantu memisahkan ketergantungan antara komponen dalam kode. Dengan menggunakan interface daripada concrete implementation, kita dapat mengganti implementasi tanpa mengganggu bagian lain dari kode. Sebagai contoh, jika saya ingin mengubah implementasi method create untuk Car, saya tidak perlu mengubah metode create pada tingkat service atau controller. Cukup dengan memperbarui CarRepository, selama return type tetap sama, perubahan ini tidak akan merusak codebase.


Dengan menerapkan prinsip SOLID, kita tidak hanya meningkatkan kualitas kode, tetapi juga mempermudah pemeliharaan, pengujian, dan pengembangan proyek dalam jangka panjang.


### Reflection 3 (Kelemahan Kode Tanpa Prinsip SOLID)

- **Kode Sulit Dibaca dan Dikelola**

  Jika kode tidak dipisahkan dengan baik sesuai tanggung jawabnya (Single Responsibility Principle - SRP), maka proyek dapat menjadi berantakan. Misalnya, jika ada kelas yang bertindak sebagai controller dan service sekaligus, struktur kode menjadi tidak jelas dan sulit dipahami. Metode yang terlalu panjang dan melakukan banyak hal juga menyulitkan pengembang baru dalam memahami alur kerja kode.


- **Kesulitan dalam Menambah Fitur**

  Jika kode tidak mengikuti Open-Closed Principle (OCP), setiap kali kita ingin menambahkan fitur baru, kita harus melakukan banyak perubahan pada kode lama. Hal ini meningkatkan risiko merusak bagian lain dari sistem. Misalnya, jika saya ingin mengubah implementasi metode delete untuk Product, saya harus memodifikasi ProductRepository. Namun, tanpa struktur yang baik, saya tidak bisa memastikan bahwa perubahan ini tidak akan berdampak negatif pada bagian lain dari kode. Hal ini dapat menyebabkan bug yang sulit dilacak, seperti produk yang tidak terhapus meskipun pengguna melakukan permintaan penghapusan.


- **Testing menjadi sulit**

  Kode yang memiliki dependensi tinggi pada bagian lain sulit untuk diuji karena kita perlu menyesuaikan banyak variabel atau melakukan mocking sebelum bisa menjalankan unit test. Jika sebuah metode terlalu panjang dan memiliki banyak ketergantungan, kita harus mengeluarkan lebih banyak usaha untuk mengisolasi bagian yang ingin diuji, yang pada akhirnya memperlambat proses pengembangan.


Dengan kata lain, tanpa prinsip SOLID, proses development akan menjadi lebih lambat, kode lebih sulit dipahami, testing lebih rumit, dan setiap perubahan bisa menyebabkan masalah yang tidak terduga.
</details>