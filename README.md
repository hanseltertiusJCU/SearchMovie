# SearchMovie
Applikasi untuk memenuhi tugas submission 1 untuk Dicoding kelas MADE

Projek ini berisi tentang Search Movie yang diambil dari TheMovieDb.

Untuk link dari sumber datanya, bisa kalian lihat disini: https://www.themoviedb.org/

Cara mengambil data dari situ adalah:
1. https://api.themoviedb.org/3/search/movie?api_key={API KEY}&language=en-US&query={MOVIE NAME} untuk Movie Search
2. https://image.tmdb.org/t/p/{POSTER SIZE}/{POSTER PATH}

Poster sizenya itu ada beberapa ukuran yakni: w92, w154, w185, w342, w500, w780, dan original.

Requirements dari projek ini adalah:
1. Halaman untuk pencarian film.
2. Halaman detail ketika item hasil pencarian dipilih.
3. Tampilan poster dari film (yang diambil dari "poster_path" attribute dari JSON).

Dalam projek ini, terdapat beberapa 3rd Party Library, yaitu:
1. Picasso - untuk mengambil gambar dari JSON
2. LoopJ - untuk menghandle pengambilan data ke webservice (alternatif dari HTTP GET)

PERINGATAN: Tugas ini hanya digunakan sebagai refrensi saja, bukan untuk melakukan plagiarisme
