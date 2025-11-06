class DetailedViewScreen : AppCompatActivity() {

    private lateinit var movies: ArrayList<String>
    private lateinit var directors: ArrayList<String>
    private lateinit var categories: ArrayList<String>
    private lateinit var ratings: ArrayList<Int>
    private lateinit var comments: ArrayList<String>

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_packing_list)

        movies = intent.getStringArrayListExtra("movies") ?: arrayListOf()
        directors = intent.getStringArrayListExtra("directors") ?: arrayListOf()
        categories = intent.getStringArrayListExtra("categories") ?: arrayListOf()
        ratings = intent.getIntegerArrayListExtra("ratings") ?: arrayListOf()
        comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()

        val displayAllButton = findViewById<Button>(R.id.btnDisplayAll)
        val displayFilteredButton = findViewById<Button>(R.id.btnDisplayFiltered)
        val backButton = findViewById<Button>(R.id.btnBack)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        displayAllButton.setOnClickListener {
            val allMovies = buildMovieList()
            adapter = MovieAdapter(allMovies)
            recyclerView.adapter = adapter
        }

        displayFilteredButton.setOnClickListener {
            val filteredMovies = buildMovieList().filter { it.rating >= 2 }
            adapter = MovieAdapter(filteredMovies)
            recyclerView.adapter = adapter
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun buildMovieList(): List<Movie> {
        return movies.indices.map { i ->
            Movie(
                title = movies[i],
                director = directors[i],
                category = categories[i],
                rating = ratings[i],
                comment = comments[i]
            )
        }
    }
}