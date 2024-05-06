# Tucil3_135522136
Penyelesaian Permainan Word Ladder Menggunakan Algoritma UCS, Greedy Best First Search, dan A*

## About The Project

Word ladder adalah permainan teka-teki di mana pemain mencoba untuk mengubah satu kata menjadi kata lainnya dengan mengganti satu huruf pada setiap langkahnya. Setiap langkah harus menghasilkan kata baru yang valid, dengan tujuan untuk mencapai kata target dalam jumlah langkah yang minimal. Misalnya, untuk mengubah kata "cat" menjadi "dog", satu langkah yang mungkin adalah mengubah "cat" menjadi "cot", kemudian "cot" menjadi "cog", dan akhirnya "cog" menjadi "dog".

Untuk menyelesaikan permainan word ladder, kita dapat menggunakan berbagai algoritma pencarian, termasuk Uniform Cost Search (UCS), Greedy Best First Search, dan A*.

    1.UCS (Uniform Cost Search): Pencarian ini berfokus pada menemukan jalur dengan biaya terendah. Algoritma ini secara berulang memperluas node yang memiliki biaya terendah saat ini, sehingga mencapai solusi dengan biaya minimum.

    2.Greedy Best First Search: Pencarian ini memprioritaskan node berdasarkan perkiraan jarak langsung ke tujuan. Algoritma ini cenderung memilih jalur yang terlihat paling dekat dengan solusi, tanpa mempertimbangkan biaya sebenarnya untuk mencapainya.

   3. A*: Pencarian ini menggabungkan keunggulan UCS dan Greedy Best First Search dengan menggunakan fungsi evaluasi yang menggabungkan biaya sejauh ini (G) dan estimasi biaya yang tersisa hingga tujuan (H). Algoritma ini berusaha untuk menemukan jalur dengan biaya total terendah.



<br/>




<!-- GETTING STARTED -->
## Getting Started



### HOW TO USE




   
1. CLONE THIS REPO

   ```
   git clone https://github.com/mzaki9/Tucil3_13522136
   ```
2. GO TO SRC
   
```sh
   cd src/
```   

3. Compile The Program
```sh
   javac Main.java
```  

4. Run The Program
```sh
   java Main
```  

5. Choose Database

Masukan dalam format 1 atau 2
``` sh
    Pilih Database:
    1. data.txt (dari asisten)
    2. word_alpha.txt

    Masukkan pilihan: 1
```



<br/>


<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- PROJECT STATUS -->
## Project Status
Project status: complete 
<br/>
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments


* [Muhammad Zaki](https://github.com/mzaki9)

